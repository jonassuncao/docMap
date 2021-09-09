package com.jassuncao.docmap.domain;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

@MappedSuperclass
public class Identifier implements Serializable {

    @Id()
    private UUID id;

    public Identifier() {
        super();
        inicialize();
    }

    public UUID getId() {
        return id;
    }

    private void setId(UUID id) {
        this.id = id;
    }

    public void inicialize() {
        id = UUID.randomUUID();
    }
}
