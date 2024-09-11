package com.example.pokemongame.service;

import com.example.pokemongame.repository.entity.Pokemon;

public interface PokemonService {
    void fetchDataAndSaveFromAPI(int id);

    void fetchDataAndSaveTypeFormAPI();

    void addPokemon(Pokemon pokemon);
    void addPokemonToUser();
}
