package com.jassuncao.docmap.domain.project;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

@Service
public class ProjectUseCase {

    private final ProjectRepository projectRepository;
    private final HibernateService hibernateService;

    public ProjectUseCase(ProjectRepository projectRepository, HibernateService hibernateService) {
        this.projectRepository = projectRepository;
        this.hibernateService = hibernateService;
    }


    public Project create(String name, String description) {
        final Project project = new Project(name, description);
        return projectRepository.save(project);
    }

    public Project update(UUID id, String name, String description) {
        final Project project = projectRepository.getById(id);
        project.update(name, description);
        return projectRepository.save(project);
    }

    public void delete(UUID id) {
        final Project project = projectRepository.getById(id);
        projectRepository.delete(project);
    }

    public Object buildHibernate(UUID id) {
        final Project project = projectRepository.getById(id);
        return hibernateService.process(project);
    }
}
