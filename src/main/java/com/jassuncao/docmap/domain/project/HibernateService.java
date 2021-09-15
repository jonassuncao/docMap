package com.jassuncao.docmap.domain.project;

import com.jassuncao.docmap.domain.attribute.AttributeEntity;
import com.jassuncao.docmap.domain.attribute.AttributeEntityRepository;
import com.jassuncao.docmap.domain.attribute.AttributeRelationshipRepository;
import com.jassuncao.docmap.domain.entity.Entity;
import com.jassuncao.docmap.domain.entity.EntityRepository;
import com.jassuncao.docmap.domain.relationship.RelationshipRepository;
import com.jassuncao.docmap.infra.InternalException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 15/09/2021
 */
@Component
public class HibernateService {

    private final EntityRepository entityRepository;
    private final RelationshipRepository relationshipRepository;
    private final AttributeEntityRepository attributeEntityRepository;
    private final AttributeRelationshipRepository attributeRelationshipRepository;
    private final Map<String, String> templates = new ConcurrentHashMap<>();
    private final Configuration cfg;

    public HibernateService(EntityRepository entityRepository,
                            RelationshipRepository relationshipRepository,
                            AttributeEntityRepository attributeEntityRepository,
                            AttributeRelationshipRepository attributeRelationshipRepository) {
        this.entityRepository = entityRepository;
        this.relationshipRepository = relationshipRepository;
        this.attributeEntityRepository = attributeEntityRepository;
        this.attributeRelationshipRepository = attributeRelationshipRepository;
        cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
        cfg.setLogTemplateExceptions(false);
    }

    public Object process(Project project) {
        return entityRepository.findByProjectId(project.getId()).stream().map(hibernateData(project)).map(this::processFile).collect(Collectors.toList());
    }

    private String processFile(HibernateData hibernateData) {
        final String entity = getFile("entity.java");
        try {
            Template template = new Template("entity.java", entity, cfg);
            try (StringWriter writer = new StringWriter()) {
                template.process(hibernateData.getParams(), writer);
                return writer.toString();
            }
        } catch (TemplateException | IOException ex) {
            throw new InternalException(ex);
        }
    }

    private String getFile(String path) {
        return templates.computeIfAbsent(path, this::loadFile);
    }

    private String loadFile(String path) {
        try {
            try (InputStream stream = safeFile(path)) {
                return IOUtils.toString(stream, StandardCharsets.UTF_8);
            }
        } catch (IOException ex) {
            throw new InternalException(ex);
        }
    }

    private InputStream safeFile(String value) throws IOException {
        String path = StringUtils.prependIfMissing(value, "/templates/");
        path = StringUtils.appendIfMissing(path, ".java");
        ClassPathResource resource = new ClassPathResource(path, getClass());
        return resource.getInputStream();
    }

    private Function<Entity, HibernateData> hibernateData(Project project) {
        return entity -> {
            final List<AttributeEntity> attributes = attributeEntityRepository.findByEntityId(entity.getId());
            return new HibernateData(project, entity, attributes);
        };
    }
}
