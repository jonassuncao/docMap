package com.jassuncao.docmap.core.entity;

import javax.persistence.Entity;
import javax.persistence.*;
import com.jassuncao.docmap.core.entity.AttributeData.AttributeDataEntity;
import java.util.UUID;

@Entity
@DiscriminatorValue("ENTITY")
public class AttributeEntity extends Attribute {

    private UUID entityId;

    AttributeEntity(AttributeDataEntity data) {
        super(data);
        setEntityId(data.getEntityId());
    }

    AttributeEntity() {
        super();
    }

    public UUID getEntityId() {
        return entityId;
    }

    void setEntityId(UUID entityId) {
        this.entityId = entityId;
    }
}
