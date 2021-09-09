package com.jassuncao.docmap.domain;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

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
