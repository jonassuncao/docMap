package com.jassuncao.docmap.domain.attribute;

import com.jassuncao.docmap.domain.attribute.AttributeData.AttributeDataEntity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

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
