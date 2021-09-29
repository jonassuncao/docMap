package com.jassuncao.docmap.domain.relationship;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Optional;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 20/09/2021
 */
public interface CardinalityCalculator {

    String getCardinality();

    default boolean isOneTo() {
        final String to = StringUtils.substringAfter(getCardinality(), ":");
        return "1".equals(to) || isOneToOptional();
    }

    default boolean isOneToOptional() {
        final String to = StringUtils.substringAfter(getCardinality(), ":");
        return "0".equals(to);
    }

    default Optional<Integer> toMaxCardinality() {
        final String to = StringUtils.substringAfter(getCardinality(), ":");
        if (NumberUtils.isCreatable(to)) {
            return Optional.of(Integer.parseInt(to));
        }
        return Optional.empty();
    }

    default boolean isOneFrom() {
        final String from = StringUtils.substringBefore(getCardinality(), ":");
        return "1".equals(from) || isOneFromOptional();
    }

    default boolean isOneFromOptional() {
        final String from = StringUtils.substringBefore(getCardinality(), ":");
        return "0".equals(from);
    }

    default Optional<Integer> fromMaxCardinality() {
        final String from = StringUtils.substringBefore(getCardinality(), ":");
        if (NumberUtils.isCreatable(from)) {
            return Optional.of(Integer.parseInt(from));
        }
        return Optional.empty();
    }

    default boolean isOneToOne() {
        return isOneFrom() && isOneTo();
    }

    default boolean isOneToMany() {
        return isOneFrom() && !isOneTo();
    }

    default boolean isManyToOne() {
        return !isOneFrom() && isOneTo();
    }

    default boolean isManyToMany() {
        return !(isOneTo() || isOneFrom());
    }
}
