package com.mechague.tacocloud.api.repository.jdbc;

import com.mechague.tacocloud.api.domain.Taco;


public interface JdbcTacoRepository {

    Taco save(Taco design);
}
