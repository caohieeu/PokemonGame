package com.example.pokemongame.service.impl;

import com.example.pokemongame.converter.custom.UserConverterCustom;
import com.example.pokemongame.dto.request.UserDTORequest;
import com.example.pokemongame.dto.response.UserDTOResponse;
import com.example.pokemongame.exception.AppException;
import com.example.pokemongame.exception.ErrorCode;
import com.example.pokemongame.repository.UserRepository;
import com.example.pokemongame.repository.entity.User;
import com.example.pokemongame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserConverterCustom userConverterCustom;
    @Override
    @PostAuthorize("hasRole('ADMIN')")
    public void addUser(UserDTORequest userDTORequest) {
        User user = userRepository.findByUsername(userDTORequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED));
        userRepository.insert(userConverterCustom.toUserEntity(userDTORequest));
    }
    @Override
    @PostAuthorize("hasRole('ADMIN')")
    public List<UserDTOResponse> getUsers(Map<String, Object> params) {
        String username = params.get("username")==null?null:params.get("username").toString();
        List<User> listUser = new ArrayList<>();
        if(username != null) {
            listUser = userRepository.findByUsernameContaining(username);
        }
        else {
            listUser = userRepository.findAll();
        }
        return userConverterCustom.toUserDTO(listUser);
    }

    @Override
    @PostAuthorize("returnObject.username == authentication.name && hasRole('ADMIN')")
    public UserDTOResponse getUser(String userId) {
        UserDTOResponse user = new UserDTOResponse();
        if(userId != null && !userId.equals("")) {
            user = userConverterCustom.toUserDTOResponse(
                    userRepository.findById(userId)
            );
        }
        return user;
    }

    @Override
    public UserDTOResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        return userConverterCustom.toUserDTOResponse(
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND))
        );
    }

    @Override
    public void updateUser(UserDTORequest userDTORequest) {
        User user = userRepository.findById(userDTORequest.getId()).orElse(null);
        if(user == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        user.setDisplayName(userDTORequest.getDisplayName());
        user.setEmail(userDTORequest.getEmail());
        user.setAvatar(userDTORequest.getAvatar());
        user.setPhone(userDTORequest.getPhone());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        userRepository.delete(user);
    }
}
