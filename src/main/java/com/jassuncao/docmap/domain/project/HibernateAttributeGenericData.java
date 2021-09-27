package com.jassuncao.docmap.domain.project;

import com.jassuncao.docmap.domain.attribute.GetterSetters;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 20/09/2021
 */
abstract class HibernateAttributeGenericData {

    private final List<String> description;

    HibernateAttributeGenericData(GetterSetters getterSetters) {
        description = description(getterSetters);
    }


    private List<String> description(GetterSetters getterSetters) {
        final var value = new StringBuilder()
                .append(getterSetters.getName())
                .append(StringUtils.LF)
                .append(getterSetters.getDescription().orElse(StringUtils.EMPTY))
                .toString();
        return Normalize.splitPreserveTokens(value, 120);
    }

    public String getsSets(GetterSetters getterSetters) {
        return getsSets(getterSetters, "getSetters.java");
    }

    public String getsSets(GetterSetters getterSetters, String template) {
        final Map<String, Object> params = new HashMap<>();
        params.put("set", setParams());
        params.put("get", getParams(getterSetters));
        return StringUtils.trim(TemplateUtils.processFile(template, params));
    }

    private Map<String, Object> setParams() {
        final Map<String, Object> params = new HashMap<>();
        params.put("modifier", "protected");
        params.put("return", "void");
        params.put("name", Normalize.classForm(getAlias()));
        params.put("typeVariable", getType());
        params.put("variable", Normalize.fieldForm(getAlias()));
        return params;
    }

    private Map<String, Object> getParams(GetterSetters getterSetters) {
        final Map<String, Object> params = new HashMap<>();
        params.put("modifier", "protected");
        params.put("name", Normalize.classForm(getAlias()));
        params.put("typeVariable", getType());
        params.put("required", getterSetters.isRequired());
        params.put("variable", Normalize.fieldForm(getAlias()));
        return params;
    }


    public List<String> getDescription() {
        return description;
    }

    public abstract String getGetSets();

    public abstract String getAlias();

    public abstract String getName();

    public abstract String getType();

    public abstract String getColumn();

    public abstract List<String> getOptions();


}
