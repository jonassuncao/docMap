package com.jassuncao.docmap.domain.entity;

import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
public class EntityTestData {

    private UUID projectId;
    private String alias = "nome";
    private String name = "Nome";
    private String description = "Descrição";
    private UUID extendId;

    public EntityTestData projectId(UUID projectId) {
        this.projectId = projectId;
        return this;
    }

    public EntityTestData alias(String alias) {
        this.alias = alias;
        return this;
    }

    public EntityTestData name(String name) {
        this.name = name;
        return this;
    }

    public EntityTestData description(String description) {
        this.description = description;
        return this;
    }

    public EntityTestData extendId(UUID extendId) {
        this.extendId = extendId;
        return this;
    }

    public EntityData data() {
        final EntityData data = new EntityData();
        data.setProjectId(projectId);
        data.setAlias(alias);
        data.setName(name);
        data.setDescription(description);
        data.setExtendId(extendId);
        return data;
    }

    public Entity build() {
        return new Entity(data());
    }

    public static EntityTestData createEntity() {
        return new EntityTestData();
    }
}
