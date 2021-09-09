package com.jassuncao.docmap.infra;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Optional;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper() {
        super();
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        registerModule(module());
    }

    private Module module() {
        final SimpleModule module = new SimpleModule();
        module.addSerializer(Optional.class, new OptionalSerializer());
        module.addDeserializer(String.class, new StringDeserializer());
        return module;
    }
}
