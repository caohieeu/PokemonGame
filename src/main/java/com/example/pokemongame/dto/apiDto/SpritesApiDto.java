package com.example.pokemongame.dto.apiDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpritesApiDto {
    String front_default;
    String back_default;
    OtherApiDto other;
}
