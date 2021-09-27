package com.jassuncao.docmap.web.relationship;

import com.jassuncao.docmap.IntegrationTests;
import com.jassuncao.docmap.application.relationship.RelationshipCreateCommand;
import com.jassuncao.docmap.domain.entity.Entity;
import com.jassuncao.docmap.domain.project.Project;
import com.jassuncao.docmap.domain.relationship.RelationshipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static com.jassuncao.docmap.domain.entity.EntityTestData.createEntity;
import static com.jassuncao.docmap.domain.project.ProjectTestData.createProject;
import static com.jassuncao.docmap.domain.relationship.RelationshipTestData.createRelationship;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
class RelationshipResourceITest extends IntegrationTests {

    @Autowired
    private RelationshipRepository relationshipRepository;

    private Project project;
    private Entity entityTo;
    private Entity entityFrom;

    @BeforeEach
    void setup() throws Exception {
        project = save(createProject().build());
        entityTo = save(createEntity().projectId(project.getId()).name("Avaliacao").build());
        entityFrom = save(createEntity().projectId(project.getId()).name("Pessoa").build());
    }

    @Test
    void create() throws Exception {
        final var command = new RelationshipCreateCommand();
        command.setAlias("pessoa_avalia");
        command.setName("Pessoa realiza avaliação");
        command.setEntityFromId(entityFrom.getId());
        command.setEntityToId(entityTo.getId());
        command.setCardinality("1:*");

        final var mock = mockMvc().perform(post("/api/relationships")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body(command)))
                .andExpect(status().isCreated());

        assertThat(headerMessage(mock)).isEqualTo("relationship.created");

        final var result = relationshipRepository.getById(id(mock));

        assertThat(result.getAlias()).isEqualTo("pessoa_avalia");
        assertThat(result.getName()).isEqualTo("Pessoa realiza avaliação");
        assertThat(result.getEntityFromId()).isEqualTo(entityFrom.getId());
        assertThat(result.getEntityToId()).isEqualTo(entityTo.getId());
        assertThat(result.getCardinality()).isEqualTo("1:*");
        assertThat(result.getRoleTo()).isEmpty();
        assertThat(result.getRoleFrom()).isEmpty();
        assertThat(result.isRequired()).isTrue();
        assertThat(result.isUniqueConstraint()).isFalse();
    }

    @Test
    void update() throws Exception {
        final var relationship = save(createRelationship().entityFrom(entityFrom.getId())
                .entityTo(entityTo.getId()).build());

        final var command = new RelationshipCreateCommand();
        command.setAlias("pessoa_avaliacao");
        command.setName("Pessoa avalia");
        command.setEntityFromId(entityFrom.getId());
        command.setEntityToId(entityTo.getId());
        command.setRequired(true);
        command.setUniqueConstraint(true);
        command.setCardinality("*:*");

        final var mock = mockMvc().perform(put("/api/relationships/{id}", relationship.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body(command)))
                .andExpect(status().isOk());

        assertThat(headerMessage(mock)).isEqualTo("relationship.updated");

        final var result = relationshipRepository.getById(id(mock));

        assertThat(result.getAlias()).isEqualTo("pessoa_avaliacao");
        assertThat(result.getName()).isEqualTo("Pessoa avalia");
        assertThat(result.getEntityFromId()).isEqualTo(entityFrom.getId());
        assertThat(result.getEntityToId()).isEqualTo(entityTo.getId());
        assertThat(result.getCardinality()).isEqualTo("*:*");
        assertThat(result.getRoleTo()).isEmpty();
        assertThat(result.getRoleFrom()).isEmpty();
        assertThat(result.isRequired()).isTrue();
        assertThat(result.isUniqueConstraint()).isTrue();
    }

    @Test
    void destroy() throws Exception {
        final var relationship = save(createRelationship().entityFrom(entityFrom.getId())
                .entityTo(entityTo.getId()).build());

        final var mock = mockMvc().perform(delete("/api/relationships/{id}", relationship.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        assertThat(headerMessage(mock)).isEqualTo("relationship.deleted");

        final var result = relationshipRepository.findById(relationship.getId());

        assertThat(result).isEmpty();
    }

}