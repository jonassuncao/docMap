package com.jassuncao.docmap.web.project;

import com.jassuncao.docmap.IntegrationTests;
import com.jassuncao.docmap.application.project.ProjectCreateCommand;
import com.jassuncao.docmap.domain.project.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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


}