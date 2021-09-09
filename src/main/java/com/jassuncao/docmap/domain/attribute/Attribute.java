package com.jassuncao.docmap.domain.attribute;

import com.jassuncao.docmap.domain.Identifier;

import javax.persistence.*;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

@Entity
@DiscriminatorColumn(name = "attribute_type", discriminatorType = DiscriminatorType.STRING)
public class Attribute extends Identifier {

    private String alias;
    private String name;
    @Enumerated(EnumType.STRING)
    private TypeData type;
    private String length;
    private boolean required;
    private boolean uniqueConstraint;
    private String cardinality;
    private String description;

    Attribute() {
        super();
    }

    Attribute(AttributeData data) {
        update(data);
        inicialize();
    }

    void update(AttributeData data) {
        setAlias(data.getAlias());
        setName(data.getName());
        setType(data.getType());
        setLength(data.getLength());
        setRequired(data.isRequired());
        setUniqueConstraint(data.isUnique());
        setCardinality(data.getCardinality());
        setDescription(data.getDescription());
    }

    public String getAlias() {
        return alias;
    }

    private void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public TypeData getType() {
        return type;
    }

    private void setType(TypeData type) {
        this.type = type;
    }

    public String getLength() {
        return length;
    }

    private void setLength(String length) {
        this.length = length;
    }

    public boolean isRequired() {
        return required;
    }

    private void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isUniqueConstraint() {
        return uniqueConstraint;
    }

    private void setUniqueConstraint(boolean uniqueConstraint) {
        this.uniqueConstraint = uniqueConstraint;
    }

    public String getCardinality() {
        return cardinality;
    }

    public void setCardinality(String cardinality) {
        this.cardinality = cardinality;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }
}
