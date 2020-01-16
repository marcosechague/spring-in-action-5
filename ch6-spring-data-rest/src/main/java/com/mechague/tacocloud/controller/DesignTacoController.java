package com.mechague.tacocloud.controller;

import com.mechague.tacocloud.domain.Taco;
import com.mechague.tacocloud.repository.jpa.IngredientRepository;
import com.mechague.tacocloud.repository.jpa.TacoRepository;
import com.mechague.tacocloud.representation.TacoModel;
import com.mechague.tacocloud.representation.assembler.TacoModelAssembler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Slf4j
@RestController("DesignTacoControllerV4")
@RequestMapping(path = "/design", produces = MediaType.APPLICATION_JSON_VALUE)
//This include a header to allow to front end application run in different servers
@CrossOrigin(origins = "*")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private final TacoRepository tacoRepo;
    //@Autowired
    //EntityLinks entityLinks;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = designRepo;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        return  tacoRepo.findById(id)
                .map( ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //only if header Accept is application/json
    @GetMapping(value = "/recent", produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<TacoModel> recentTacos() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());

        List<Taco> tacos = tacoRepo.findAll(page).getContent();


        /*List<TacoResource> tacoResources =
                new TacoResourceAssembler().toResources(tacos);
        Resources<TacoResource> recentResources =
                new Resources<TacoResource>(tacoResources);
        recentResources.add(
                linkTo(methodOn(DesignTacoController.class).recentTacos())
                        .withRel("recents"));*/

        CollectionModel<TacoModel> tacoModels = new TacoModelAssembler().toCollectionModel(tacos);

        //CollectionModel<EntityModel<Taco>> recentResources = CollectionModel.(tacoModels);

        tacoModels.add(
                linkTo(methodOn(DesignTacoController.class).recentTacos())
                .withRel("recents"));
        return tacoModels;
    }

    //only if header Content-type is application/json
    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }

}