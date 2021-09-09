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
        return null;
    }

    public Entity update(UUID id, EntityData data) {
        return null;
    }

    public Entity delete(UUID id) {
        return null;
    }
}
