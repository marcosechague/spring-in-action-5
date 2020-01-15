package com.mechague.tacocloud.api.repository.jpa;

import com.mechague.tacocloud.api.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository <Ingredient, String> {
}
