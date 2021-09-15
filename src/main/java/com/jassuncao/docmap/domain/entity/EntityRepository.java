package com.jassuncao.docmap.domain.entity;

import com.jassuncao.docmap.domain.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

@Repository
public interface EntityRepository extends CrudRepository<Entity> {

    List<Entity> findByProjectId(UUID projectId);
}
