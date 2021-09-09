package com.jassuncao.docmap.infra;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;
import java.util.Optional;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

public class OptionalSerializer extends JsonSerializer<Optional> {

    @Override
    public void serialize(Optional optional, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNull();
        if (ObjectUtils.isNotEmpty(optional) && optional.isPresent()) {
            jsonGenerator.writeObject(optional.get());
        }
    }
}
