package com.jassuncao.docmap.domain.project;

import com.jassuncao.docmap.infra.I18nCommon;
import com.jassuncao.docmap.infra.ValidationException;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

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
            return StringUtils.remove(formatted, " ");
        }
        throw ValidationException.valueOf(I18nCommon.NORMALIZE_ERROR_PACKAGEFORM, value);
    }

    public static String classForm(String value) {
        if (StringUtils.isNotEmpty(value) && StringUtils.isAlphaSpace(CharUtils.toString(value.charAt(0)))) {
            String formatted = StringUtils.lowerCase(value);
            formatted = StringUtils.stripAccents(formatted);
            formatted = StringUtils.trim(formatted);
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
            String formatted = StringUtils.lowerCase(value);
            formatted = StringUtils.stripAccents(formatted);
            formatted = StringUtils.trim(formatted);
            return Arrays.stream(StringUtils.split(formatted, StringUtils.SPACE)).filter(StringUtils::isNoneEmpty)
                    .collect(Collectors.joining("_"));
        }
        throw ValidationException.valueOf(I18nCommon.NORMALIZE_ERROR_CLASSFORM, value);
    }

    public static List<String> splitPreserveTokens(String value, int maxLineLength) {
        if (Objects.nonNull(value)) {
            final StringTokenizer tokenizer = new StringTokenizer(value);
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
            return results;
        }
        throw ValidationException.valueOf(I18nCommon.NORMALIZE_ERROR_SPLITPRESERVETOKENS, value);
    }
}
