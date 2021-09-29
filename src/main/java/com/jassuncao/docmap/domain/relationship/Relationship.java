package com.jassuncao.docmap.domain.relationship;

import com.jassuncao.docmap.domain.Identifier;
import com.jassuncao.docmap.domain.attribute.GetterSetters;
import com.jassuncao.docmap.domain.project.Normalize;

import javax.persistence.Entity;
import java.util.Optional;
import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

@Entity
public class Relationship extends Identifier implements GetterSetters, CardinalityCalculator {

    private String alias;
    private String name;
    private UUID entityToId;
    private String roleTo;
    private UUID entityFromId;
    private String roleFrom;
    private boolean uniqueConstraint;
    private String cardinality;

    Relationship() {
        super();
    }

    Relationship(RelationshipData data) {
        update(data);
        inicialize();
    }

    void update(RelationshipData data) {
        setAlias(data.getAlias());
        setName(data.getName());
        setEntityToId(data.getEntityToId());
        data.getRoleTo().ifPresentOrElse(this::setRoleTo, () -> roleTo = null);
        setEntityFromId(data.getEntityFromId());
        data.getRoleFrom().ifPresentOrElse(this::setRoleFrom, () -> roleFrom = null);
        setUniqueConstraint(data.isUniqueConstraint());
        setCardinality(data.getCardinality());
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String type() {
        return Normalize.classForm(alias);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getEntityToId() {
        return entityToId;
    }

    public void setEntityToId(UUID entityTo) {
        this.entityToId = entityTo;
    }

    public Optional<String> getRoleTo() {
        return Optional.ofNullable(roleTo);
    }

    public void setRoleTo(String roleTo) {
        this.roleTo = roleTo;
    }

    public UUID getEntityFromId() {
        return entityFromId;
    }

    public void setEntityFromId(UUID entityFrom) {
        this.entityFromId = entityFrom;
    }

    public Optional<String> getRoleFrom() {
        return Optional.ofNullable(roleFrom);
    }

    public void setRoleFrom(String roleFrom) {
        this.roleFrom = roleFrom;
    }

    public boolean isRequired() {
        return !isOneFromOptional();
    }

    public boolean isUniqueConstraint() {
        return uniqueConstraint;
    }

    public void setUniqueConstraint(boolean uniqueConstraint) {
        this.uniqueConstraint = uniqueConstraint;
    }

    public String getCardinality() {
        return cardinality;
    }

    public void setCardinality(String cardinality) {
        this.cardinality = cardinality;
    }
}
