package com.jassuncao.docmap.web.entity;

import com.jassuncao.docmap.IntegrationTests;
import com.jassuncao.docmap.application.entity.EntityCreateCommand;
import com.jassuncao.docmap.application.entity.EntityUpdateCommand;
import com.jassuncao.docmap.domain.entity.EntityRepository;
import com.jassuncao.docmap.domain.project.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static com.jassuncao.docmap.domain.entity.EntityTestData.createEntity;
import static com.jassuncao.docmap.domain.project.ProjectTestData.createProject;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
class EntityResourceITest extends IntegrationTests {

    @Autowired
    private EntityRepository entityRepository;

    private Project project;

    @BeforeEach
    void setup() throws Exception {
        project = save(createProject().build());
    }

    @Test
    void create() throws Exception {
        final var command = new EntityCreateCommand();
        command.setProjectId(project.getId());
        command.setAlias("analise_elemento");
        command.setName("Análise de Elemento");
        command.setDescription("Vestibulum suscipit accumsan viverra.");

        final var mock = mockMvc().perform(post("/api/entities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body(command)))
                .andExpect(status().isCreated());

        assertThat(headerMessage(mock)).isEqualTo("entity.created");

        final var result = entityRepository.getById(id(mock));

        assertThat(result.getProjectId()).isEqualTo(project.getId());
        assertThat(result.getAlias()).isEqualTo("analise_elemento");
        assertThat(result.getName()).isEqualTo("Análise de Elemento");
        assertThat(result.getDescription()).isEqualTo("Vestibulum suscipit accumsan viverra.");
        assertThat(result.getExtendId()).isEmpty();
    }

    @Test
    void update() throws Exception {
        final var entity = save(createEntity().projectId(project.getId()).build());

        final var command = new EntityUpdateCommand();
        command.setProjectId(project.getId());
        command.setAlias("avaliacao");
        command.setName("Avaliação");
        command.setDescription("Nunc felis neque, ornare a efficitur ac, efficitur vitae risus.");

        final var mock = mockMvc().perform(put("/api/entities/{id}", entity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body(command)))
                .andExpect(status().isOk());

        assertThat(headerMessage(mock)).isEqualTo("entity.updated");

        final var result = entityRepository.getById(id(mock));

        assertThat(result.getProjectId()).isEqualTo(project.getId());
        assertThat(result.getAlias()).isEqualTo("avaliacao");
        assertThat(result.getName()).isEqualTo("Avaliação");
        assertThat(result.getDescription()).isEqualTo("Nunc felis neque, ornare a efficitur ac, efficitur vitae risus.");
        assertThat(result.getExtendId()).isEmpty();
    }

    @Test
    void destroy() throws Exception {
        final var entity = save(createEntity().projectId(project.getId()).build());

        final var mock = mockMvc().perform(delete("/api/entities/{id}", entity.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        assertThat(headerMessage(mock)).isEqualTo("entity.deleted");

        final var result = entityRepository.findById(entity.getId());

        assertThat(result).isEmpty();
    }

}