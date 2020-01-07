package com.mechague.tacocloud.data;

import com.mechague.tacocloud.domain.Ingredient;

// Chaper 3
public interface IngredientRepository {

    Iterable<Ingredient> findAll();
    Ingredient findOne(String id);
    Ingredient save(Ingredient ingredient);
}
