package com.example.pokemongame.controller;

import com.example.pokemongame.dto.request.UserDTORequest;
import com.example.pokemongame.dto.response.ApiResponse;
import com.example.pokemongame.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping
    public ResponseEntity<?> getUsers(
            @RequestParam Map<String, Object> params
            ) {
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .code(2000)
                        .message("Get users successfully")
                        .data(userService.getUsers(params))
                        .build()
        );
    }
    @PostMapping
    public ResponseEntity<?> addUser(
            @RequestBody UserDTORequest userDTORequest
            ) {
        userService.addUser(userDTORequest);
        return ResponseEntity.ok().body("Created");
    }
}
