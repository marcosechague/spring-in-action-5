package com.mechague.tacocloud.api.config;

import com.mechague.tacocloud.api.domain.User;
import com.mechague.tacocloud.api.repository.jpa.IngredientRepository;
import com.mechague.tacocloud.api.repository.jpa.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo,
                                        UserRepository userRepo, PasswordEncoder encoder) {
        User user = User.builder()
                .fullname("John Smith")
                .city("Asuncion")
                .phoneNumber("595985445212")
                .password(encoder.encode("123456"))
                .username("jsmith")
                .build();

        return args -> userRepo.save(user);

    }



}
