package com.jassuncao.docmap.domain.entity;

import com.jassuncao.docmap.domain.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository extends CrudRepository<Entity> {
}
