package com.jassuncao.docmap.domain.project;

import com.jassuncao.docmap.domain.attribute.Attribute;
import com.jassuncao.docmap.domain.attribute.TypeData;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 15/09/2021
 */
public class HibernateColumn {

    private final Attribute attribute;

    public HibernateColumn(Attribute attribute) {
        this.attribute = attribute;
    }

    public Optional<String> ifName() {
        if (StringUtils.isMixedCase(Normalize.fieldForm(attribute.getAlias()))) {
            return Optional.of(String.format("name=\"%s\"", Normalize.dataBaseForm(attribute.getAlias())));
        }
        return Optional.empty();
    }

    public Optional<String> ifUnique() {
        if (attribute.isUniqueConstraint()) {
            return Optional.of("unique=true");
        }
        return Optional.empty();
    }

    public Optional<String> ifNullable() {
        if (attribute.isRequired()) {
            return Optional.of("nullable=false");
        }
        return Optional.empty();
    }

    public Optional<String> ifLength() {
        if (TypeData.Text.equals(attribute.getType())) {
            return attribute.getLength().filter(StringUtils::isNumeric)
                    .map(length -> String.format("length=%s", length));
        }
        return Optional.empty();
    }

    public Optional<String> ifPrecision() {
        if (TypeData.Number.equals(attribute.getType())) {
            return attribute.getLength().map(length -> StringUtils.substringBefore(length, ","))
                    .filter(StringUtils::isNumeric)
                    .map(length -> String.format("precision=%s", length));
        }
        return Optional.empty();
    }

    public Optional<String> ifScale() {
        if (TypeData.Number.equals(attribute.getType())) {
            return attribute.getLength().map(length -> StringUtils.substringAfter(length, ","))
                    .filter(StringUtils::isNumeric)
                    .map(length -> String.format("scale=%s", length));
        }
        return Optional.empty();
    }

    public static HibernateColumn valueOf(Attribute attribute) {
        return new HibernateColumn(attribute);
    }
}
