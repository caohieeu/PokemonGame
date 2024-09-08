package com.example.pokemongame.repository;

import com.example.pokemongame.repository.entity.Moves;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MoveRepository extends MongoRepository<Moves, Long> {

}
