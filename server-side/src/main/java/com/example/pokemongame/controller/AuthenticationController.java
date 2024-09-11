package com.example.pokemongame.controller;

import com.example.pokemongame.dto.request.AuthenticateRequest;
import com.example.pokemongame.dto.request.IntrospectRequest;
import com.example.pokemongame.dto.request.RegisterRequest;
import com.example.pokemongame.dto.response.ApiResponse;
import com.example.pokemongame.dto.response.AuthenticateResponse;
import com.example.pokemongame.dto.response.IntrospectResponse;
import com.example.pokemongame.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid
            @RequestBody RegisterRequest registerRequest
            ) {
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .code(2000)
                        .message("Register successfully")
                        .data(authenticationService.register(registerRequest))
                        .build()
        );
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody AuthenticateRequest authenticateRequest,
            HttpServletResponse response
    ) {
        String token = authenticationService.authenticate(authenticateRequest).getToken();
        Cookie cookie = new Cookie("auth_token", token);
        cookie.setHttpOnly(false);
        cookie.setSecure(false);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.<AuthenticateResponse>ok()
                .body(ApiResponse.builder()
                        .code(2000)
                        .message("Login successfully")
                        .data(authenticationService.authenticate(authenticateRequest))
                        .build());
    }
    @PostMapping("/introspect")
    public ResponseEntity<?> introspect(
            @RequestBody IntrospectRequest introspectRequest
    ) throws ParseException, JOSEException {
        return ResponseEntity.ok()
                .body(ApiResponse.builder()
                        .code(2000)
                        .data(authenticationService.introspectToken(introspectRequest.getToken()))
                        .build());
    }
}
