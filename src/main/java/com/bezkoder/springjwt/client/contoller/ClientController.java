package com.bezkoder.springjwt.client.contoller;

import com.bezkoder.springjwt.client.dto.ClientDto;
import com.bezkoder.springjwt.client.dto.ClientResponseDto;
import com.bezkoder.springjwt.client.service.ClientService;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/client")

public class ClientController {
    Logger logger = LoggerFactory.logger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/addClient")
    public String addClient(@RequestBody ClientDto clientDto){
         logger.info("adding client " +clientDto);
        return clientService.addClient(clientDto);
    }

    @GetMapping("/getAll/{page}/{size}")
    public List<ClientResponseDto>getAll(@PathVariable("page")int page, @PathVariable("size")int size){
        return clientService.getAll(page,size);
    }

    @DeleteMapping("/deleteById/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteClientById(@PathVariable(name = "id")Long id){
        return clientService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public String updateById(@PathVariable(name = "id")Long id, @RequestBody ClientDto clientDto){
        return clientService.updateClient(id,clientDto);
    }




}
