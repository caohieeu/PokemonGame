package com.example.pokemongame.repository;

import com.example.pokemongame.repository.custom.UserRepositoryCustom;
import com.example.pokemongame.repository.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {
    List<User> findByUsernameContaining(String name);
    Optional<User> findByUsername(String name);
    Boolean existsByUsername(String username);
}
