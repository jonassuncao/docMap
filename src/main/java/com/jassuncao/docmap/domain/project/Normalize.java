package com.jassuncao.docmap.domain.project;

import com.jassuncao.docmap.infra.I18nCommon;
import com.jassuncao.docmap.infra.ValidationException;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 15/09/2021
 */
public final class Normalize {

    public static String packageForm(String value) {
        if (StringUtils.isNotEmpty(value) && StringUtils.isAlphaSpace(CharUtils.toString(value.charAt(0)))) {
            String formatted = StringUtils.lowerCase(value);
            formatted = StringUtils.stripAccents(formatted);
            formatted = StringUtils.trim(formatted);
            formatted = formatted.replaceAll("[^a-zA-Z0-9]", StringUtils.EMPTY);
            return StringUtils.remove(formatted, StringUtils.SPACE);
        }
        throw ValidationException.valueOf(I18nCommon.NORMALIZE_ERROR_PACKAGEFORM, value);
    }

    public static String classForm(String value) {
        if (StringUtils.isNotEmpty(value) && StringUtils.isAlphaSpace(CharUtils.toString(value.charAt(0)))) {
            String formatted = StringUtils.stripAccents(value);
            formatted = StringUtils.trim(formatted);
            formatted = formatted.replaceAll("[^a-zA-Z0-9]", StringUtils.SPACE);
            return Arrays.stream(StringUtils.split(formatted, StringUtils.SPACE)).map(StringUtils::capitalize)
                    .collect(Collectors.joining(StringUtils.EMPTY));
        }
        throw ValidationException.valueOf(I18nCommon.NORMALIZE_ERROR_CLASSFORM, value);
    }

    public static String fieldForm(String value) {
        return StringUtils.uncapitalize(classForm(value));
    }

    public static String dataBaseForm(String value) {
        if (StringUtils.isNotEmpty(value) && StringUtils.isAlphaSpace(CharUtils.toString(value.charAt(0)))) {
            String formatted = value.replaceAll("([A-Z])", StringUtils.SPACE.concat("$1"));
            formatted = StringUtils.lowerCase(formatted);
            formatted = StringUtils.stripAccents(formatted);
            formatted = StringUtils.trim(formatted);
            return Arrays.stream(StringUtils.split(formatted, StringUtils.SPACE)).filter(StringUtils::isNoneEmpty)
                    .collect(Collectors.joining("_"));
        }
        throw ValidationException.valueOf(I18nCommon.NORMALIZE_ERROR_CLASSFORM, value);
    }

    public static List<String> splitPreserveTokens(String value, int maxLineLength) {
        if (Objects.nonNull(value)) {
            final List<String> result = Arrays.stream(StringUtils.split(value, StringUtils.LF)).map(element -> {
                final StringTokenizer tokenizer = new StringTokenizer(element);
                final List<String> results = new ArrayList<>();
                StringBuilder line = new StringBuilder();
                while (tokenizer.hasMoreTokens()) {
                    final String token = tokenizer.nextToken();
                    if (token.length() + line.length() > maxLineLength) {
                        results.add(StringUtils.trim(line.toString()));
                        line = new StringBuilder(StringUtils.EMPTY);
                    }
                    line.append(token);
                    line.append(StringUtils.SPACE);
                }
                results.add(StringUtils.trim(line.toString()));
                return results;
            }).reduce(new LinkedList<>(), (computed, next) -> {
                computed.add(StringUtils.EMPTY);
                computed.addAll(next);
                return computed;
            });
            if (!CollectionUtils.isEmpty(result)) {
                result.remove(0);
            }
            return result;

        }
        throw ValidationException.valueOf(I18nCommon.NORMALIZE_ERROR_SPLITPRESERVETOKENS, value);
    }

    public static String importForm(String project, String object) {
        return new StringBuilder()
                .append("com.")
                .append(Normalize.packageForm(project))
                .append(".")
                .append(Normalize.packageForm(object))
                .toString();
    }

}
