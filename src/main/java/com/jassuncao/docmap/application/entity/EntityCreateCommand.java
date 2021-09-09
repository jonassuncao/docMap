package com.jassuncao.docmap.application.entity;

import com.jassuncao.docmap.application.AbstractCommand;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
public class EntityCreateCommand extends AbstractCommand {

    @NotNull
    private UUID projectId;
    @NotEmpty
    private String alias;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    private UUID extendId;

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

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

    public Optional<UUID> getExtendId() {
        return Optional.ofNullable(extendId);
    }

    public void setExtendId(UUID extendId) {
        this.extendId = extendId;
    }
}
