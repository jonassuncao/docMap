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
        return null;
    }

    public Relationship update(UUID id, RelationshipData data) {
        return null;
    }

    public Relationship delete(UUID id) {
        return null;
    }
}
