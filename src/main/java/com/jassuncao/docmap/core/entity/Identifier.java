package com.jassuncao.docmap.core.entity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
public class Identifier implements Serializable {

    @Id()
    private UUID id;

    Identifier() {
        super();
    }

    public UUID getId() {
        return id;
    }

     void inicialize() {
        id = UUID.randomUUID();
    }

    private void setId(UUID id) {
        this.id = id;
    }
}
