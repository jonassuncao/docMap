package com.jassuncao.docmap.domain.project;

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
        if (relationship.isOneToOne()) {
            data.resolveOneToOne(project, entityRepository.getById(relationship.getEntityFromId()));
        }
        if (relationship.isOneToMany()) {
            data.resolveOneToMany(project, entityRepository.getById(relationship.getEntityFromId()));
        }
//        if (relationship.isManyToOne()) {
//            column = "@ManyToOne"; //Class
//        }
//        if (relationship.isManyToMany()) {
//            column = "@ManyToMany"; //Collection
//        }
        return data;
    }


}
