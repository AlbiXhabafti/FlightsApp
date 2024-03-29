package com.bezkoder.springjwt.account.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDto {
    private String username;
    private String email;
    private String password;
    private List<RoleDto> roleDto;

    public <E> UserDto(String username, String password, ArrayList<E> es) {
    }

    public UserDto() {

    }
}
