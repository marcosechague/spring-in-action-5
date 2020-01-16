package com.mechague.tacocloud.config;

import com.mechague.tacocloud.domain.Taco;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;

@Configuration
public class RepresentationModelConfig {

    @Bean
    public RepresentationModelProcessor<PagedModel<EntityModel<Taco>>> tacoModelProcessor(EntityLinks links){

        return new RepresentationModelProcessor<PagedModel<EntityModel<Taco>>>() {
            @Override
            public PagedModel<EntityModel<Taco>> process(PagedModel<EntityModel<Taco>> model) {
                model.add(
                        links.linkFor(Taco.class)
                                .slash("recent")
                                .withRel("recents"));
                return model;
            }
        };
    }
}
