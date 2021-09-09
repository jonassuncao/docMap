package com.jassuncao.docmap.web.relationship;

import com.jassuncao.docmap.application.relationship.RelationshipApplication;
import com.jassuncao.docmap.application.relationship.RelationshipCreateCommand;
import com.jassuncao.docmap.application.relationship.RelationshipDeleteCommand;
import com.jassuncao.docmap.application.relationship.RelationshipUpdateCommand;
import com.jassuncao.docmap.domain.relationship.Relationship;
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
@RequestMapping("/api/relationships")
public class RelationshipResource {

    @Autowired
    private RelationshipApplication relationshipApplication;

    @PostMapping()
    public ResponseEntity<UUID> create(@RequestBody RelationshipCreateCommand command) {
        final var result = relationshipApplication.when(command);
        return ResponseUtils.created(result.getId(), Relationship.class);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> update(@PathVariable UUID id, @RequestBody RelationshipUpdateCommand command) {
        final var result = relationshipApplication.when(command.id(id));
        return ResponseUtils.updated(result.getId(), Relationship.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> Delete(@PathVariable UUID id) {
        relationshipApplication.when(new RelationshipDeleteCommand(id));
        return ResponseUtils.deleted(Relationship.class);
    }
}
