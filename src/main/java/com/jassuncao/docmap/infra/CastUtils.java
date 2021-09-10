package com.jassuncao.docmap.infra;

import java.util.Optional;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
public abstract class CastUtils {

    public static <T> Optional<T> ifCast(Object data, Class<T> target) {
        return Optional.ofNullable(target).filter(t -> t.isInstance(data)).map(t -> t.cast(data));
    }
}
