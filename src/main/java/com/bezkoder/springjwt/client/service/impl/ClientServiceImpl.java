package com.bezkoder.springjwt.client.service.impl;

import com.bezkoder.springjwt.account.converter.UserConverter;
import com.bezkoder.springjwt.account.models.User;
import com.bezkoder.springjwt.account.repository.UserRepository;
import com.bezkoder.springjwt.client.converter.ClientConverter;
import com.bezkoder.springjwt.client.dto.ClientDto;
import com.bezkoder.springjwt.client.dto.ClientDtoRequest;
import com.bezkoder.springjwt.client.dto.ClientResponseDto;
import com.bezkoder.springjwt.client.model.Client;
import com.bezkoder.springjwt.client.repository.ClientRepository;
import com.bezkoder.springjwt.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    @Lazy
    @Autowired
    PasswordEncoder passwordEncode;


    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;




    public String sendSimpleMail(String email) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(email);
            mailMessage.setText("Hello,thanks a lot for your choice being client of Flight App ");
            mailMessage.setSubject("Flight-App");
            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        } catch (Exception e) {
            return "Error while Sending Mail";
        }
    }


    @Override
    public List<ClientResponseDto> getAll(int page, int size) {
        Page<Client> allClient = clientRepository.findAll(PageRequest.of(page,size));
        List<Client>clientList = allClient.getContent();

        List<ClientResponseDto>dtoList = ClientConverter.toDtoList(clientList);
        return dtoList;
    }

    @Override
    public String createClient(ClientDtoRequest clientDtoRequest) {
        Client client = ClientConverter.toClientRequestEntity(clientDtoRequest);
        User user = userRepository.findByEmail(clientDtoRequest.getUserRequestDto().getEmail());
        if (user == null){
            throw new RuntimeException("user doesn't exist, please try again");
        }
        if (clientRepository.existsByNid(clientDtoRequest.getNid())){
            Client existingClient = clientRepository.findByNid(clientDtoRequest.getNid());
            client.setId(existingClient.getId());
        }
        client.setCreatedBy(user);
        sendSimpleMail(clientDtoRequest.getEmail());
        clientRepository.save(client);
        return "Client is registered ";
    }

    @Override
    public String deleteById(Long id) {
        clientRepository.deleteById(id);
        return "client with ID: " +id +" is deleted";
    }

    @Override
    public String updateClient(Long id,ClientDto dto ) {
       Client client = clientRepository.findById(id).get();
       if (!client.getNid().equals(dto.getNid())){
           client.setNid(dto.getNid());
       }
       if (!client.getEmail().equals(dto.getEmail())){
           client.setEmail(dto.getEmail());
       }
       if (!client.getFullName().equals(dto.getFullName())){
           client.setFullName(dto.getFullName());
       }
       clientRepository.save(client);

        return "Updating Client with id " + id +" is done";
    }


}
