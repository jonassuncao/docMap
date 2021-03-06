package com.jassuncao.docmap.domain.entity;

import java.util.Optional;
import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

public class EntityData {

    private UUID projectId;
    private String alias;
    private String name;
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
