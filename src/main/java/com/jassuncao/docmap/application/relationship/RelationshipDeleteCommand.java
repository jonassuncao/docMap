package com.jassuncao.docmap.application.relationship;

import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
public class RelationshipDeleteCommand {

    private UUID id;

    public RelationshipDeleteCommand id(UUID id) {
        this.id = id;
        return this;
    }

    public UUID getId() {
        return id;
    }
}
