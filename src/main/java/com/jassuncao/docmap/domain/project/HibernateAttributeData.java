package com.jassuncao.docmap.domain.project;

import com.jassuncao.docmap.domain.attribute.Attribute;
import com.jassuncao.docmap.domain.attribute.TypeData;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 15/09/2021
 */
public class HibernateAttributeData extends HibernateAttributeGenericData {

    private final String column;
    private final List<String> options;
    private final String type;
    private final String getSets;
    private final String name;

    HibernateAttributeData(Attribute attribute) {
        super(attribute);
        name = Normalize.fieldForm(attribute.getAlias());
        type = attribute.type();
        column = column(attribute);
        options = options(attribute);
        getSets = getsSets(attribute);
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

    @Override
    public String getGetSets() {
        return getSets;
    }

    @Override
    public String getAlias() {
        return name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getColumn() {
        return column;
    }

    @Override
    public List<String> getOptions() {
        return options;
    }
}
