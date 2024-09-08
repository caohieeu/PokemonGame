package com.example.pokemongame.controller;

import com.example.pokemongame.repository.entity.Pokemon;
import com.example.pokemongame.service.PokemonService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FieldDefaults(level = AccessLevel.PRIVATE)

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {
    @Autowired
    PokemonService pokemonService;
    @PostMapping("/fetchAndSaveData/{id}")
    public ResponseEntity<?> fetchDataAndSaveFromAPI(
            @PathVariable int id
    ) {
        for(int i = 1; i <= 1302; i++) {
            pokemonService.fetchDataAndSaveFromAPI(i);
        }
        return ResponseEntity.ok().body("fetch success");
    }
    @PostMapping("/fetchAndSaveDataType")
    public ResponseEntity<?> fetchDataAndSaveFromAPI() {
        pokemonService.fetchDataAndSaveTypeFormAPI();
        return ResponseEntity.ok().body("fetch success");
    }
    @PostMapping
    public ResponseEntity<?> addPokemon(
            @RequestBody Pokemon pokemon
            ) {
        pokemonService.addPokemon(pokemon);
        return ResponseEntity.ok().body("Created");
    }
}
