package com.jassuncao.docmap.infra;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 15/09/2021
 */
public class ValidationException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;
    private final List<Object> params;

    public ValidationException(String key, List<Object> params) {
        super(key);
        this.params = params;
    }

    public static ValidationException valueOf(String key, Object... params) {
        return new ValidationException(key, Arrays.asList(params));
    }
}
