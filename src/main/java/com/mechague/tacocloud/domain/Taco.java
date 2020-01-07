package com.mechague.tacocloud.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

//Lombook annotation  @see <a href="https://projectlombok.org">Project Lombook</a>
@Data
public class Taco {
    //The library of those validation annotations already comes with the dependency spring boot starter
    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<String> ingredients;

    //Chapter 3
    private Long id;

    private Date createdAt;

}
