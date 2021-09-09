package com.jassuncao.docmap.application.relationship;

import com.jassuncao.docmap.application.AbstractCommand;

import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
public class RelationshipDeleteCommand extends AbstractCommand {

    private UUID id;

    public RelationshipDeleteCommand(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
