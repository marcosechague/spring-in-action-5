package com.mechague.tacocloud.representation;

import com.mechague.tacocloud.domain.Ingredient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

public class IngredientModel extends RepresentationModel<Ingredient> {

    @Getter
    @Setter
    private String name;
    @Getter
    private Ingredient.Type type;

    public IngredientModel(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }
}
