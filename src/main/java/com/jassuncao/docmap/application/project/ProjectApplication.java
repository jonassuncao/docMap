package com.jassuncao.docmap.application.project;

import com.jassuncao.docmap.domain.project.Project;
import com.jassuncao.docmap.domain.project.ProjectUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

@Component
@Transactional
public class ProjectApplication {

    @Autowired
    private ProjectUseCase projectUseCase;

    public Project when(ProjectCreateCommand command) {
        return projectUseCase.create(command.getName(), command.getDescription());
    }
}
