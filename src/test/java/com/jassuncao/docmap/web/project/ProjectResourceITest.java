package com.jassuncao.docmap.web.project;

import com.jassuncao.docmap.IntegrationTests;
import com.jassuncao.docmap.application.project.ProjectCreateCommand;
import com.jassuncao.docmap.application.project.ProjectUpdateCommand;
import com.jassuncao.docmap.domain.project.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static com.jassuncao.docmap.domain.project.ProjectTestData.createProject;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ProjectResourceITest extends IntegrationTests {


    @Autowired
    private ProjectRepository projectRepository;

    @Test
    void create() throws Exception {
        final var command = new ProjectCreateCommand();
        command.setName("Mapa escolar");
        command.setDescription("Vestibulum suscipit accumsan viverra.");

        final var mock = mockMvc.perform(post("/api/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body(command)))
                .andExpect(status().isCreated());

        assertThat(headerMessage(mock)).isEqualTo("project.created");

        final var result = projectRepository.getById(id(mock));

        assertThat(result.getName()).isEqualTo("Mapa escolar");
        assertThat(result.getDescription()).isEqualTo("Vestibulum suscipit accumsan viverra.");
    }

    @Test
    void update() throws Exception {
        final var project = save(createProject().build());

        final var command = new ProjectUpdateCommand();
        command.setName("Portal da transparência");
        command.setDescription("Nunc felis neque, ornare a efficitur ac, efficitur vitae risus.");

        final var mock = mockMvc.perform(put("/api/projects/{id}", project.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body(command)))
                .andExpect(status().isOk());

        assertThat(headerMessage(mock)).isEqualTo("project.updated");

        final var result = projectRepository.getById(id(mock));

        assertThat(result.getId()).isEqualTo(project.getId());
        assertThat(result.getName()).isEqualTo("Portal da transparência");
        assertThat(result.getDescription()).isEqualTo("Nunc felis neque, ornare a efficitur ac, efficitur vitae risus.");
    }

    @Test
    void destroy() throws Exception {
        final var project = save(createProject().build());

        final var mock = mockMvc.perform(delete("/api/projects/{id}", project.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        assertThat(headerMessage(mock)).isEqualTo("project.deleted");

        final var result = projectRepository.findById(project.getId());

        assertThat(result).isEmpty();
    }


}