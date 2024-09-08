package com.example.pokemongame.dto.apiDto;

import com.example.pokemongame.repository.entity.Type;
import com.example.pokemongame.repository.subentity.Species;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MoveDetailApiDto {
    Long accuracy;
    Long pp;
    Long power;
    Species type;
}
