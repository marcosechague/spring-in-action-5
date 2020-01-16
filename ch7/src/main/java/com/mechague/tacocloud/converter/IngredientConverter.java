package com.mechague.tacocloud.converter;

import com.mechague.tacocloud.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientConverter implements Converter< String, Ingredient> {

    @Override
    public Ingredient convert(String id) {
        return new Ingredient(id, null, null);
    }
}
