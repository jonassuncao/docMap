package com.jassuncao.docmap.application.attribute;

import com.jassuncao.docmap.application.AbstractCommand;
import com.jassuncao.docmap.domain.attribute.TypeData;

import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
public class AttributeCreateCommand extends AbstractCommand {

    private String alias;
    private String name;
    private TypeData type;
    private String length;
    private boolean required;
    private boolean uniqueConstraint;
    private String cardinality;
    private String description;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeData getType() {
        return type;
    }

    public void setType(TypeData type) {
        this.type = type;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isUniqueConstraint() {
        return uniqueConstraint;
    }

    public void setUniqueConstraint(boolean uniqueConstraint) {
        this.uniqueConstraint = uniqueConstraint;
    }

    public String getCardinality() {
        return cardinality;
    }

    public void setCardinality(String cardinality) {
        this.cardinality = cardinality;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public class AttributeCreateEntityCommand extends AttributeCreateCommand {

        private UUID entityId;

        public UUID getEntityId() {
            return entityId;
        }

        public void setEntityId(UUID entityId) {
            this.entityId = entityId;
        }
    }

    public class AttributeCreateRelationshipCommand extends AttributeCreateCommand {

        private UUID relationshipId;

        public UUID getRelationshipId() {
            return relationshipId;
        }

        public void setRelationshipId(UUID relationshipId) {
            this.relationshipId = relationshipId;
        }
    }
}
