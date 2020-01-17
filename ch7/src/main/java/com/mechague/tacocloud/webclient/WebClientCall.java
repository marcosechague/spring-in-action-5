package com.mechague.tacocloud.webclient;

import com.mechague.tacocloud.domain.Ingredient;
import com.mechague.tacocloud.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class WebClientCall {

    private RestTemplate rest;
    private Traverson traverson;

    public WebClientCall(RestTemplate restTemplate, Traverson traverson){
        this.rest= restTemplate;
        this.traverson = traverson;
    }

    private Ingredient getIngredientByIdUsingObject(String ingredientId) {
        Ingredient ingredient = null;
        try{
            ingredient = rest.getForObject("http://localhost:9090/api/ingredients/{id}",
                    Ingredient.class, ingredientId);
        }catch (RestClientException e){
            log.error("Error executing call");
        }
        return ingredient;
    }

    private Ingredient getIngredientById(String ingredientId) {
        Ingredient ingredient = null;
        try {
            Map<String, String> urlVariables = new HashMap<>();
            urlVariables.put("id", ingredientId);
            ingredient = rest.getForObject("http://localhost:9090/api/ingredients/{id}",
                    Ingredient.class, urlVariables);
        }catch (RestClientException e){
            log.error("Error executing call");
        }
        return ingredient;
    }

    private Ingredient getIngredientByIdUsingEntity(String ingredientId) {
        Ingredient ingredient = null;
        try {
            ResponseEntity<Ingredient> responseEntity =
                    rest.getForEntity("http://localhost:9090/api/ingredients/{id}",
                            Ingredient.class, ingredientId);
            log.info("Fetched time: " +
                    responseEntity.getHeaders().getDate());
            ingredient = responseEntity.getBody();
        }catch (RestClientException e){
            log.error("Error executing call");
        }
        return ingredient;

    }

    private void updateIngredient(Ingredient ingredient) {
        try {
            rest.put("http://localhost:9090/api/ingredients/{id}",
                    ingredient,
                    ingredient.getId());
        }catch (RestClientException e){
            log.error("Error executing call");
        }
    }

    private void deleteIngredient(String ingredientId) {
        try{
            rest.delete("http://localhost:9090/api/ingredients/{id}",
                ingredientId);
        }catch (RestClientException e){
            log.error("Error executing call");
        }
    }

    private Ingredient createIngredientUsingObject(Ingredient ingredient) {
        Ingredient ingredientCreated = null;
        try{
            ingredientCreated = rest.postForObject("http://localhost:9090/api/ingredients",
                ingredient,
                Ingredient.class);
        }catch (RestClientException e){
            log.error("Error executing call");
        }
        return  ingredientCreated;
    }

    private URI createIngredientUsingLocation(Ingredient ingredient) {
        URI location = null;
        try {
            location = rest.postForLocation("http://localhost:9090/api/ingredients",
                    ingredient);
        }catch (RestClientException e){
            log.error("Error executing call");
        }
        return location;
    }

    private Ingredient createIngredientUsingEntity(Ingredient ingredient) {

        Ingredient ingredientCreated = null;
        try{
            ResponseEntity<Ingredient> responseEntity =
                    rest.postForEntity("http://localhost:9090/api/ingredients",
                            ingredient,
                            Ingredient.class);
            log.info("New resource created at " +
                    responseEntity.getHeaders().getLocation());
            ingredientCreated = responseEntity.getBody();
        }catch (RestClientException e){
            log.error("Error executing call");
        }
        return  ingredientCreated;
    }

    private Collection<Ingredient> getAllIngretientsTraverson(){

        Collection<Ingredient> ingredients = null;

        try{
            ParameterizedTypeReference<CollectionModel<Ingredient>> ingredientType =
                    new ParameterizedTypeReference<CollectionModel<Ingredient>>() {};

            CollectionModel<Ingredient> ingredientRes = traverson
                    .follow("ingredients")
                    .toObject(ingredientType);

            ingredients = ingredientRes.getContent();
        }catch (RestClientException e){
            log.error("Error executing call");
        }
        return  ingredients;


    }

    private Collection<Taco> getRecentTacosTraverson(){
        Collection<Taco> recentTacos = null;
        try{
            ParameterizedTypeReference<CollectionModel<Taco>> tacoType =
                    new ParameterizedTypeReference<CollectionModel<Taco>>() {};

            CollectionModel<Taco> tacosRes = traverson
                    .follow("tacos")
                    .follow("recents")
                    .toObject(tacoType);

            recentTacos =  tacosRes.getContent();
        }catch (RestClientException e){
            log.error("Error executing call", e);
        }
        return recentTacos;

    }

    private Ingredient addIngredientTraverson(Ingredient ingredient) {
        Ingredient ingredientCreated = null;
        try {
            String ingredientsUrl = traverson
                    .follow("ingredients")
                    .asLink()
                    .getHref();
            ingredientCreated = rest.postForObject(ingredientsUrl,
                    ingredient,
                    Ingredient.class);

        }catch (RestClientException e){
            log.error("Error executing call");
        }
        return ingredientCreated;
    }

    public void makeCalls(){
        getIngredientByIdUsingObject("FLTO");
        getIngredientById("FLTO");
        getIngredientByIdUsingEntity("FLTO");
        updateIngredient(new Ingredient("TMTO", "Tomatoes", Ingredient.Type.WRAP));
        deleteIngredient("SRCR");
        createIngredientUsingObject(new Ingredient("LIME", "Lime", Ingredient.Type.SAUCE ));
        createIngredientUsingLocation(new Ingredient("CORN", "Corn Tortilla", Ingredient.Type.WRAP ));
        createIngredientUsingEntity(new Ingredient("CUMI", "Cumin", Ingredient.Type.SAUCE ));
        addIngredientTraverson(new Ingredient("CHIL", "Chili powder", Ingredient.Type.SAUCE ));
        Collection<Ingredient> ingredientsByTraverson = getAllIngretientsTraverson();
        log.info("Ingredients by traverson", ingredientsByTraverson);
        Collection<Taco> recentTacosByTraverson = getRecentTacosTraverson();
        log.info("Recent tacos by traverson", recentTacosByTraverson);
    }



}
