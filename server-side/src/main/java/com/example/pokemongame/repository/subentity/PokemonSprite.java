package com.example.pokemongame.repository.subentity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PokemonSprite {
    String image;
    String back;
    String front;
}
