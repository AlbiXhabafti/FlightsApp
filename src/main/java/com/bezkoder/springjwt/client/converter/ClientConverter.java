package com.bezkoder.springjwt.client.converter;

import com.bezkoder.springjwt.account.converter.UserConverter;
import com.bezkoder.springjwt.client.dto.ClientDto;
import com.bezkoder.springjwt.client.dto.ClientResponseDto;
import com.bezkoder.springjwt.client.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientConverter {

    public static Client toEntity(ClientDto dto){
        Client client = new Client();
        client.setFullName(dto.getFullName());
        client.setEmail(dto.getEmail());
        client.setNid(dto.getNid());

        return client;
    }
    public static ClientResponseDto toDto(Client client){
        ClientResponseDto dto = new ClientResponseDto();
        dto.setNid(client.getNid());
        dto.setFullName(client.getFullName());
        dto.setCreatedBy(UserConverter.toUserDto(client.getCreatedBy()));
        return dto;
    }
    public static List<ClientResponseDto>toDtoList(List<Client>clientList){
        List<ClientResponseDto>dtoList = new ArrayList<>();
        for (Client client:clientList){
            dtoList.add(toDto(client));
        }
        return dtoList;
    }


}