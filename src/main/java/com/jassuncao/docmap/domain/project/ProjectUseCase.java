package com.jassuncao.docmap.domain.project;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProjectUseCase {

    private final ProjectRepository projectRepository;

    public ProjectUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project create(String name, String description) {
        return null;
    }

    public Project update(UUID id, String name, String description) {
        return null;
    }

    public Project delete(UUID id) {
        return null;
    }
}
