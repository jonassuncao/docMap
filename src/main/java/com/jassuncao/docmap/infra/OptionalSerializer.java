package com.jassuncao.docmap.infra;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

public class OptionalSerializer extends JsonSerializer<Optional> {

    @Override
    public void serialize(Optional optional, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        final var value = ObjectUtils.defaultIfNull(optional, Optional.empty()).flatMap(this::sanitize)
                .filter(ObjectUtils::isNotEmpty).orElse(null);
        if (Objects.nonNull(value)) {
            jsonGenerator.writeObject(value);
        } else {
            jsonGenerator.writeNull();
        }
    }

    private Optional<Object> sanitize(Object data) {
        return new OptionalMapper<>(data).ifCast(String.class, StringUtils::trimToEmpty).build();
    }
}
