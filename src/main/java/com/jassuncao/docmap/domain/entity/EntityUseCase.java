package com.jassuncao.docmap.domain.entity;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

@Service
public class EntityUseCase {

    private final EntityRepository entityRepository;

    public EntityUseCase(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }


    public Entity create(EntityData data) {
        final Entity entity = new Entity(data);
        return entityRepository.save(entity);
    }

    public Entity update(UUID id, EntityData data) {
        final Entity entity = entityRepository.getById(id);
        entity.update(data);
        return entityRepository.save(entity);
    }

    public void delete(UUID id) {
        final Entity entity = entityRepository.getById(id);
        entityRepository.delete(entity);
    }
}
