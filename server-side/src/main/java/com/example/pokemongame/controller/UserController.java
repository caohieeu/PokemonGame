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
    @GetMapping("/getAll")
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
    @GetMapping
    public ResponseEntity<?> getUser() {
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .code(2000)
                        .message("Get users successfully")
                        .data(userService.getUserByToken())
                        .build()
        );
    }
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(
            @PathVariable String userId
    ) {
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .code(2000)
                        .message("Get user successfully")
                        .data(userService.getUser(userId))
                        .build()
        );
    }
    @GetMapping("/myInfo")
    public ResponseEntity<?> getMyInfo() {
        return ResponseEntity.ok().body(
                ApiResponse.builder()
                        .code(2000)
                        .message("Get info successfully")
                        .data(userService.getMyInfo())
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
    @PutMapping
    public ResponseEntity<?> updateUser(
            @RequestBody UserDTORequest userDTORequest
    ) {
        userService.updateUser(userDTORequest);
        return ResponseEntity.ok().body("Updated");
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(
            @PathVariable("userId") String userId
    ) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().body("Deleted");
    }
}
