package com.jassuncao.docmap.infra;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 15/09/2021
 */
public class InternalException extends RuntimeException implements Serializable {

    private final List<Object> params;

    private static final long serialVersionUID = 1L;

    public InternalException(Throwable throwable) {
        super(throwable);
        params = Collections.emptyList();
    }

}
