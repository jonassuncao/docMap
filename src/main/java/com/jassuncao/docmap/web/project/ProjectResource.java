package com.jassuncao.docmap.web.project;

import com.jassuncao.docmap.application.project.*;
import com.jassuncao.docmap.domain.project.Project;
import com.jassuncao.docmap.web.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

@RestController
@RequestMapping("/api/projects")
public class ProjectResource {

    @Autowired
    private ProjectApplication projectApplication;

    @PostMapping()
    public ResponseEntity<UUID> create(@RequestBody ProjectCreateCommand command) {
        final var result = projectApplication.when(command);
        return ResponseUtils.created(result.getId(), Project.class);
    }

    @PostMapping("/{id}/hibernate")
    public ResponseEntity<?> hibernate(@PathVariable UUID id) {
        final var result = projectApplication.when(new ProjectBuildHibernateCommand(id));
        return ResponseUtils.created(new ProjectBuildHibernateCommand(id).getId(), Project.class);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> update(@PathVariable UUID id, @RequestBody ProjectUpdateCommand command) {
        final var result = projectApplication.when(command.id(id));
        return ResponseUtils.updated(result.getId(), Project.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> Delete(@PathVariable UUID id) {
        projectApplication.when(new ProjectDeleteCommand(id));
        return ResponseUtils.deleted(Project.class);
    }
}
