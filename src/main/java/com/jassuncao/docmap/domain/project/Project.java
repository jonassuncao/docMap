package com.jassuncao.docmap.domain.project;

import com.jassuncao.docmap.domain.Identifier;

import javax.persistence.Entity;

@Entity
public class Project extends Identifier {

    private String name;
    private String description;

    Project() {
        super();
    }

    Project(String name, String description) {
        setName(name);
        setDescription(description);
        inicialize();
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
}
