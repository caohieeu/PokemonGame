package com.example.pokemongame.repository.entity;

import com.example.pokemongame.repository.subentity.Species;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Document("Moves")
public class Moves {
    @Id
    Long id;
    String name;
//    Type type; //lưu dưới dạng list các id của type
    Species type;
    Long power;
    Long priority;
    Long effect_chance;
    Long pp;
    Long accuracy;
}
