package com.jassuncao.docmap.domain.relationship;

import com.jassuncao.docmap.domain.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

@Repository
public interface RelationshipRepository extends CrudRepository<Relationship> {

    List<Relationship> findByEntityToId(UUID entityId);
}
