package com.jassuncao.docmap.application.project;

import com.jassuncao.docmap.application.AbstractCommand;

import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
public class ProjectDeleteCommand extends AbstractCommand {

    private final UUID id;

    public ProjectDeleteCommand(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
