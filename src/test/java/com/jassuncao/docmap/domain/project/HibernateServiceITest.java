package com.jassuncao.docmap.domain.project;

import com.jassuncao.docmap.IntegrationTests;
import com.jassuncao.docmap.domain.attribute.TypeData;
import com.jassuncao.docmap.domain.entity.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.jassuncao.docmap.domain.attribute.AttributeTestData.createAttribute;
import static com.jassuncao.docmap.domain.entity.EntityTestData.createEntity;
import static com.jassuncao.docmap.domain.project.ProjectTestData.createProject;
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
        entityTo = save(createEntity().projectId(project.getId())
                .alias("avaliacao")
                .name("Avaliação")
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut libero justo, ultrices non urna id, elementum ultrices justo. Curabitur molestie velit odio. In ullamcorper vitae arcu vel rutrum. Sed quam metus, fermentum ac leo eu, fringilla rhoncus urna.")
                .build());
        entityFrom = save(createEntity().projectId(project.getId())
                .alias("pessoa")
                .name("Pessoa")
                .build());
        save(createAttribute().entityId(entityTo.getId()).alias("nome atributo").name("consectetur adipiscing elit.").type(TypeData.Text).length("1").required(true).uniqueConstraint(true).description("Lorem ipsum dolor sit amet").buildEntity());
    }

    @Test
    void buildHibernate() throws Exception {

        final Object result = hibernateService.process(project);

        assertThat(result).isNotNull();
    }
}