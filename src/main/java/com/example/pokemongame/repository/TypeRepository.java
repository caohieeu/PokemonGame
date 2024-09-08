package com.example.pokemongame.repository;

import com.example.pokemongame.repository.entity.Type;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TypeRepository extends MongoRepository<Type, Long> {
}
