package com.example.pokemongame.repository.entity;

import com.example.pokemongame.repository.subentity.PokemonSub;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Document("User")
public class User {
    @Id
    String id;
    String username;
    String displayName;
    String password;
    String avatar;
    String email;
    Set<String> role;
    String phone;
    Long point;
    List<String> moves;
    List<Pokemon> pokemons;
    Date dateCreated;
}
