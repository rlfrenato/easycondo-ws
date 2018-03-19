package com.mycompany.easycondows.easycondows.condominium.repository;

import com.mycompany.easycondows.easycondows.condominium.model.Condominium;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondominiumRepository extends CrudRepository<Condominium, Long> {

}
