package com.jassuncao.docmap.domain.relationship;

import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
public class RelationshipTestData {

    private String alias = "relacao";
    private String name = "Relação";
    private UUID entityTo;
    private String roleTo;
    private UUID entityFrom;
    private String roleFrom;
    private boolean required;
    private boolean uniqueConstraint;
    private String cardinality = "1:1";

    public RelationshipTestData alias(String alias) {
        this.alias = alias;
        return this;
    }

    public RelationshipTestData name(String name) {
        this.name = name;
        return this;
    }

    public RelationshipTestData entityTo(UUID entityTo) {
        this.entityTo = entityTo;
        return this;
    }

    public RelationshipTestData roleTo(String roleTo) {
        this.roleTo = roleTo;
        return this;
    }

    public RelationshipTestData entityFrom(UUID entityFrom) {
        this.entityFrom = entityFrom;
        return this;
    }

    public RelationshipTestData roleFrom(String roleFrom) {
        this.roleFrom = roleFrom;
        return this;
    }

    public RelationshipTestData required(boolean required) {
        this.required = required;
        return this;
    }

    public RelationshipTestData uniqueConstraint(boolean uniqueConstraint) {
        this.uniqueConstraint = uniqueConstraint;
        return this;
    }

    public RelationshipTestData cardinality(String cardinality) {
        this.cardinality = cardinality;
        return this;
    }


    public RelationshipData data() {
        final RelationshipData data = new RelationshipData();
        data.setAlias(alias);
        data.setName(name);
        data.setEntityFromId(entityFrom);
        data.setRoleFrom(roleFrom);
        data.setEntityToId(entityTo);
        data.setRoleFrom(roleFrom);
        data.setRequired(required);
        data.setUniqueConstraint(uniqueConstraint);
        data.setCardinality(cardinality);
        return data;
    }

    public Relationship build() {
        return new Relationship(data());
    }

    public static RelationshipTestData createRelationship() {
        return new RelationshipTestData();
    }
}
