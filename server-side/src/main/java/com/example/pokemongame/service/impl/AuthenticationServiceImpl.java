package com.example.pokemongame.service.impl;

import com.example.pokemongame.converter.custom.UserConverterCustom;
import com.example.pokemongame.dto.request.AuthenticateRequest;
import com.example.pokemongame.dto.request.RegisterRequest;
import com.example.pokemongame.dto.response.AuthenticateResponse;
import com.example.pokemongame.dto.response.IntrospectResponse;
import com.example.pokemongame.dto.response.UserDTOResponse;
import com.example.pokemongame.enums.Role;
import com.example.pokemongame.exception.AppException;
import com.example.pokemongame.exception.ErrorCode;
import com.example.pokemongame.repository.UserRepository;
import com.example.pokemongame.repository.entity.User;
import com.example.pokemongame.service.AuthenticationService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.Collator;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    UserConverterCustom userConverterCustom;
    @NonFinal
    String SIGN_KEY =
            "bmQ2ajMzOWttMWxpdTQwaTd3Njl0MmQ1cTFsaHBmYzRpcmllZmp4ZjZhaXU1aGkyeTMyeWgwbXFkYmF2ams2cGcxb2s0OTA5M2JpOGUyOGVsZmVjN25zMjMyYzBkcm9wNTh5djNjZ25qdnc1cnYwNnNuY3RuZTc3a2JmcXhxeWM=";
    @Override
    public AuthenticateResponse authenticate(AuthenticateRequest authenticateRequest) {
        User user = userRepository.findByUsername(authenticateRequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        boolean chkPassword = passwordEncoder.matches(authenticateRequest.getPassword(),
                user.getPassword());
        if(chkPassword) {
            String token = generateToken(user);
            return AuthenticateResponse.builder()
                    .authenticated(chkPassword)
                    .token(token)
                    .build();
        }
        throw new AppException(ErrorCode.UNAUTHENTICATED);
    }

    private String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("pokemongame.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope", buildScope(user.getRole()))
                .build();
        Payload payload = jwtClaimsSet.toPayload();
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGN_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
    private String buildScope(Set<String> roles) {
        String scope = "";
        if(!CollectionUtils.isEmpty(roles)) {
            scope = String.join(" ", roles);
        }
        return scope;
    }
    public IntrospectResponse introspectToken(String tokenRequest) throws ParseException, JOSEException {
        SignedJWT token = SignedJWT.parse(tokenRequest);
        Date expiryTime = token.getJWTClaimsSet().getExpirationTime();
        JWSVerifier verifier = new MACVerifier(SIGN_KEY.getBytes());
        return IntrospectResponse.builder()
                .valid(token.verify(verifier) && expiryTime.after(new Date()))
                .build();
    }
    @Override
    public UserDTOResponse register(RegisterRequest registerRequest) {
        if(userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userConverterCustom.toUserEntity(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRole(roles);
        return userConverterCustom.toUserDTOResponse(
                userRepository.save(user)
        );
    }
}
