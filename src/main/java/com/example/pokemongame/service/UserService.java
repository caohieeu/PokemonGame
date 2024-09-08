package com.example.pokemongame.service;

import com.example.pokemongame.dto.request.UserDTORequest;
import com.example.pokemongame.dto.response.UserDTOResponse;

import java.util.List;
import java.util.Map;

public interface UserService {
    void addUser(UserDTORequest userDTORequest);
    List<UserDTOResponse> getUsers(Map<String, Object> params);
    UserDTOResponse getUser(String userId);
    UserDTOResponse getMyInfo();
    void updateUser(UserDTORequest userDTORequest);
    void deleteUser(String userId);
}
