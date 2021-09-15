package com.jassuncao.docmap.domain.project;

import com.jassuncao.docmap.domain.attribute.Attribute;
import com.jassuncao.docmap.domain.attribute.TypeData;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 15/09/2021
 */
public class HibernateAttributeData {

    private final List<HibernateAttributeModel> models;

    private HibernateAttributeData(List<? extends Attribute> attributes) {
        models = attributes.stream().map(HibernateAttributeModel::new).collect(Collectors.toList());
    }

    public List<Object> getAttributes() {
        return List.of();
    }

    public List<String> getImports() {
        return List.of();
    }

    public List<Object> getGetSet() {
        return List.of();
    }

    public static HibernateAttributeData process(List<? extends Attribute> attributes) {
        return new HibernateAttributeData(attributes);
    }

    public static class HibernateAttributeModel {

        private final String name;
        private final String type;
        private final String column;
        private final List<HibernateImport> options;
        private Set<String> imports = new HashSet<>();
        private String getSets;

        HibernateAttributeModel(Attribute attribute) {
            name = Normalize.fieldForm(attribute.getAlias());
            type = type(attribute);
            column = column(attribute);
            options = options(attribute);
        }

        private String type(Attribute attribute) {
            imports.add(attribute.getType().getPack().getPackage());
            return attribute.getType().getType();
        }

        private String column(Attribute attribute) {
            final HibernateColumn column = HibernateColumn.valueOf(attribute);
            final List<String> columns = new LinkedList<>();
            column.ifName().ifPresent(columns::add);
            column.ifUnique().ifPresent(columns::add);
            column.ifNullable().ifPresent(columns::add);
            column.ifLength().ifPresent(columns::add);
            column.ifPrecision().ifPresent(columns::add);
            column.ifScale().ifPresent(columns::add);
            if (!columns.isEmpty()) {
                imports.add(HibernateImport.Column.getPackage());
                return String.format("@Column(%s)", String.join(", ", columns));
            }
            return StringUtils.EMPTY;
        }

        private List<HibernateImport> options(Attribute attribute) {
            final List<HibernateImport> options = new LinkedList<>();
            if (attribute.isRequired() && attribute.isUniqueConstraint()) {
                options.add(HibernateImport.Id);
            }
            if (TypeData.Binary.equals(attribute.getType())) {
                options.add(HibernateImport.Lob);
            }
            return options;
        }

    }
}
