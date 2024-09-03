package com.example.pokemongame.controllerAdvice;

import com.example.pokemongame.dto.response.ApiResponse;
import com.example.pokemongame.exception.AppException;
import com.example.pokemongame.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> handlerAppException(
            AppException ex
    ) {
        ErrorCode errorCode = ex.getErrorCode();
        return ResponseEntity.badRequest().body(
                ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
        );
    }
}
