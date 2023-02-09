package com.bezkoder.springjwt.client.contoller;

import com.bezkoder.springjwt.client.dto.ClientDto;
import com.bezkoder.springjwt.client.dto.ClientResponseDto;
import com.bezkoder.springjwt.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/addClient")
    public String addClient(@RequestBody ClientDto clientDto){
        return clientService.addClient(clientDto);
    }

    @GetMapping("/getAll")
    public List<ClientResponseDto>getAllFlights(){
        return clientService.getAll();
    }

    @DeleteMapping("/deleteById/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteClientById(@PathVariable(name = "id")Long id){
        return clientService.deleteById(id);
    }



}
