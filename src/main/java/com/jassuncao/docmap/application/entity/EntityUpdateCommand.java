package com.jassuncao.docmap.application.entity;

import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
public class EntityUpdateCommand extends EntityCreateCommand {

    private UUID id;

    public EntityUpdateCommand id(UUID id) {
        this.id = id;
        return this;
    }

    public UUID getId() {
        return id;
    }
}
