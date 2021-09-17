package com.jassuncao.docmap.domain.project;

import com.jassuncao.docmap.domain.attribute.Attribute;
import com.jassuncao.docmap.domain.attribute.TypeData;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 15/09/2021
 */
public class HibernateAttributeData {

    private final String name;
    private final String type;
    private final String column;
    private final List<String> options;
    private final List<String> description;
    private String getSets;

    HibernateAttributeData(Attribute attribute) {
        name = Normalize.fieldForm(attribute.getAlias());
        type = type(attribute);
        column = column(attribute);
        options = options(attribute);
        description = description(attribute);
        getSets = getSets(attribute);
    }

    private List<String> description(Attribute attribute) {
        final var value = new StringBuilder()
                .append(attribute.getName())
                .append(StringUtils.LF)
                .append(attribute.getDescription().orElse(StringUtils.EMPTY))
                .toString();
        return Normalize.splitPreserveTokens(value, 120);
    }

    private String type(Attribute attribute) {
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
            return String.format("@Column(%s)", String.join(", ", columns));
        }
        return StringUtils.EMPTY;
    }

    private List<String> options(Attribute attribute) {
        final List<String> options = new LinkedList<>();
        if (attribute.isRequired() && attribute.isUniqueConstraint()) {
            options.add("@Id");
        }
        if (TypeData.Binary.equals(attribute.getType())) {
            options.add("@Lob");
        }
        return options;
    }

    private String getSets(Attribute attribute) {
        final Map<String, Object> params = new HashMap<>();
        params.put("set", setParams(attribute));
        params.put("get", getParams(attribute));
        return TemplateUtils.processFile("getSetters.java", params);
    }

    private Map<String, Object> setParams(Attribute attribute) {
        final Map<String, Object> params = new HashMap<>();
        params.put("modifier", "private");
        params.put("return", "void");
        params.put("name", Normalize.classForm(attribute.getAlias()));
        params.put("typeVariable", attribute.getType().getType());
        params.put("variable", Normalize.fieldForm(attribute.getAlias()));
        return params;
    }

    private Map<String, Object> getParams(Attribute attribute) {
        final Map<String, Object> params = new HashMap<>();
        params.put("modifier", "protected");
        params.put("name", Normalize.classForm(attribute.getAlias()));
        params.put("typeVariable", attribute.getType().getType());
        params.put("required", attribute.isRequired());
        params.put("variable", Normalize.fieldForm(attribute.getAlias()));
        return params;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getColumn() {
        return column;
    }

    public List<String> getOptions() {
        return options;
    }

    public List<String> getDescription() {
        return description;
    }

    public String getGetSets() {
        return getSets;
    }
}
