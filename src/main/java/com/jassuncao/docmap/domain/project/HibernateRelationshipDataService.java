package com.jassuncao.docmap.domain.project;

import com.jassuncao.docmap.domain.entity.Entity;
import com.jassuncao.docmap.domain.entity.EntityRepository;
import com.jassuncao.docmap.domain.relationship.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 15/09/2021
 */
@Component
public class HibernateRelationshipDataService {

    @Autowired
    private EntityRepository entityRepository;

    public HibernateRelationshipData buildRelationship(Project project, Relationship relationship) {
        final HibernateRelationshipData data = new HibernateRelationshipData(relationship);
        final Entity entityTo = entityRepository.getById(relationship.getEntityToId());
        final Entity entityFrom = entityRepository.getById(relationship.getEntityFromId());
        if (relationship.isOneToOne()) {
            data.resolveOneToOne(project, entityTo);
        } else if (relationship.isOneToMany()) {
            data.resolveOneToMany(project, entityFrom, entityTo);
        } else if (relationship.isManyToOne()) {
            data.resolveManyToOne(project, entityTo);
        } else if (relationship.isManyToMany()) {
            data.resolveManyToMany(project, entityFrom, entityTo);
        }
        return data;
    }


}
