package com.example.pokemongame.repository.subentity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ability {
    Species ability;
    boolean is_hidden;
    Long slot;
}
