package com.jassuncao.docmap.web.attribute;

import com.jassuncao.docmap.IntegrationTests;
import com.jassuncao.docmap.application.attribute.AttributeCreateCommand.AttributeCreateEntityCommand;
import com.jassuncao.docmap.application.attribute.AttributeCreateCommand.AttributeCreateRelationshipCommand;
import com.jassuncao.docmap.application.attribute.AttributeUpdateCommand.AttributeUpdateEntityCommand;
import com.jassuncao.docmap.application.attribute.AttributeUpdateCommand.AttributeUpdateRelationshipCommand;
import com.jassuncao.docmap.domain.attribute.AttributeEntityRepository;
import com.jassuncao.docmap.domain.attribute.AttributeRelationshipRepository;
import com.jassuncao.docmap.domain.attribute.TypeData;
import com.jassuncao.docmap.domain.entity.Entity;
import com.jassuncao.docmap.domain.project.Project;
import com.jassuncao.docmap.domain.relationship.Relationship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static com.jassuncao.docmap.domain.attribute.AttributeTestData.createAttribute;
import static com.jassuncao.docmap.domain.entity.EntityTestData.createEntity;
import static com.jassuncao.docmap.domain.project.ProjectTestData.createProject;
import static com.jassuncao.docmap.domain.relationship.RelationshipTestData.createRelationship;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 10/09/2021
 */
class AttributeResourceITest extends IntegrationTests {

    @Autowired
    private AttributeEntityRepository attributeEntityRepository;
    @Autowired
    private AttributeRelationshipRepository attributeRelationshipRepository;

    private Project project;
    private Entity entityTo;
    private Entity entityFrom;
    private Relationship relationship;

    @BeforeEach
    void setup() throws Exception {
        project = save(createProject().build());
        entityTo = save(createEntity().projectId(project.getId()).name("Avaliacao").build());
        entityFrom = save(createEntity().projectId(project.getId()).name("Pessoa").build());
        relationship = save(createRelationship().entityTo(entityTo.getId()).entityFrom(entityFrom.getId()).build());
    }

