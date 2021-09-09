package com.jassuncao.docmap.domain.project;

import com.jassuncao.docmap.domain.Identifier;

import javax.persistence.Entity;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

@Entity
public class Project extends Identifier {

    private String name;
    private String description;

    Project() {
        super();
    }

    Project(String name, String description) {
        update(name, description);
        inicialize();
    }

    void update(String name, String description) {
        setName(name);
        setDescription(description);
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
