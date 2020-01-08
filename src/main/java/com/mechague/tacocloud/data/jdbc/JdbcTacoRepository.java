package com.mechague.tacocloud.data.jdbc;

import com.mechague.tacocloud.domain.Taco;


public interface JdbcTacoRepository {

    Taco save(Taco design);
}
