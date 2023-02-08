package com.bezkoder.springjwt.account.converter;

import com.bezkoder.springjwt.account.dto.UserDto;
import com.bezkoder.springjwt.account.models.Role;
import com.bezkoder.springjwt.account.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {

    public static User toEntity(UserDto dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRoles(RoleConverter.toListEntity(dto.getRoleDto()));
        return user;
    }
    public static UserDto toDto(User user){
        UserDto dto = new UserDto();
        dto.setUsername(user.getUsername());
        dto.setEmail(dto.getEmail());
        dto.setRoleDto(RoleConverter.toListDto(user.getRoles()));

        return dto;
    }
    public static List<UserDto> toListDto(List<User>users){
        List<UserDto>userDtoList = new ArrayList<>();
        for (User user:users){
                userDtoList.add(toDto(user));
            }
        return userDtoList;
        }

    public static List<User> toListEntity(List<UserDto>users){
        List<User>userList = new ArrayList<>();
        for (UserDto user:users){
            userList.add(toEntity(user));
        }
        return userList;
    }

    }

