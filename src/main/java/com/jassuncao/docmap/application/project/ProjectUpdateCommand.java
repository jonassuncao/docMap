package com.jassuncao.docmap.application.project;

import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
public class ProjectUpdateCommand extends ProjectCreateCommand {

    private UUID id;

    public ProjectUpdateCommand id(UUID id) {
        this.id = id;
        return this;
    }

    public UUID getId() {
        return id;
    }
}
