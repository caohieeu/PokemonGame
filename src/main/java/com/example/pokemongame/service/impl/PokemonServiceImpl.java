package com.example.pokemongame.service.impl;

import com.example.pokemongame.dto.apiDto.*;
import com.example.pokemongame.exception.AppException;
import com.example.pokemongame.exception.ErrorCode;
import com.example.pokemongame.repository.MoveRepository;
import com.example.pokemongame.repository.PokemonRepository;
import com.example.pokemongame.repository.TypeRepository;
import com.example.pokemongame.repository.entity.Moves;
import com.example.pokemongame.repository.entity.Pokemon;
import com.example.pokemongame.repository.entity.Type;
import com.example.pokemongame.repository.entity.User;
import com.example.pokemongame.repository.subentity.PokemonSprite;
import com.example.pokemongame.repository.subentity.Stat;
import com.example.pokemongame.service.PokemonService;
import com.sun.tools.jconsole.JConsolePlugin;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)

@Service
public class PokemonServiceImpl implements PokemonService {
    @Autowired
    PokemonRepository pokemonRepository;
    @Autowired
    MoveRepository moveRepository;
    @Autowired
    TypeRepository typeRepository;
    @Override
    public void fetchDataAndSaveFromAPI(int id) {
        String apiUrl = "https://pokeapi.co/api/v2/pokemon/"+id+"/";
        RestTemplate restTemplate = new RestTemplate();
        PokemonApiDto data = restTemplate.getForObject(apiUrl, PokemonApiDto.class);

        List<String> listType = Arrays.stream(data.getTypes()).map(t -> t.getType().getName()).toList();
        PokemonSprite pokemonSprite = PokemonSprite.builder()
                .image(data.getSprites().getFront_default())
                .front(data.getSprites().getOther().getShowdown().getFront_default())
                .back(data.getSprites().getOther().getShowdown().getBack_default())
                .build();
        Stat newStat = new Stat();
        for(StatApiDto stat : data.getStats()) {
            switch (stat.getStat().getName()) {
                case "hp": {
                    newStat.setHp(stat.getBase_stat());
                    break;
                }
                case "attack": {
                    newStat.setAtk(stat.getBase_stat());
                    break;
                }
                case "defense": {
                    newStat.setDefense(stat.getBase_stat());
                    break;
                }
                case "special-attack": {
                    newStat.setSp_atk(stat.getBase_stat());
                    break;
                }
                case "special-defense": {
                    newStat.setSp_def(stat.getBase_stat());
                    break;
                }
                case "speed": {
                    newStat.setSpeed(stat.getBase_stat());
                    break;
                }
            }
        }
        List<Moves> listMove = new ArrayList<>();
        for(MoveApiDto moveApiDto : data.getMoves()) {
            RestTemplate restTemplate2 = new RestTemplate();
            Moves data2 = restTemplate.getForObject(moveApiDto.getMove().getUrl(),
                    Moves.class);
            listMove.add(data2);
        }
        if(data != null) {
            Pokemon pokemon = Pokemon.builder()
                    .id(data.getId())
                    .name(data.getName())
                    .type(listType)
                    .sprites(pokemonSprite)
                    .species(data.getSpecies())
                    .stat(newStat)
                    .abilities(data.getAbilities())
                    .moves(listMove)
                    .build();
            pokemonRepository.insert(pokemon);
        }
    }

    @Override
    public void fetchDataAndSaveTypeFormAPI() {
        String apiUrl = "https://pokeapi.co/api/v2/type/";
        for(int i = 1; i <= 19; i++){
            String api = apiUrl + i + "/";
            RestTemplate restTemplate = new RestTemplate();
            Type data = restTemplate.getForObject(api, Type.class);
            typeRepository.insert(data);
        }
    }

    @Override
    public void addPokemon(Pokemon pokemon) {
        Pokemon pkm = pokemonRepository.findByName(pokemon.getName());
        if(pkm != null) {
            throw new AppException(ErrorCode.POKEMON_EXISTED);
        }
        pokemonRepository.insert(pokemon);
    }
}
