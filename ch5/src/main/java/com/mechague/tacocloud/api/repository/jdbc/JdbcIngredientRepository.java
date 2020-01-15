package com.mechague.tacocloud.api.repository.jdbc;

import com.mechague.tacocloud.api.domain.Ingredient;

// Chaper 3
public interface JdbcIngredientRepository {

    Iterable<Ingredient> findAll();
    Ingredient findOne(String id);
    Ingredient save(Ingredient ingredient);
}
