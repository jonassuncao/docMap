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
    private UUID entityTo;
    private String roleTo;
    private UUID entityFrom;
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

    public UUID getEntityTo() {
        return entityTo;
    }

    public void setEntityTo(UUID entityTo) {
        this.entityTo = entityTo;
    }

    public Optional<String> getRoleTo() {
        return Optional.ofNullable(roleTo);
    }

    public void setRoleTo(String roleTo) {
        this.roleTo = roleTo;
    }

    public UUID getEntityFrom() {
        return entityFrom;
    }

    public void setEntityFrom(UUID entityFrom) {
        this.entityFrom = entityFrom;
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
