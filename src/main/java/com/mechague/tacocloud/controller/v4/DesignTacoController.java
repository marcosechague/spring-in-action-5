package com.mechague.tacocloud.controller.v4;

import com.mechague.tacocloud.domain.Taco;
import com.mechague.tacocloud.repository.jpa.IngredientRepository;
import com.mechague.tacocloud.repository.jpa.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController("DesignTacoControllerV4")
@RequestMapping(path = "/v4/design", produces = MediaType.APPLICATION_JSON_VALUE)
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
    public Iterable<Taco> recentTacos() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        return tacoRepo.findAll(page)
                .getContent();
    }

    //only if header Content-type is application/json
    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }

}