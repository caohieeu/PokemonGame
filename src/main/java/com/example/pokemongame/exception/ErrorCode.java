package com.example.pokemongame.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ErrorCode {
    USER_NOT_FOUND(1001, "User is not found");
    int code;
    String message;
    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
