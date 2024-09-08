package com.example.pokemongame.dto.apiDto;

import com.example.pokemongame.repository.subentity.Species;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatApiDto {
    long base_stat;
    long effort;
    Species stat;
}
