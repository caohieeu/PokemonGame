package com.example.pokemongame.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UserDTORequest {
    String username;
    String password;
    String email;
    String phone;
}
