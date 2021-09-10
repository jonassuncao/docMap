package com.jassuncao.docmap.application.attribute;

import com.jassuncao.docmap.application.attribute.AttributeCreateCommand.AttributeCreateEntityCommand;
import com.jassuncao.docmap.application.attribute.AttributeCreateCommand.AttributeCreateRelationshipCommand;
import com.jassuncao.docmap.application.attribute.AttributeUpdateCommand.AttributeUpdateEntityCommand;
import com.jassuncao.docmap.application.attribute.AttributeUpdateCommand.AttributeUpdateRelationshipCommand;
import com.jassuncao.docmap.domain.attribute.*;
import com.jassuncao.docmap.domain.attribute.AttributeData.AttributeDataEntity;
import com.jassuncao.docmap.domain.attribute.AttributeData.AttributeDataRelationship;
import com.jassuncao.docmap.infra.OptionalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
@Component
@Transactional
public class AttributeApplication {

    @Autowired
    private AttributeEntityUseCase attributeEntityUseCase;
    @Autowired
    private AttributeRelationshipUseCase attributeRelationshipUseCase;

    public Attribute when(AttributeCreateCommand command) {
        return new OptionalMapper<Attribute>(command)
                .ifCast(AttributeCreateEntityCommand.class, this::createAttribute)
                .ifCast(AttributeCreateRelationshipCommand.class, this::createAttribute)
                .build().orElseThrow();
    }

    private AttributeEntity createAttribute(AttributeCreateEntityCommand command) {
        final AttributeDataEntity data = new AttributeDataEntity();
        data.setEntityId(command.getEntityId());
        attributeHelper(command, data);
        return attributeEntityUseCase.create(data);
    }

    private AttributeRelationship createAttribute(AttributeCreateRelationshipCommand command) {
        final AttributeDataRelationship data = new AttributeDataRelationship();
        data.setRelationshipId(command.getRelationshipId());
        attributeHelper(command, data);
        return attributeRelationshipUseCase.create(data);
    }

    public Attribute when(AttributeUpdateCommand command) {
        return new OptionalMapper<Attribute>(command)
                .ifCast(AttributeUpdateEntityCommand.class, this::updateAttribute)
                .ifCast(AttributeUpdateRelationshipCommand.class, this::updateAttribute)
                .build().orElseThrow();
    }

    private AttributeEntity updateAttribute(AttributeUpdateEntityCommand command) {
        final AttributeDataEntity data = new AttributeDataEntity();
        data.setEntityId(command.getEntityId());
        attributeHelper(command, data);
        return attributeEntityUseCase.update(command.getId(), data);
    }

    private AttributeRelationship updateAttribute(AttributeUpdateRelationshipCommand command) {
        final AttributeDataRelationship data = new AttributeDataRelationship();
        data.setRelationshipId(command.getRelationshipId());
        attributeHelper(command, data);
        return attributeRelationshipUseCase.update(command.getId(), data);
    }

    public void when(AttributeDeleteCommand command) {
        attributeEntityUseCase.delete(command.getId());
        attributeRelationshipUseCase.delete(command.getId());
    }

    private void attributeHelper(AttributeCreateCommand command, AttributeData data) {
        data.setAlias(command.getAlias());
        data.setName(command.getName());
        data.setType(command.getType());
        command.getLength().ifPresent(data::setLength);
        data.setRequired(command.isRequired());
        data.setUnique(command.isUniqueConstraint());
        data.setCardinality(command.getCardinality());
        command.getDescription().ifPresent(data::setDescription);
    }
}