    @Test
    void createAttributeEntity() throws Exception {
        final var command = new AttributeCreateEntityCommand();
        command.setEntityId(entityTo.getId());
        command.setAlias("momento_criacao");
        command.setName("Momento da cria????o da avalia????o");
        command.setType(TypeData.Datetime);
        command.setRequired(true);
        command.setCardinality("1:*");

        final var mock = mockMvc().perform(post("/api/attributes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body(command)))
                .andExpect(status().isCreated());

        assertThat(headerMessage(mock)).isEqualTo("attribute.created");

        final var result = attributeEntityRepository.getById(id(mock));

        assertThat(result.getEntityId()).isEqualTo(entityTo.getId());
        assertThat(result.getAlias()).isEqualTo("momento_criacao");
        assertThat(result.getName()).isEqualTo("Momento da cria????o da avalia????o");
        assertThat(result.getType()).isEqualTo(TypeData.Datetime);
        assertThat(result.getLength()).isEmpty();
        assertThat(result.isRequired()).isTrue();
        assertThat(result.isUniqueConstraint()).isFalse();
        assertThat(result.getCardinality()).isEqualTo("1:*");
        assertThat(result.getDescription()).isEmpty();
    }

    @Test
    void createAttributeRelationship() throws Exception {
        final var command = new AttributeCreateRelationshipCommand();
        command.setRelationshipId(relationship.getId());
        command.setAlias("momento_criacao");
        command.setName("Momento da cria????o da avalia????o");
        command.setType(TypeData.Datetime);
        command.setRequired(true);
        command.setCardinality("1:*");

        final var mock = mockMvc().perform(post("/api/attributes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body(command)))
                .andExpect(status().isCreated());

        assertThat(headerMessage(mock)).isEqualTo("attribute.created");

        final var result = attributeRelationshipRepository.getById(id(mock));

        assertThat(result.getRelationshipId()).isEqualTo(relationship.getId());
        assertThat(result.getAlias()).isEqualTo("momento_criacao");
        assertThat(result.getName()).isEqualTo("Momento da cria????o da avalia????o");
        assertThat(result.getType()).isEqualTo(TypeData.Datetime);
        assertThat(result.getLength()).isEmpty();
        assertThat(result.isRequired()).isTrue();
        assertThat(result.isUniqueConstraint()).isFalse();
        assertThat(result.getCardinality()).isEqualTo("1:*");
        assertThat(result.getDescription()).isEmpty();
    }

    @Test
    void updateAttributeEntity() throws Exception {
        final var attribute = save(createAttribute().entityId(entityTo.getId()).buildEntity());

        final var command = new AttributeUpdateEntityCommand();
        command.setEntityId(entityTo.getId());
        command.setAlias("e_reclamacao");
        command.setName("?? reclama????o");
        command.setType(TypeData.Boolean);
        command.setCardinality("1:1");
        command.setDescription("Verdadeiro indica que a avalia????o ?? uma reclama????o.");

        final var mock = mockMvc().perform(put("/api/attributes/{id}", attribute.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body(command)))
                .andExpect(status().isOk());

        assertThat(headerMessage(mock)).isEqualTo("attribute.updated");

        final var result = attributeEntityRepository.getById(id(mock));

        assertThat(result.getEntityId()).isEqualTo(entityTo.getId());
        assertThat(result.getAlias()).isEqualTo("e_reclamacao");
        assertThat(result.getName()).isEqualTo("?? reclama????o");
        assertThat(result.getType()).isEqualTo(TypeData.Boolean);
        assertThat(result.getLength()).isEmpty();
        assertThat(result.isRequired()).isFalse();
        assertThat(result.isUniqueConstraint()).isFalse();
        assertThat(result.getCardinality()).isEqualTo("1:1");
        assertThat(result.getDescription()).contains("Verdadeiro indica que a avalia????o ?? uma reclama????o.");
    }

    @Test
    void updateAttributeRelationship() throws Exception {
        final var attribute = save(createAttribute().relationshipId(relationship.getId()).buildRelationship());

        final var command = new AttributeUpdateRelationshipCommand();
        command.setRelationshipId(relationship.getId());
        command.setAlias("e_reclamacao");
        command.setName("?? reclama????o");
        command.setType(TypeData.Boolean);
        command.setCardinality("1:1");
        command.setDescription("Verdadeiro indica que a avalia????o ?? uma reclama????o.");

        final var mock = mockMvc().perform(put("/api/attributes/{id}", attribute.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body(command)))
                .andExpect(status().isOk());

        assertThat(headerMessage(mock)).isEqualTo("attribute.updated");

        final var result = attributeRelationshipRepository.getById(id(mock));

        assertThat(result.getRelationshipId()).isEqualTo(relationship.getId());
        assertThat(result.getAlias()).isEqualTo("e_reclamacao");
        assertThat(result.getName()).isEqualTo("?? reclama????o");
        assertThat(result.getType()).isEqualTo(TypeData.Boolean);
        assertThat(result.getLength()).isEmpty();
        assertThat(result.isRequired()).isFalse();
        assertThat(result.isUniqueConstraint()).isFalse();
        assertThat(result.getCardinality()).isEqualTo("1:1");
        assertThat(result.getDescription()).contains("Verdadeiro indica que a avalia????o ?? uma reclama????o.");
    }

    @Test
    void destroyAttributeEntity() throws Exception {
        final var attribute = save(createAttribute().entityId(entityTo.getId()).buildEntity());

        final var mock = mockMvc().perform(delete("/api/attributes/{id}", attribute.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        assertThat(headerMessage(mock)).isEqualTo("attribute.deleted");

        final var result = attributeEntityRepository.findById(attribute.getId());

        assertThat(result).isEmpty();
    }

    @Test
    void destroyAttributeRelationship() throws Exception {
        final var attribute = save(createAttribute().relationshipId(relationship.getId()).buildRelationship());

        final var mock = mockMvc().perform(delete("/api/attributes/{id}", attribute.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        assertThat(headerMessage(mock)).isEqualTo("attribute.deleted");

        final var result = attributeRelationshipRepository.findById(attribute.getId());

        assertThat(result).isEmpty();
    }
}