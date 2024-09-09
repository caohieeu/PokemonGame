package com.example.pokemongame.service;

import com.example.pokemongame.dto.request.AuthenticateRequest;
import com.example.pokemongame.dto.request.RegisterRequest;
import com.example.pokemongame.dto.request.UserDTORequest;
import com.example.pokemongame.dto.response.AuthenticateResponse;
import com.example.pokemongame.dto.response.IntrospectResponse;
import com.example.pokemongame.dto.response.UserDTOResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticateResponse authenticate(AuthenticateRequest authenticateRequest);
    UserDTOResponse register(RegisterRequest registerRequest);
    IntrospectResponse introspectToken(String tokenRequest) throws ParseException, JOSEException;
}
