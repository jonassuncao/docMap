package com.jassuncao.docmap.domain.entity;

import com.jassuncao.docmap.domain.Identifier;

import java.util.Optional;
import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

@javax.persistence.Entity
public class Entity extends Identifier {

    private UUID projectId;
    private String alias;
    private String name;
    private String description;
    private UUID extendId;

    Entity() {
        super();
    }

    public Entity(EntityData data) {
        setProjectId(data.getProjectId());
        setAlias(data.getAlias());
        setName(data.getName());
        setDescription(data.getDescription());
        data.getExtendId().ifPresentOrElse(this::setExtendId, () -> extendId = null);
        inicialize();
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    public String getAlias() {
        return alias;
    }

    private void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public Optional<UUID> getExtendId() {
        return Optional.ofNullable(extendId);
    }

    private void setExtendId(UUID extendId) {
        this.extendId = extendId;
    }
}
