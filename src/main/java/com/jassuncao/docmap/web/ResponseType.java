package com.jassuncao.docmap.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */
public enum ResponseType {
    CREATED(HttpStatus.CREATED),
    UPDATED(HttpStatus.OK),
    DELETED(HttpStatus.NO_CONTENT),
    ;

    private final HttpStatus status;

    ResponseType(HttpStatus status) {
        this.status = status;
    }

    public String message(Class resource) {
        final String name = StringUtils.substringAfterLast(resource.getName(), ".");
        return String.format("%s.%s", name, name()).toLowerCase();
    }

    public HttpStatus getHttpStatus() {
        return status;
    }
}
