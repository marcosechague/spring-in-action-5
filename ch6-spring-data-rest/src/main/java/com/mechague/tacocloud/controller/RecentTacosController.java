package com.mechague.tacocloud.controller;

import com.mechague.tacocloud.domain.Taco;
import com.mechague.tacocloud.repository.jpa.TacoRepository;
import com.mechague.tacocloud.representation.TacoModel;
import com.mechague.tacocloud.representation.assembler.TacoModelAssembler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RepositoryRestController
public class RecentTacosController {

    private TacoRepository tacoRepo;

    public RecentTacosController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping(path="/tacos/recent", produces= MediaType.APPLICATION_JSON_VALUE)
    //ResponseEntity or @ResponseBody is required when use @RepositoryRestController to tell that is a REST response
    public ResponseEntity<CollectionModel<TacoModel>> recentTacos() {
        PageRequest page = PageRequest.of(
                0, 10, Sort.by("createdAt").descending());

        List<Taco> tacos = tacoRepo.findAll(page).getContent();

        CollectionModel<TacoModel> tacoModels = new TacoModelAssembler().toCollectionModel(tacos);

        tacoModels.add(
                linkTo(methodOn(DesignTacoController.class).recentTacos())
                        .withRel("recents"));

        return new ResponseEntity<>(tacoModels, HttpStatus.OK);
    }
}
