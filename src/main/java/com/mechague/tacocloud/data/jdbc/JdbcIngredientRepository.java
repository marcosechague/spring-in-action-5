package com.mechague.tacocloud.data.jdbc;

import com.mechague.tacocloud.domain.Ingredient;

// Chaper 3
public interface JdbcIngredientRepository {

    Iterable<Ingredient> findAll();
    Ingredient findOne(String id);
    Ingredient save(Ingredient ingredient);
}
