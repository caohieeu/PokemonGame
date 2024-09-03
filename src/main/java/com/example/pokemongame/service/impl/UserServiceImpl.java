package com.example.pokemongame.service.impl;

import com.example.pokemongame.converter.UserConverter;
import com.example.pokemongame.dto.request.UserDTORequest;
import com.example.pokemongame.dto.response.UserDTOResponse;
import com.example.pokemongame.repository.UserRepository;
import com.example.pokemongame.repository.entity.User;
import com.example.pokemongame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserConverter userConverter;
    @Override
    public void addUser(UserDTORequest userDTORequest) {
        User user = userRepository.find
    }
    @Override
    public List<UserDTOResponse> getUsers(Map<String, Object> params) {
        String username = params.get("username").toString();
        List<User> listUser = userRepository.findByUsernameContaining(username);
        return userConverter.toUserDTO(listUser);
    }
}
