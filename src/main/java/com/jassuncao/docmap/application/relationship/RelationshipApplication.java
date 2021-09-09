package com.jassuncao.docmap.application.relationship;

import com.jassuncao.docmap.domain.relationship.Relationship;
import com.jassuncao.docmap.domain.relationship.RelationshipData;
import com.jassuncao.docmap.domain.relationship.RelationshipUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
@Component
@Transactional
public class RelationshipApplication {

    @Autowired
    private RelationshipUseCase relationshipUseCase;

    public Relationship when(RelationshipCreateCommand command) {
        return relationshipUseCase.create(helper(command));
    }

    public Relationship when(RelationshipUpdateCommand command) {
        return relationshipUseCase.update(command.getId(), helper(command));
    }

    public void when(RelationshipDeleteCommand command) {
        relationshipUseCase.delete(command.getId());
    }

    private RelationshipData helper(RelationshipCreateCommand command) {
        final RelationshipData data = new RelationshipData();
        data.setAlias(command.getAlias());
        data.setName(command.getName());
        data.setEntityTo(command.getEntityTo());
        command.getRoleTo().ifPresent(data::setRoleTo);
        data.setEntityFrom(command.getEntityFrom());
        command.getRoleFrom().ifPresent(data::setRoleFrom);
        data.setRequired(command.isRequired());
        data.setUniqueConstraint(command.isUniqueConstraint());
        data.setCardinality(command.getCardinality());
        return data;
    }
}
