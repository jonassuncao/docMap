package com.jassuncao.docmap.domain.attribute;

import com.jassuncao.docmap.domain.attribute.AttributeData.AttributeDataRelationship;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
@DiscriminatorValue("RELATIONSHIP")
public class AttributeRelationship extends Attribute {

    private UUID relationshipId;

    AttributeRelationship(AttributeDataRelationship data) {
        super(data);
        setRelationshipId(data.getRelationshipId());
    }

    AttributeRelationship() {
        super();
    }

    public UUID getRelationshipId() {
        return relationshipId;
    }

    void setRelationshipId(UUID relationshipId) {
        this.relationshipId = relationshipId;
    }
}
