package com.bezkoder.springjwt.client.dto;

import com.bezkoder.springjwt.account.dto.UserRequestDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDtoRequest {
    private String fullName;

    private String email;

    private String nid;

    private UserRequestDto userRequestDto;
}
