package com.example.pokemongame.repository.entity;

import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {
    @Id
    String id;
    String name;
    String image;
    String type;
    String species;
    Double height;
    Double weight;
    String abilities;
    Long hp;
    Long atk;
    Long defense;
    Long sp_atk;
    Long sp_def;
    Long speed;
    Long total;
}
