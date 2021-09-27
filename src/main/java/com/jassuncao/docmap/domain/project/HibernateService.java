package com.jassuncao.docmap.domain.project;

import com.jassuncao.docmap.domain.attribute.AttributeEntity;
import com.jassuncao.docmap.domain.attribute.AttributeEntityRepository;
import com.jassuncao.docmap.domain.entity.Entity;
import com.jassuncao.docmap.domain.entity.EntityRepository;
import com.jassuncao.docmap.domain.relationship.Relationship;
import com.jassuncao.docmap.domain.relationship.RelationshipRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 15/09/2021
 */
@Component
public class HibernateService {

    private final EntityRepository entityRepository;
    private final HibernateDataService hibernateDataService;
    private final RelationshipRepository relationshipRepository;
    private final AttributeEntityRepository attributeEntityRepository;

    public HibernateService(EntityRepository entityRepository,
                            HibernateDataService hibernateDataService,
                            RelationshipRepository relationshipRepository,
                            AttributeEntityRepository attributeEntityRepository) {
        this.entityRepository = entityRepository;
        this.hibernateDataService = hibernateDataService;
        this.relationshipRepository = relationshipRepository;
        this.attributeEntityRepository = attributeEntityRepository;
    }


    public List<String> process(Project project) {
        return entityRepository.findByProjectId(project.getId()).stream()
                .map(hibernateData(project))
                .map(params -> TemplateUtils.processFile("entity.java", params))
                .collect(Collectors.toList());
    }

    private Function<Entity, Map<String, Object>> hibernateData(Project project) {
        return entity -> {
            final List<AttributeEntity> attributes = attributeEntityRepository.findByEntityId(entity.getId());
            final List<Relationship> relationships = relationshipRepository.findByEntityToId(entity.getId());
            return hibernateDataService.getParams(project, entity, relationships, attributes);
        };
    }
}
