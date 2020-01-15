package com.mechague.tacocloud.api.repository.jpa;

import com.mechague.tacocloud.api.domain.Taco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface TacoRepository extends CrudRepository<Taco, Long> {
    Page<Taco> findAll(Pageable page);
}