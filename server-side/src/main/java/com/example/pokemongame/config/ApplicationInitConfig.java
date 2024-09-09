package com.example.pokemongame.config;

import com.example.pokemongame.enums.Role;
import com.example.pokemongame.repository.UserRepository;
import com.example.pokemongame.repository.entity.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Configuration
public class ApplicationInitConfig {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            if(!userRepository.existsByUsername("admin")) {
                HashSet<String> roles = new HashSet<>();
                roles.add(Role.ADMIN.name());
                userRepository.insert(User.builder()
                                .username("admin")
                                .password(passwordEncoder.encode("admin123"))
                                .role(roles)
                                .build());
            }
        };
    }
}
