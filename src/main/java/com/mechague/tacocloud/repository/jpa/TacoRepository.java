package com.mechague.tacocloud.repository.jpa;

import com.mechague.tacocloud.domain.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}