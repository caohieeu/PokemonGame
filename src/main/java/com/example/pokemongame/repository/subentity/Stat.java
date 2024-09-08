package com.example.pokemongame.repository.subentity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stat {
    Long hp;
    Long atk;
    Long defense;
    Long sp_atk;
    Long sp_def;
    Long speed;
    Long total;
}
