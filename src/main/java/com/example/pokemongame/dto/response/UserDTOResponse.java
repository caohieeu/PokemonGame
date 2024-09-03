package com.example.pokemongame.dto.response;

import com.example.pokemongame.repository.subentity.PokemonSub;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTOResponse {
    String username;
    String email;
    String phone;
    String role;
    Long point;
    List<String> moves;
    List<PokemonSub> pokemons;
    Date dateCreated;
}
