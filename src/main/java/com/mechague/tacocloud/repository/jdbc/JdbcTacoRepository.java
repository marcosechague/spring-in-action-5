package com.mechague.tacocloud.repository.jdbc;

import com.mechague.tacocloud.domain.Taco;


public interface JdbcTacoRepository {

    Taco save(Taco design);
}
