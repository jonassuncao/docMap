package com.jassuncao.docmap.domain.project;

import com.jassuncao.docmap.application.project.ProjectCreateCommand;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

@Service
public class ProjectUseCase {

    private final ProjectRepository projectRepository;

    public ProjectUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
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
}
