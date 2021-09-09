package com.jassuncao.docmap.web;

import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
public abstract class ResponseUtils {

    public static final String DOCMAP_RESPONSE = "docmap-response";

    public static ResponseEntity created(Class resource, Serializable response) {
        final ResponseType responseType = ResponseType.CREATED;
        return new ResponseEntity(response, responseHeaders(responseType.message(resource)), responseType.getHttpStatus());
    }

    private static MultiValueMap<String, String> responseHeaders(String message) {
        final MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.add(DOCMAP_RESPONSE, message);
        return header;
    }
}
