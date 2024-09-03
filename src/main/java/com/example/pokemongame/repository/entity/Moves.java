package com.example.pokemongame.repository.entity;

import com.example.pokemongame.repository.subentity.Type;
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
    String id;
    String name;
    Type type;
    Long power;
    Long acc;
}
