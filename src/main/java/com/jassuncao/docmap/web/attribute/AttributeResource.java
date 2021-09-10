package com.jassuncao.docmap.web.attribute;

import com.jassuncao.docmap.application.attribute.AttributeApplication;
import com.jassuncao.docmap.application.attribute.AttributeCreateCommand;
import com.jassuncao.docmap.application.attribute.AttributeDeleteCommand;
import com.jassuncao.docmap.application.attribute.AttributeUpdateCommand;
import com.jassuncao.docmap.domain.attribute.Attribute;
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
@RequestMapping("/api/attributes")
public class AttributeResource {

    @Autowired
    private AttributeApplication attributeApplication;

    @PostMapping()
    public ResponseEntity<UUID> create(@RequestBody AttributeCreateCommand command) {
        final var result = attributeApplication.when(command);
        return ResponseUtils.created(result.getId(), Attribute.class);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> update(@PathVariable UUID id, @RequestBody AttributeUpdateCommand command) {
        final var result = attributeApplication.when(command.id(id));
        return ResponseUtils.updated(result.getId(), Attribute.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> Delete(@PathVariable UUID id) {
        attributeApplication.when(new AttributeDeleteCommand(id));
        return ResponseUtils.deleted(Attribute.class);
    }
}
