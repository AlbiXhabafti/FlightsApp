package com.bezkoder.springjwt.client.service;

import com.bezkoder.springjwt.client.dto.ClientDto;
import com.bezkoder.springjwt.client.dto.ClientResponseDto;

import java.util.List;

public interface ClientService {
    public String addClient(ClientDto clientDto);
    public List<ClientResponseDto>getAll();
    public String deleteById(Long id);
}
