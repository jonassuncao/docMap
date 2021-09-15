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

    public Project when(ProjectUpdateCommand command) {
        return projectUseCase.update(command.getId(), command.getName(), command.getDescription());
    }

    public void when(ProjectDeleteCommand command) {
        projectUseCase.delete(command.getId());
    }

    public Object when(ProjectBuildHibernateCommand command) {
        return projectUseCase.buildHibernate(command.getId());
    }
}
