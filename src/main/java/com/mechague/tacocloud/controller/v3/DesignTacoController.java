package com.mechague.tacocloud.controller.v3;

import com.mechague.tacocloud.repository.jpa.IngredientRepository;
import com.mechague.tacocloud.repository.jpa.TacoRepository;
import com.mechague.tacocloud.domain.Ingredient;
import com.mechague.tacocloud.domain.Order;
import com.mechague.tacocloud.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mechague.tacocloud.domain.Ingredient.Type;

@Slf4j
@Controller("DesignTacoControllerV3")
@RequestMapping("/v3/design")
//Specifies any model objects like the order attribute that should be kept in session and available across
//multiple requests
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private final TacoRepository designRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
    }


    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }
    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        addIngredientsToModel(model);
        return "design";
    }

    private void addIngredientsToModel(Model model){
        List<Ingredient> ingredients = getIngredients();

        Type[] types = Type.values();
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
                                Model model,
                                //Indicates that its value should come from the model and that Spring MVC shouldn’t attempt to bind  request parameters to it.
                                @ModelAttribute Order order) {
        // Save the taco design...
        if(errors.hasErrors()){
            addIngredientsToModel(model);
            return "design";
        }
        log.info("Processing design: " + taco);
        Taco saved = designRepo.save(taco);
        order.addDesign(saved);
        return "redirect:/v3/orders/current";
    }

    private List<Ingredient> getIngredients(){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll()
                .forEach(ingredients::add);
        return ingredients;
    }
}