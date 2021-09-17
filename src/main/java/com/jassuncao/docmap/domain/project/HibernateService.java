package com.jassuncao.docmap.domain.project;

import com.jassuncao.docmap.domain.attribute.AttributeEntity;
import com.jassuncao.docmap.domain.attribute.AttributeEntityRepository;
import com.jassuncao.docmap.domain.attribute.AttributeRelationshipRepository;
import com.jassuncao.docmap.domain.entity.Entity;
import com.jassuncao.docmap.domain.entity.EntityRepository;
import com.jassuncao.docmap.domain.relationship.RelationshipRepository;
import org.springframework.stereotype.Component;

import java.util.List;
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

    public HibernateService(EntityRepository entityRepository,
                            RelationshipRepository relationshipRepository,
                            AttributeEntityRepository attributeEntityRepository,
                            AttributeRelationshipRepository attributeRelationshipRepository) {
        this.entityRepository = entityRepository;
        this.relationshipRepository = relationshipRepository;
        this.attributeEntityRepository = attributeEntityRepository;
        this.attributeRelationshipRepository = attributeRelationshipRepository;
    }

    public List<String> process(Project project) {
        return entityRepository.findByProjectId(project.getId()).stream()
                .map(hibernateData(project))
                .map(hibernateData -> TemplateUtils.processFile("entity.java", hibernateData.getParams()))
                .collect(Collectors.toList());
    }

    private Function<Entity, HibernateData> hibernateData(Project project) {
        return entity -> {
            final List<AttributeEntity> attributes = attributeEntityRepository.findByEntityId(entity.getId());
            return new HibernateData(project, entity, attributes);
        };
    }
}
