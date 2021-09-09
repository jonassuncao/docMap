package com.jassuncao.docmap.web;

import com.jassuncao.docmap.domain.project.Project;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
public abstract class ResponseUtils {

    public static final String DOCMAP_RESPONSE = "docmap-response";

    public static ResponseEntity created(Serializable response, Class resource) {
        return responseEntity(ResponseType.CREATED, response, resource);
    }

    public static ResponseEntity<UUID> updated(Serializable response, Class resource) {
        return responseEntity(ResponseType.UPDATED, response, resource);
    }

    public static ResponseEntity<UUID> deleted(Class resource) {
        final ResponseType responseType = ResponseType.DELETED;
        return ResponseEntity.noContent().headers(responseHeaders(responseType.message(resource))).build();
    }

    private static ResponseEntity<UUID> responseEntity(ResponseType responseType, Serializable response, Class<Project> resource) {
        return new ResponseEntity(response, responseHeaders(responseType.message(resource)), responseType.getHttpStatus());
    }

    private static HttpHeaders responseHeaders(String message) {
        final HttpHeaders header = new HttpHeaders();
        header.add(DOCMAP_RESPONSE, message);
        return header;
    }
}
