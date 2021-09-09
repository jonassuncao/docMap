package com.jassuncao.docmap.web.project;

import com.jassuncao.docmap.IntegrationTests;
import com.jassuncao.docmap.application.project.ProjectCreateCommand;
import com.jassuncao.docmap.domain.project.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


class ProjectResourceITest extends IntegrationTests {


    @Autowired
    private ProjectRepository projectRepository;

    @Test
    void create() throws Exception {
        final var command = new ProjectCreateCommand();
        command.setName(" Mapa escolar ");
        command.setDescription("Vestibulum suscipit accumsan viverra. Phasellus mattis nisi facilisis, malesuada purus ut, interdum nunc. Aenean accumsan enim quis lectus dictum lacinia.");

        mockMvc.perform(post("/api/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body(command)));

        final var result = projectRepository.getById(UUID.randomUUID());

        assertThat(result.getName()).isEqualTo("Mapa escolar");
        assertThat(result.getName()).isEqualTo("Vestibulum suscipit accumsan viverra. Phasellus mattis nisi facilisis, malesuada purus ut, interdum nunc. Aenean accumsan enim quis lectus dictum lacinia.");
    }


}