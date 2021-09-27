package com.jassuncao.docmap.domain.attribute;

import java.util.Optional;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 20/09/2021
 */
public interface GetterSetters {

    String getName();

    String getAlias();

    String type();

    boolean isRequired();

    boolean isUniqueConstraint();

    default Optional<String> getDescription(){
        return Optional.empty();
    };

}
