package com.example.pokemongame.repository.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Document("RoomBattle")
public class RoomBattle {
    @Id
    String id;
    String roomId;
    String password;
    User player;
    User opponent;
    Time time;
    Date dateCreated;
}
