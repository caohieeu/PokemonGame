package com.example.pokemongame.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTORequest {
    String id;
    String username;
    String avatar;
    String displayName;
    String password;
    String email;
    String phone;
}
