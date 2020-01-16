package com.mechague.tacocloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Configuration
public class WebClientConfig {

    @Bean
    public RestTemplate configureRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public Traverson configureTraverson(){
        return new Traverson(URI.create("http://localhost:9090/api"), MediaTypes.HAL_JSON);
    }
}
