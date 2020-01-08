package com.mechague.tacocloud.data;

import com.mechague.tacocloud.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository <Ingredient, String> {
}
