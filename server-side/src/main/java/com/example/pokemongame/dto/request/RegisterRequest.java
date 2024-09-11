package com.example.pokemongame.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    @Size(min = 5, message = "USERNAME_INVALID")
    String username;

    String displayName;

    @Size(min = 6, message = "PASSWORD_INVALID")
    String password;

    //@Email(message = "EMAIL_INVALID")
    String email;
    String phone;
}
