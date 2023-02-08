package com.bezkoder.springjwt.account.converter;

import com.bezkoder.springjwt.account.dto.RoleDto;
import com.bezkoder.springjwt.account.models.Role;
import com.bezkoder.springjwt.account.models.RoleEnum;
import com.bezkoder.springjwt.account.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class RoleConverter {
    public static Role toEntity(RoleDto dto) {
        Role role = new Role();
        role.setRoleEnum(RoleEnum.valueOf(dto.getRoleEnum()));
        return role;
    }

    public static List<Role> toListEntity(List<RoleDto> dtoList) {
        List<Role> roles = new ArrayList<>();
        for (RoleDto dto : dtoList) {
            roles.add(toEntity(dto));
            }
        return roles;
    }
    public static RoleDto toDto(Role role){
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleEnum(String.valueOf(role.getRoleEnum()));
        return roleDto;
    }


    public static  List<RoleDto>toListDto(List<Role>roleList){
        List<RoleDto>dtoList = new ArrayList<>();
        if (!roleList.isEmpty()){
            for (Role role:roleList){
                dtoList.add(toDto(role));
            }
        }
        return dtoList;
    }
}
