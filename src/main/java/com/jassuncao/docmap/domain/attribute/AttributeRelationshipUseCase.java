package com.jassuncao.docmap.domain.attribute;


import com.jassuncao.docmap.domain.attribute.AttributeData.AttributeDataRelationship;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

@Service
public class AttributeRelationshipUseCase {


    private final AttributeRelationshipRepository attributeRelationshipRepository;

    public AttributeRelationshipUseCase(AttributeRelationshipRepository attributeRelationshipRepository) {
        this.attributeRelationshipRepository = attributeRelationshipRepository;
    }

    public AttributeRelationship create(AttributeDataRelationship data) {
        final AttributeRelationship attribute = new AttributeRelationship(data);
        return attributeRelationshipRepository.save(attribute);
    }

    public AttributeRelationship update(UUID id, AttributeData data) {
        final AttributeRelationship attribute = attributeRelationshipRepository.getById(id);
        attribute.update(data);
        return attributeRelationshipRepository.save(attribute);
    }

    public void delete(UUID id) {
        attributeRelationshipRepository.findById(id).ifPresent(attributeRelationshipRepository::delete);
    }
}
