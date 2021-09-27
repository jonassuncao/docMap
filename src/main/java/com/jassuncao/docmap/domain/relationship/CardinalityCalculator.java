package com.jassuncao.docmap.domain.relationship;

import org.apache.commons.lang3.StringUtils;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 20/09/2021
 */
public interface CardinalityCalculator {

    String getCardinality();

    default boolean isOneTo() {
        final String to = StringUtils.substringBefore(getCardinality(), ":");
        return "1".equals(to) || isOneToOptional();
    }

    default boolean isOneToOptional() {
        final String to = StringUtils.substringBefore(getCardinality(), ":");
        return "0".equals(to);

    }

    default boolean isOneFrom() {
        final String from = StringUtils.substringAfter(getCardinality(), ":");
        return "1".equals(from) || isOneFromOptional();
    }

    default boolean isOneFromOptional() {
        final String from = StringUtils.substringAfter(getCardinality(), ":");
        return "0".equals(from);
    }

    default boolean isOneToOne() {
        return isOneTo() && isOneFrom();
    }

    default boolean isOneToMany() {
        return isOneTo() && !isOneFrom();
    }

    default boolean isManyToOne() {
        return !isOneFrom() && isOneTo();
    }

    default boolean isManyToMany() {
        return !(isOneTo() || isOneFrom());
    }
}
