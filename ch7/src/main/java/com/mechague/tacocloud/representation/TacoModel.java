package com.mechague.tacocloud.representation;

import com.mechague.tacocloud.domain.Taco;
import com.mechague.tacocloud.representation.assembler.IngredientModelAssembler;
import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Date;

@Relation(value="taco", collectionRelation="tacos")
public class TacoModel extends RepresentationModel<Taco> {

    private static final IngredientModelAssembler
            ingredientAssembler = new IngredientModelAssembler();

    @Getter
    private final String name;
    @Getter
    private final Date createdAt;
    @Getter
    private final CollectionModel<IngredientModel> ingredients;

    public TacoModel(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients = ingredientAssembler.toCollectionModel(taco.getIngredients());
    }
}
