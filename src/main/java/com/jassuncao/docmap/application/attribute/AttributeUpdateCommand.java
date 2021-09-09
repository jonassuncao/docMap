package com.jassuncao.docmap.application.attribute;

import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
public class AttributeUpdateCommand extends AttributeCreateCommand {

    private UUID id;

    public AttributeUpdateCommand id(UUID id) {
        this.id = id;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public class AttributeUpdateEntityCommand extends AttributeUpdateCommand {

        private UUID entityId;

        public UUID getEntityId() {
            return entityId;
        }

        public void setEntityId(UUID entityId) {
            this.entityId = entityId;
        }
    }

    public class AttributeUpdateRelationshipCommand extends AttributeUpdateCommand {

        private UUID relationshipId;

        public UUID getRelationshipId() {
            return relationshipId;
        }

        public void setRelationshipId(UUID relationshipId) {
            this.relationshipId = relationshipId;
        }
    }
}
