package com.jassuncao.docmap.domain.project;

import com.jassuncao.docmap.domain.entity.Entity;
import com.jassuncao.docmap.domain.relationship.Relationship;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

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
        options = List.of(String.format("@JoinColumn(name=\"%s_id\")", Normalize.fieldForm(getName())));
        getSets = getsSets(relationship);
    }

    private void setAlias(String alias) {
        this.alias = alias;
        this.name = Normalize.fieldForm(alias);
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
    public String getGetSets() {
        return getSets;
    }

    @Override
    public String getAlias() {
        return alias;
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
