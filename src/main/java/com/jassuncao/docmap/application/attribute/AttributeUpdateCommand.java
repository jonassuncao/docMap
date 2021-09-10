package com.jassuncao.docmap.application.attribute;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "attribute_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AttributeUpdateCommand.AttributeUpdateEntityCommand.class, name = "ENTITY"),
        @JsonSubTypes.Type(value = AttributeUpdateCommand.AttributeUpdateRelationshipCommand.class, name = "RELATIONSHIP")
})
public abstract class AttributeUpdateCommand extends AttributeCreateCommand {

    private UUID id;

    public AttributeUpdateCommand id(UUID id) {
        this.id = id;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public static class AttributeUpdateEntityCommand extends AttributeUpdateCommand {

        @NotNull
        private UUID entityId;

        public UUID getEntityId() {
            return entityId;
        }

        public void setEntityId(UUID entityId) {
            this.entityId = entityId;
        }
    }

    public static class AttributeUpdateRelationshipCommand extends AttributeUpdateCommand {

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
