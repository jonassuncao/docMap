package com.jassuncao.docmap.domain.relationship;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

@Service
public class RelationshipUseCase {

    private final RelationshipRepository relationshipRepository;

    public RelationshipUseCase(RelationshipRepository relationshipRepository) {
        this.relationshipRepository = relationshipRepository;
    }


    public Relationship create(RelationshipData data) {
        final Relationship relationship = new Relationship(data);
        return relationshipRepository.save(relationship);
    }

    public Relationship update(UUID id, RelationshipData data) {
        final Relationship relationship = relationshipRepository.getById(id);
        relationship.update(data);
        return relationshipRepository.save(relationship);
    }

    public void delete(UUID id) {
        final Relationship relationship = relationshipRepository.getById(id);
        relationshipRepository.delete(relationship);
    }
}
