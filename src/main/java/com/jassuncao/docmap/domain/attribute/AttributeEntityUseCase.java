package com.jassuncao.docmap.domain.attribute;


import com.jassuncao.docmap.domain.attribute.AttributeData.AttributeDataEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

@Service
public class AttributeEntityUseCase {

    private final AttributeEntityRepository attributeEntityRepository;

    public AttributeEntityUseCase(AttributeEntityRepository attributeEntityRepository) {
        this.attributeEntityRepository = attributeEntityRepository;
    }

    public AttributeEntity create(AttributeDataEntity data) {
        final AttributeEntity attribute = new AttributeEntity(data);
        return attributeEntityRepository.save(attribute);
    }

    public AttributeEntity update(UUID id, AttributeData data) {
        final AttributeEntity attribute = attributeEntityRepository.getById(id);
        attribute.update(data);
        return attributeEntityRepository.save(attribute);
    }

    public void delete(UUID id) {
        attributeEntityRepository.findById(id).ifPresent(attributeEntityRepository::delete);
    }
}
