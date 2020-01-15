package com.mechague.tacocloud.api.converter;

import com.mechague.tacocloud.api.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientConverter implements Converter< String, Ingredient> {

    @Override
    public Ingredient convert(String id) {
        return new Ingredient(id, null, null);
    }
}
