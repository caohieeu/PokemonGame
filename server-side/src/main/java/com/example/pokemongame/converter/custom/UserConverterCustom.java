package com.example.pokemongame.converter.custom;

import com.example.pokemongame.dto.request.RegisterRequest;
import com.example.pokemongame.dto.request.UserDTORequest;
import com.example.pokemongame.dto.response.UserDTOResponse;
import com.example.pokemongame.repository.entity.User;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class UserConverterCustom {
    @Autowired
    ModelMapper modelMapper;
    public <T> User toUserEntity(T dto) {
        User user = modelMapper.map(dto, User.class);
        user.setDateCreated(new Date());
        return user;
    }
    public <T> UserDTOResponse toUserDTOResponse(T dto) {
        UserDTOResponse user = modelMapper.map(dto, UserDTOResponse.class);
        user.setDateCreated(new Date());
        return user;
    }

    public List<UserDTOResponse> toUserDTO(List<User> listUser) {
        List<UserDTOResponse> listUserResponse = new ArrayList<>();
        for(User user : listUser) {
            listUserResponse.add(modelMapper.map(user, UserDTOResponse.class));
        }
        return listUserResponse;
    }
}
