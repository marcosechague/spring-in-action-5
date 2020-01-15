package com.mechague.tacocloud.config;

import com.mechague.tacocloud.domain.User;
import com.mechague.tacocloud.repository.jpa.IngredientRepository;
import com.mechague.tacocloud.repository.jpa.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo,
                                        UserRepository userRepo) {
        User user = User.builder()
                .fullname("Jhon Smith")
                .city("Asuncion")
                .phoneNumber("595985445212")
                .password("123456")
                .username("jsmith")
                .build();

        return args -> userRepo.save(user);

    }
}
