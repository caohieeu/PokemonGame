package com.example.pokemongame.dto.apiDto;

import com.example.pokemongame.repository.entity.Moves;
import com.example.pokemongame.repository.subentity.Ability;
import com.example.pokemongame.repository.subentity.Species;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PokemonApiDto {
    long id;
    String name;
    Ability[] abilities;
    MoveApiDto[] moves;
    Species species;
    SpritesApiDto sprites;
    StatApiDto[] stats;
    TypeElementApiDto[] types;
    long height;
    long weight;
}
