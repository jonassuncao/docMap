package com.jassuncao.docmap.application.project;

import com.jassuncao.docmap.application.AbstractCommand;

import javax.validation.constraints.NotEmpty;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

public class ProjectCreateCommand extends AbstractCommand {

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
