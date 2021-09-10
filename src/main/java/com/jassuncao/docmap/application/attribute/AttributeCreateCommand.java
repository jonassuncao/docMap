package com.jassuncao.docmap.application.attribute;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.jassuncao.docmap.application.AbstractCommand;
import com.jassuncao.docmap.domain.attribute.TypeData;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "attribute_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AttributeCreateCommand.AttributeCreateEntityCommand.class, name = "ENTITY"),
        @JsonSubTypes.Type(value = AttributeCreateCommand.AttributeCreateRelationshipCommand.class, name = "RELATIONSHIP")
})
public abstract class AttributeCreateCommand extends AbstractCommand {

    @NotEmpty
    private String alias;
    @NotEmpty
    private String name;
    @NotNull
    private TypeData type;
    private String length;
    private boolean required;
    private boolean uniqueConstraint;
    @NotEmpty
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

    public Optional<String> getLength() {
        return Optional.ofNullable(length);
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

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class AttributeCreateEntityCommand extends AttributeCreateCommand {

        @NotNull
        private UUID entityId;

        public UUID getEntityId() {
            return entityId;
        }

        public void setEntityId(UUID entityId) {
            this.entityId = entityId;
        }
    }

    public static class AttributeCreateRelationshipCommand extends AttributeCreateCommand {

        @NotNull
        private UUID relationshipId;

        public UUID getRelationshipId() {
            return relationshipId;
        }

        public void setRelationshipId(UUID relationshipId) {
            this.relationshipId = relationshipId;
        }
    }
}
