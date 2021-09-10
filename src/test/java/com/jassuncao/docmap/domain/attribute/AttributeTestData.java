package com.jassuncao.docmap.domain.attribute;

import com.jassuncao.docmap.domain.attribute.AttributeData.AttributeDataEntity;
import com.jassuncao.docmap.domain.attribute.AttributeData.AttributeDataRelationship;

import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
public class AttributeTestData {

    private UUID entityId;
    private UUID relationshipId;
    private String alias = "alias";
    private String name = "name";
    private TypeData type = TypeData.Text;
    private String length;
    private boolean required;
    private boolean uniqueConstraint;
    private String cardinality = "1:1";
    private String description;

    public static AttributeTestData createAttribute() {
        return new AttributeTestData();
    }

    public AttributeTestData entityId(UUID entityId) {
        this.entityId = entityId;
        return this;
    }

    public AttributeTestData relationshipId(UUID relationshipId) {
        this.relationshipId = relationshipId;
        return this;
    }

    public AttributeTestData alias(String alias) {
        this.alias = alias;
        return this;
    }

    public AttributeTestData name(String name) {
        this.name = name;
        return this;
    }

    public AttributeTestData type(TypeData type) {
        this.type = type;
        return this;
    }

    public AttributeTestData length(String length) {
        this.length = length;
        return this;
    }

    public AttributeTestData required(boolean required) {
        this.required = required;
        return this;
    }

    public AttributeTestData uniqueConstraint(boolean uniqueConstraint) {
        this.uniqueConstraint = uniqueConstraint;
        return this;
    }

    public AttributeTestData cardinality(String cardinality) {
        this.cardinality = cardinality;
        return this;
    }

    public AttributeTestData description(String description) {
        this.description = description;
        return this;
    }

    public AttributeDataEntity dataEntity() {
        final AttributeDataEntity data = new AttributeDataEntity();
        data(data);
        data.setEntityId(entityId);
        return data;
    }

    public AttributeDataRelationship dataRelationship() {
        final AttributeDataRelationship data = new AttributeDataRelationship();
        data(data);
        data.setRelationshipId(relationshipId);
        return data;
    }

    private void data(AttributeData data) {
        data.setAlias(alias);
        data.setName(name);
        data.setType(type);
        data.setLength(length);
        data.setRequired(required);
        data.setUnique(uniqueConstraint);
        data.setCardinality(cardinality);
        data.setDescription(description);
    }

    public AttributeEntity buildEntity() {
        return new AttributeEntity(dataEntity());
    }

    public AttributeRelationship buildRelationship() {
        return new AttributeRelationship(dataRelationship());
    }
}
