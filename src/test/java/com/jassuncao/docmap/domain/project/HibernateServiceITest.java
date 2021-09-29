package com.jassuncao.docmap.domain.project;

import com.jassuncao.docmap.IntegrationTests;
import com.jassuncao.docmap.domain.attribute.TypeData;
import com.jassuncao.docmap.domain.entity.Entity;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.net.URI;
import java.net.URL;

import static com.jassuncao.docmap.domain.attribute.AttributeTestData.createAttribute;
import static com.jassuncao.docmap.domain.entity.EntityTestData.createEntity;
import static com.jassuncao.docmap.domain.project.ProjectTestData.createProject;
import static com.jassuncao.docmap.domain.relationship.RelationshipTestData.createRelationship;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 15/09/2021
 */
class HibernateServiceITest extends IntegrationTests {

    @Autowired
    private HibernateService hibernateService;

    private Project project;
    private Entity entityTo;
    private Entity entityFrom;

    @BeforeEach
    void setup() throws Exception {
        project = save(createProject().build());
        entityFrom = save(createEntity().projectId(project.getId())
                .alias("avaliacao")
                .name("Avaliação")
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut libero justo, ultrices non urna id, elementum ultrices justo. Curabitur molestie velit odio. In ullamcorper vitae arcu vel rutrum. Sed quam metus, fermentum ac leo eu, fringilla rhoncus urna.")
                .build());
        entityTo = save(createEntity().projectId(project.getId())
                .alias("pessoa")
                .name("Pessoa")
                .build());
        save(createAttribute().entityId(entityFrom.getId()).alias("attrText").type(TypeData.Text).length("200")
                .required(true).uniqueConstraint(true).buildEntity());
        save(createAttribute().entityId(entityFrom.getId()).alias("attrLong").type(TypeData.Long).required(true).buildEntity());
        save(createAttribute().entityId(entityFrom.getId()).alias("attrInteger").type(TypeData.Integer).required(true).buildEntity());
        save(createAttribute().entityId(entityFrom.getId()).alias("attrNumber").type(TypeData.Number).length("5,4").required(true).buildEntity());
        save(createAttribute().entityId(entityFrom.getId()).alias("attrDatetime").type(TypeData.Datetime).buildEntity());

        save(createAttribute().entityId(entityTo.getId()).alias("attrDate").type(TypeData.Date)
                .required(true).uniqueConstraint(true).buildEntity());
        save(createAttribute().entityId(entityTo.getId()).alias("attrTimestamp").type(TypeData.Timestamp).buildEntity());
        save(createAttribute().entityId(entityTo.getId()).alias("attrBoolean").type(TypeData.Boolean).buildEntity());
        save(createAttribute().entityId(entityTo.getId()).alias("attrBinary").type(TypeData.Binary).buildEntity());
    }

    @Test
    void buildHibernate() throws Exception {

        final var result = hibernateService.process(project);

        final String avaliacao = TemplateUtils.getTemplateFile("Avaliacao.java");
        final String pessoa = TemplateUtils.getTemplateFile("Pessoa.java");

        assertThat(result.get(0)).isEqualTo(avaliacao);
        assertThat(result.get(1)).isEqualTo(pessoa);
    }

    @Test
    void buildHibernate__WithOneToOne() throws Exception {
        save(createRelationship().entityFrom(entityFrom).entityTo(entityTo).alias("avaliacao_pessoa")
                .cardinality("0:1").build());
        save(createRelationship().entityFrom(entityFrom).roleFrom("alternativa").entityTo(entityFrom).roleTo("Principal")
                .alias("avaliacao_avaliacao").cardinality("1:1").build());

        final var result = hibernateService.process(project);

        final String oneToOne = TemplateUtils.getTemplateFile("OneToOne.java");
        final String pessoa = TemplateUtils.getTemplateFile("Pessoa.java");

        assertThat(result.get(0)).isEqualTo(oneToOne);
        assertThat(result.get(1)).isEqualTo(pessoa);
    }

    @Test
    void buildHibernate__WithOneToMany() throws Exception {
        save(createRelationship().entityFrom(entityFrom).entityTo(entityTo).alias("avaliacao_pessoa")
                .cardinality("0:2").build());
        save(createRelationship().entityFrom(entityFrom).roleFrom("alternativa").entityTo(entityFrom).roleTo("Principal")
                .alias("avaliacao_avaliacao").cardinality("1:*").build());

        final var result = hibernateService.process(project);

        final String oneToMany = TemplateUtils.getTemplateFile("OneToMany.java");
        final String pessoa = TemplateUtils.getTemplateFile("Pessoa.java");

        assertThat(result.get(0)).isEqualTo(oneToMany);
        assertThat(result.get(1)).isEqualTo(pessoa);
    }

    @Test
    void buildHibernate__WithManyToOne() throws Exception {
        save(createRelationship().entityFrom(entityFrom).entityTo(entityTo).alias("avaliacao_pessoa")
                .cardinality("5:1").build());
        save(createRelationship().entityFrom(entityFrom).roleFrom("alternativa").entityTo(entityFrom).roleTo("Principal")
                .uniqueConstraint(true).alias("avaliacao_avaliacao").cardinality("*:1").build());

        final var result = hibernateService.process(project);

        final String manyToOne = TemplateUtils.getTemplateFile("ManyToOne.java");
        final String pessoa = TemplateUtils.getTemplateFile("Pessoa.java");

        assertThat(result.get(0)).isEqualTo(manyToOne);
        assertThat(result.get(1)).isEqualTo(pessoa);
    }

    @Test
    void buildHibernate__WithManyToMany() throws Exception {
        save(createRelationship().entityFrom(entityFrom).entityTo(entityTo).alias("avaliacao_pessoa")
                .cardinality("5:5").build());
        save(createRelationship().entityFrom(entityFrom).roleFrom("alternativa").entityTo(entityFrom).roleTo("Principal")
                .uniqueConstraint(true).alias("avaliacao_avaliacao").cardinality("*:*").build());

        final var result = hibernateService.process(project);

        final String manyToMany = TemplateUtils.getTemplateFile("ManyToMany.java");
        final String pessoa = TemplateUtils.getTemplateFile("Pessoa.java");

        assertThat(result.get(0)).isEqualTo(manyToMany);
        assertThat(result.get(1)).isEqualTo(pessoa);
    }
}