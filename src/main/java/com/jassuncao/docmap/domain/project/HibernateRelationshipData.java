package com.jassuncao.docmap.domain.project;

import com.jassuncao.docmap.domain.entity.Entity;
import com.jassuncao.docmap.domain.relationship.Relationship;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 20/09/2021
 */
public class HibernateRelationshipData extends HibernateAttributeGenericData {

    private final Relationship relationship;
    private String getSets;
    private String column;
    private List<String> options = Collections.emptyList();
    private String pack;
    private String initializer;
    private String alias;
    private String name;
    private String type;

    HibernateRelationshipData(Relationship relationship) {
        super(relationship);
        this.relationship = relationship;
    }

    public void resolveOneToOne(Project project, Entity entity) {
        relationship.getRoleTo().ifPresentOrElse(this::setAlias, () -> {
            setAlias(entity.getAlias());
            pack = Normalize.importForm(project.getName(), entity.getAlias());
        });
        type = Normalize.classForm(entity.getAlias());
        column = "@OneToOne";
        options = List.of(options(entity));
        getSets = getsSets(relationship);
    }

    public void resolveOneToMany(Project project, Entity entity) {
        relationship.getRoleTo().ifPresentOrElse(this::setAlias, () -> {
            setAlias(entity.getAlias());
            pack = Normalize.importForm(project.getName(), entity.getAlias());
        });
        type = String.format("%s<%s>", collectionType(), Normalize.classForm(entity.getAlias()));
        column = "@OneToMany";
        options = List.of(options(entity));
        getSets = getsSets(relationship, "getSettersWithoutOptional.java");
        initializer = relationship.isUniqueConstraint() ? resolveCapacitySet() : resolveCapacityList();
    }

    private String resolveCapacitySet() {
        return relationship.fromMaxCardinality().map(capacity -> String.format("new HashSet<>(%s)", capacity)).orElse("new HashSet<>()");
    }

    private String resolveCapacityList() {
        return relationship.fromMaxCardinality().map(capacity -> String.format("new ArrayList<>(%s)", capacity)).orElse("new LinkedList<>()");
    }

    private String collectionType() {
        return relationship.isUniqueConstraint() ? "Set" : "List";
    }

    private String options(Entity entity) {
        final List<String> columns = new LinkedList<>();
        relationship.getRoleTo().ifPresentOrElse(nameWithRole(entity, columns), name(columns));
        ifTrue(relationship.isUniqueConstraint(), () -> columns.add("unique=true"));
        ifTrue(relationship.isRequired(), () -> columns.add("nullable=false"));
        return String.format("@JoinColumn(%s)", String.join(", ", columns));
    }

    private Consumer<String> nameWithRole(Entity entity, List<String> columns) {
        return role -> columns.add(String.format("name=\"%s_%s_id\"", Normalize.fieldForm(entity.getAlias()), Normalize.fieldForm(role)));
    }

    private Runnable name(List<String> columns) {
        return () -> columns.add(String.format("name=\"%s_id\"", Normalize.fieldForm(getName())));
    }

    private void ifTrue(boolean expression, Runnable runnable) {
        if (expression) {
            runnable.run();
        }
    }

    @Override
    public String getGetSets() {
        return getSets;
    }
//    private List<String> options(Relationship relationship) {
//        final List<String> options = new LinkedList<>();
//        if (relationship.isOneToOne() || relationship.isOneToMany()) {
//            options.add(String.format("@JoinColumn(name=\"%s_fk\")", Normalize.fieldForm(entityRepository.getById(relationship.getEntityToId()).getAlias()))); //Class
//        }
//        if (relationship.isManyToOne()) {
//            options.add(String.format("@JoinColumn(name=\"%s_fk\")", Normalize.fieldForm(entityRepository.getById(relationship.getEntityFromId()).getAlias()))); //Class
//        }
//        if (relationship.isManyToMany()) {
//            options.add("    @JoinTable(\n" +
//                    "        name=\"EMPLOYER_EMPLOYEE\",\n" +
//                    "        joinColumns=@JoinColumn(name=\"EMPER_ID\"),\n" +
//                    "        inverseJoinColumns=@JoinColumn(name=\"EMPEE_ID\")\n" +
//                    "    )"); //Collection
//        }
//        return options;
//    }
//
//    public String getInicializer() {
//        return "new LinkedList<>();";
////        return new HashSet<>(); if unique

//    }

    @Override
    public String getAlias() {
        return alias;
    }

    private void setAlias(String alias) {
        this.alias = alias;
        this.name = Normalize.fieldForm(alias);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getColumn() {
        return column;
    }

    @Override
    public List<String> getOptions() {
        return options;
    }

    public String getPack() {
        return pack;
    }

    public String getInitializer() {
        return initializer;
    }

    public String getName() {
        return name;
    }
}
