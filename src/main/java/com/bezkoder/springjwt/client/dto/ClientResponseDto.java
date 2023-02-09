package com.bezkoder.springjwt.client.dto;

import com.bezkoder.springjwt.account.dto.response.UserResponseDto;
import lombok.Data;

@Data
public class ClientResponseDto {
    private String fullName;
    private String nid;
    private UserResponseDto createdBy;
}
