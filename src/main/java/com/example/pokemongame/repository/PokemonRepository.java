package com.example.pokemongame.repository;

import com.example.pokemongame.repository.entity.Pokemon;
import com.example.pokemongame.repository.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository extends MongoRepository<Pokemon, String> {
    List<Pokemon> findByNameContaining(String name);
    Pokemon findByName(String name);
}
