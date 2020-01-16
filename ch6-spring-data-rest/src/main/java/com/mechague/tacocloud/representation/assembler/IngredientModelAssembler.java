package com.mechague.tacocloud.representation.assembler;

import com.mechague.tacocloud.controller.DesignTacoController;
import com.mechague.tacocloud.domain.Ingredient;
import com.mechague.tacocloud.representation.IngredientModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class IngredientModelAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngredientModel> {

    public IngredientModelAssembler() {
        super(DesignTacoController.class, IngredientModel.class);
    }

    @Override
    public IngredientModel instantiateModel(Ingredient ingredient) {
        return new IngredientModel(ingredient);
    }

    @Override
    public IngredientModel toModel(Ingredient ingredient) {
        return createModelWithId(ingredient.getId(), ingredient);
    }

}
