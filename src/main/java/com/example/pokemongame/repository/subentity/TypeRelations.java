package com.example.pokemongame.repository.subentity;

import com.example.pokemongame.repository.entity.Type;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TypeRelations {
    List<Type> no_damage_to;
    List<Type> half_damage_to;
    List<Type> double_damage_to;
    List<Type> no_damage_from;
    List<Type> half_damage_from;
    List<Type> double_damage_from;
}
