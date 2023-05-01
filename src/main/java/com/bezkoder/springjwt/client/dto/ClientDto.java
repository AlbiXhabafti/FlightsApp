package com.bezkoder.springjwt.client.dto;

import com.bezkoder.springjwt.account.dto.UserDto;
import com.bezkoder.springjwt.account.dto.UserRequestDto;
import lombok.Data;

@Data
public class ClientDto {

    private String fullName;

    private String email;

    private String nid;

    private UserRequestDto createdBy;

}
