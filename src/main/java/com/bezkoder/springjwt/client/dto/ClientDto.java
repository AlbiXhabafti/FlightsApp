package com.bezkoder.springjwt.client.dto;

import com.bezkoder.springjwt.account.dto.UserDto;
import lombok.Data;

@Data
public class ClientDto {

    private String fullName;

    private String email;

    private String nid;

    private UserDto createdBy;

}
