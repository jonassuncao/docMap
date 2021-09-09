package com.jassuncao.docmap.application.entity;

import com.jassuncao.docmap.domain.entity.Entity;
import com.jassuncao.docmap.domain.entity.EntityData;
import com.jassuncao.docmap.domain.entity.EntityUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
@Component
@Transactional
public class EntityApplication {

    @Autowired
    private EntityUseCase entityUseCase;

    public Entity when(EntityCreateCommand command) {
        return entityUseCase.create(helper(command));
    }

    public Entity when(EntityUpdateCommand command) {
        return entityUseCase.update(command.getId(), helper(command));
    }

    public void when(EntityDeleteCommand command) {
        entityUseCase.delete(command.getId());
    }

    private EntityData helper(EntityCreateCommand command) {
        final EntityData data = new EntityData();
        data.setProjectId(command.getProjectId());
        data.setAlias(command.getAlias());
        data.setName(command.getName());
        data.setDescription(command.getDescription());
        command.getExtendId().ifPresent(data::setExtendId);
        return data;
    }
}
