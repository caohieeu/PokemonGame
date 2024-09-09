package com.example.pokemongame.repository.entity;

import com.example.pokemongame.repository.subentity.TypeRelations;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Document("Type")
public class Type {
    @Id
    Long id;
    String name;
    TypeRelations damage_relations;
}
