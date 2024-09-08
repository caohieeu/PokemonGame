package com.example.pokemongame.repository.entity;

import com.example.pokemongame.repository.subentity.Ability;
import com.example.pokemongame.repository.subentity.PokemonSprite;
import com.example.pokemongame.repository.subentity.Species;
import com.example.pokemongame.repository.subentity.Stat;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Document("Pokemon")
public class Pokemon {
    @Id
    Long id;
    String name;
    List<String> type;
    PokemonSprite sprites;
    Species species;
    Stat stat;
    Ability[] abilities;
    List<Moves> moves;
    Double height;
    Double weight;
}
