package com.mechague.tacocloud.config;

import com.mechague.tacocloud.domain.User;
import com.mechague.tacocloud.repository.jpa.IngredientRepository;
import com.mechague.tacocloud.repository.jpa.UserRepository;
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
                .fullname("Marcos Echague")
                .city("Asuncion")
                .phoneNumber("595985445212")
                .password(encoder.encode("123456"))
                .username("mechague")
                .build();

        return args -> userRepo.save(user);

    }
}
