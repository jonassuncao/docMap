package com.jassuncao.docmap.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;


/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class AbstractCommand implements Serializable {
    private static final long serialVersionUID = 1L;
}
