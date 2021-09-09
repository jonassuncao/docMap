package com.jassuncao.docmap.application.relationship;

import com.jassuncao.docmap.application.AbstractCommand;

import java.util.Optional;
import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
public class RelationshipCreateCommand extends AbstractCommand {

    private String alias;
    private String name;
    private UUID entityToId;
    private String roleTo;
    private UUID entityFromId;
    private String roleFrom;
    private boolean required;
    private boolean uniqueConstraint;
    private String cardinality;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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

    public void setEntityToId(UUID entityToId) {
        this.entityToId = entityToId;
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

    public void setEntityFromId(UUID entityFromId) {
        this.entityFromId = entityFromId;
    }

    public Optional<String> getRoleFrom() {
        return Optional.ofNullable(roleFrom);
    }

    public void setRoleFrom(String roleFrom) {
        this.roleFrom = roleFrom;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
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
