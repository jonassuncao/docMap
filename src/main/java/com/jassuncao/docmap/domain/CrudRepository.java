package com.jassuncao.docmap.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

/**
 * @author jonathas.assuncao - jaa020399@gmail.com
 * 09/09/2021
 */

@NoRepositoryBean
public interface CrudRepository<T> extends JpaRepository<T, UUID> {

}
