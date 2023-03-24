package com.bezkoder.springjwt.client.converter;

import com.bezkoder.springjwt.account.converter.UserConverter;
import com.bezkoder.springjwt.client.dto.ClientDto;
import com.bezkoder.springjwt.client.dto.ClientDtoRequest;
import com.bezkoder.springjwt.client.dto.ClientRequestForFlightDto;
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
        client.setFlagDeleted(Boolean.FALSE);

        return client;
    }
    public static Client toFlightEntity(ClientRequestForFlightDto dto){
        Client client = new Client();
        client.setEmail(dto.getEmail());


        return client;
    }

    public static Client toClientRequestEntity(ClientDtoRequest dto){
        Client client = new Client();
        client.setFullName(dto.getFullName());
        client.setEmail(dto.getEmail());
        client.setNid(dto.getNid());
        client.setFlagDeleted(Boolean.FALSE);
        return client;
    }
    public static List<Client> toEntityList(List<ClientDto>dtoList){
        List<Client> clientList = new ArrayList<>();
        for (ClientDto dto:dtoList){
            clientList.add(toEntity(dto));
        }
        return clientList;
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
