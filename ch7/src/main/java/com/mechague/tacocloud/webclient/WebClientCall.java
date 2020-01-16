package com.mechague.tacocloud.webclient;

import com.mechague.tacocloud.domain.Ingredient;
import com.mechague.tacocloud.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WebClientCall {

    private RestTemplate rest;
    private Traverson traverson;

    public WebClientCall(RestTemplate restTemplate, Traverson traverson){
        this.rest= restTemplate;
        this.traverson = traverson;
    }

    public Ingredient getIngredientByIdUsingObject(String ingredientId) {
        return rest.getForObject("http://localhost:8080/ingredients/{id}",
                Ingredient.class, ingredientId);
    }

    public Ingredient getIngredientById(String ingredientId) {
        Map<String,String> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);
        return rest.getForObject("http://localhost:8080/ingredients/{id}",
                Ingredient.class, urlVariables);
    }

    public Ingredient getIngredientByIdUsingEntity(String ingredientId) {
        ResponseEntity<Ingredient> responseEntity =
                rest.getForEntity("http://localhost:8080/ingredients/{id}",
                        Ingredient.class, ingredientId);
        log.info("Fetched time: " +
                responseEntity.getHeaders().getDate());
        return responseEntity.getBody();
    }

    public void updateIngredient(Ingredient ingredient) {
        rest.put("http://localhost:8080/ingredients/{id}",
                ingredient,
                ingredient.getId());
    }

    public void deleteIngredient(String ingredientId) {
        rest.delete("http://localhost:8080/ingredients/{id}",
                ingredientId);
    }

    public Ingredient createIngredientUsingObject(Ingredient ingredient) {
        return rest.postForObject("http://localhost:8080/ingredients",
                ingredient,
                Ingredient.class);
    }

    public URI createIngredientUsingLocation(Ingredient ingredient) {
        return rest.postForLocation("http://localhost:8080/ingredients",
                ingredient);
    }

    public Ingredient createIngredientUsingEntity(Ingredient ingredient) {
        ResponseEntity<Ingredient> responseEntity =
                rest.postForEntity("http://localhost:8080/ingredients",
                        ingredient,
                        Ingredient.class);
        log.info("New resource created at " +
                responseEntity.getHeaders().getLocation());
        return responseEntity.getBody();
    }

    public Collection<Ingredient> getAllIngretientsTraverson(){
        ParameterizedTypeReference<CollectionModel<Ingredient>> ingredientType =
                new ParameterizedTypeReference<CollectionModel<Ingredient>>() {};

        CollectionModel<Ingredient> ingredientRes = traverson
                .follow("ingredients")
                .toObject(ingredientType);

        return  ingredientRes.getContent();

    }

    public Collection<Taco> getRecentTacosTraverson(){
        ParameterizedTypeReference<CollectionModel<Taco>> tacoType =
                new ParameterizedTypeReference<CollectionModel<Taco>>() {};

        CollectionModel<Taco> tacosRes = traverson
                .follow("tacos")
                .follow("recents")
                .toObject(tacoType);

        return  tacosRes.getContent();
    }

    public Ingredient addIngredient(Ingredient ingredient) {
        String ingredientsUrl = traverson
                .follow("ingredients")
                .asLink()
                .getHref();
        return rest.postForObject(ingredientsUrl,
                ingredient,
                Ingredient.class);
    }

    public void makeCalls(){
        getIngredientByIdUsingObject("FLTO");
        getIngredientById("FLTO");
        getIngredientByIdUsingEntity("FLTO");
        updateIngredient();
        deleteIngredient("SRCR");
        createIngredientUsingObject(new Ingredient("LIME", "Lime", Ingredient.Type.SAUCE );
        createIngredientUsingLocation(null);
        createIngredientUsingEntity(null);
        Colle getAllIngretientsTraverson();
        addIngredient
    }



}
