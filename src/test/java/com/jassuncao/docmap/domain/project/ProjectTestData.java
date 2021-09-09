package com.jassuncao.docmap.domain.project;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
public class ProjectTestData {

    private String name = "Projeto";
    private String description = "Descrição";

    public ProjectTestData name(String name) {
        this.name = name;
        return this;
    }

    public ProjectTestData description(String description) {
        this.description = description;
        return this;
    }

    public Project build() {
        return new Project(name, description);
    }

    public static ProjectTestData createProject() {
        return new ProjectTestData();
    }
}
