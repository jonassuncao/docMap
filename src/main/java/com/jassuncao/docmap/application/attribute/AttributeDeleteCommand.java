package com.jassuncao.docmap.application.attribute;

import com.jassuncao.docmap.application.AbstractCommand;

import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
public class AttributeDeleteCommand extends AbstractCommand {

    private final UUID id;

    public AttributeDeleteCommand(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
