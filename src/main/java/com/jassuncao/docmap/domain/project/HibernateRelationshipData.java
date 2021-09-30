package com.jassuncao.docmap.domain.project;

import com.jassuncao.docmap.domain.entity.Entity;
import com.jassuncao.docmap.domain.relationship.Relationship;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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

    public void resolveOneToOne(Project project, Entity to) {
        relationship.getRoleTo().ifPresentOrElse(this::setAlias, () -> {
            setAlias(to.getAlias());
            pack = Normalize.importForm(project.getName(), to.getAlias());
        });
        type = Normalize.classForm(to.getAlias());
        column = "@OneToOne";
        options = List.of(options(relationship.getRoleTo()));
        getSets = getsSets(relationship);
    }

    public void resolveOneToMany(Project project, Entity from, Entity to) {
        relationship.getRoleFrom().ifPresentOrElse(this::setAlias, () -> {
            setAlias(to.getAlias());
            pack = Normalize.importForm(project.getName(), to.getAlias());
        });
        type = String.format("%s<%s>", collectionType(), Normalize.classForm(to.getAlias()));
        column = "@OneToMany";
        options = List.of(options(relationship.getRoleFrom(), from.getAlias()));
        getSets = getsSets(relationship, "getSettersWithoutOptional.java", getAlias());
        initializer = relationship.isUniqueConstraint() ? resolveCapacitySet() : resolveCapacityList();
    }

    public void resolveManyToOne(Project project, Entity to) {
        relationship.getRoleTo().ifPresentOrElse(this::setAlias, () -> {
            setAlias(to.getAlias());
            pack = Normalize.importForm(project.getName(), to.getAlias());
        });
        type = Normalize.classForm(to.getAlias());
        column = "@ManyToOne";
        options = List.of(options(relationship.getRoleTo()));
        getSets = getsSets(relationship);
    }

    public void resolveManyToMany(Project project, Entity from, Entity to) {
        relationship.getRoleTo().ifPresentOrElse(this::setAlias, () -> {
            setAlias(to.getAlias());
            pack = Normalize.importForm(project.getName(), to.getAlias());
        });
        type = String.format("%s<%s>", collectionType(), Normalize.classForm(to.getAlias()));
        column = "@ManyToMany";
        options = List.of(joinTable(from, to));
        getSets = getsSets(relationship, "getSettersWithoutOptional.java", getAlias());
        initializer = relationship.isUniqueConstraint() ? resolveCapacitySet() : resolveCapacityList();
    }

    private String joinTable(Entity from, Entity to) {
        return new StringBuilder()
                .append(String.format("@JoinTable(name=\"%s\",\n", Normalize.dataBaseForm(relationship.getAlias())))
                .append(String.format("\t\tjoinColumns={%s},\n", options(relationship.getRoleTo(), to.getAlias())))
                .append(String.format("\t\tinverseJoinColumns={%s})", options(relationship.getRoleFrom(), from.getAlias())))
                .toString();
    }

    private String resolveAlias(Optional<String> role, String defaultAlias) {
        return Normalize.dataBaseForm(role.orElse(defaultAlias));
    }

    private String resolveCapacitySet() {
        return relationship.toMaxCardinality().map(capacity -> String.format("new HashSet<>(%s)", capacity)).orElse("new HashSet<>()");
    }

    private String resolveCapacityList() {
        return relationship.toMaxCardinality().map(capacity -> String.format("new ArrayList<>(%s)", capacity)).orElse("new LinkedList<>()");
    }

    private String collectionType() {
        return relationship.isUniqueConstraint() ? "Set" : "List";
    }

    private String options(Optional<String> role) {
        return options(role, getName());
    }

    private String options(Optional<String> role, String name) {
        final List<String> columns = new LinkedList<>();
        role.ifPresentOrElse(nameWithRole(columns), () -> columns.add(name(name)));
        ifTrue(relationship.isRequired(), () -> columns.add("nullable=false"));
        columns.add(String.format("\n\t\t\t\tforeignKey = @ForeignKey(name = \"%s_%s_fkey\")", Normalize.dataBaseForm(relationship.getAlias()), resolveAlias(role, name)));
        return String.format("@JoinColumn(%s)", String.join(", ", columns));
    }

    private String name(String name) {
        return String.format("name=\"%s_id\"", Normalize.fieldForm(name));
    }

    private Consumer<String> nameWithRole(List<String> columns) {
        return role -> columns.add(String.format("name=\"%s_id\"", Normalize.fieldForm(role)));
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
