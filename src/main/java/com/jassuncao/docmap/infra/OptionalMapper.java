package com.jassuncao.docmap.infra;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
public class OptionalMapper<R> {

    private final Object value;
    private R result;

    public OptionalMapper(Object value) {
        this.value = value;
    }

    public <T> OptionalMapper<R> ifCast(Class<T> target, Function<T, R> transform) {
        if (result == null) {
            CastUtils.ifCast(value, target).map(transform).ifPresent(this::setResult);
        }
        return this;
    }

    private void setResult(R result) {
        this.result = result;
    }

    public Optional<R> build() {
        return Optional.ofNullable(result);
    }
}
