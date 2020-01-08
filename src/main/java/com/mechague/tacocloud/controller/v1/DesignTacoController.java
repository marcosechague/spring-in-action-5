package com.mechague.tacocloud.controller.v1;

import com.mechague.tacocloud.domain.Ingredient;
import com.mechague.tacocloud.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.mechague.tacocloud.domain.Ingredient.Type;

@Slf4j
@Controller("DesignTacoControllerV1")
@RequestMapping("/v1/design")
public class DesignTacoController {

    //Chapter 2 return hardcoded list of ingredients
    @GetMapping
    public String showDesignForm(Model model) {
        addIngredientsToModel(model);
        model.addAttribute("taco", new Taco());
        return "design";
    }

    private void addIngredientsToModel(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }


    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
        return ingredients.stream()
                .filter( i -> type.equals(i.getType()))
                .collect(Collectors.toList());
    }

    @PostMapping
    //@Valid is us to validate the annotations provided in TacoEntity class
    //Errors bounds the errors that comes from the validations if exists
    //if any error, the taco  object must be te same name like the object of the thymeleaf template, to binding results
    public String processDesign(@Valid Taco taco, Errors errors,
                                Model model) {
        // Save the taco design...
        if(errors.hasErrors()){
            addIngredientsToModel(model);
            return "design";
        }
        log.info("Processing design: " + taco);
        return "redirect:/v1/orders/current";
    }

}