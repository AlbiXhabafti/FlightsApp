package com.bezkoder.springjwt.client.service;

import com.bezkoder.springjwt.client.dto.ClientDto;
import com.bezkoder.springjwt.client.dto.ClientDtoRequest;
import com.bezkoder.springjwt.client.dto.ClientResponseDto;

import java.util.List;

public interface ClientService {
    public List<ClientResponseDto>getAll(int page, int size);
    public String deleteById(Long id);
    public String updateClient(Long id, ClientDto dto );

    public String createClient(ClientDtoRequest clientDtoRequest);
}
