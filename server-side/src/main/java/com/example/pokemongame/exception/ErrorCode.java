package com.example.pokemongame.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND(1001, "User is not found"),
    USER_EXISTED(1002, "User already exist"),
    POKEMON_EXISTED(1003, "Pokemon already exist"),
    UNAUTHENTICATED(1004, "Unauthenticated user");
    int code;
    String message;
    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
