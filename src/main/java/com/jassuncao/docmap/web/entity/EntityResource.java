package com.jassuncao.docmap.web.entity;

import com.jassuncao.docmap.application.entity.EntityApplication;
import com.jassuncao.docmap.application.entity.EntityCreateCommand;
import com.jassuncao.docmap.application.entity.EntityDeleteCommand;
import com.jassuncao.docmap.application.entity.EntityUpdateCommand;
import com.jassuncao.docmap.domain.entity.Entity;
import com.jassuncao.docmap.web.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

@RestController
@RequestMapping("/api/entities")
public class EntityResource {

    @Autowired
    private EntityApplication entityApplication;

    @PostMapping()
    public ResponseEntity<UUID> create(@RequestBody EntityCreateCommand command) {
        final var result = entityApplication.when(command);
        return ResponseUtils.created(result.getId(), Entity.class);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> update(@PathVariable UUID id, @RequestBody EntityUpdateCommand command) {
        final var result = entityApplication.when(command.id(id));
        return ResponseUtils.updated(result.getId(), Entity.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> Delete(@PathVariable UUID id) {
        entityApplication.when(new EntityDeleteCommand(id));
        return ResponseUtils.deleted(Entity.class);
    }
}
