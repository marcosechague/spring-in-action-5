package com.mechague.tacocloud.api.repository.jpa;

import com.mechague.tacocloud.api.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository <User, Long> {
    Optional<User> findByUsername(String username);
}
