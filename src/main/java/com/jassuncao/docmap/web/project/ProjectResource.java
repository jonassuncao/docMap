package com.jassuncao.docmap.web.project;

import com.jassuncao.docmap.application.project.ProjectApplication;
import com.jassuncao.docmap.application.project.ProjectCreateCommand;
import com.jassuncao.docmap.domain.project.Project;
import com.jassuncao.docmap.web.ResponseUtils;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return ResponseUtils.created(Project.class, result.getId());
    }
}
