package com.bezkoder.springjwt.client.service.impl;

import com.bezkoder.springjwt.account.converter.UserConverter;
import com.bezkoder.springjwt.account.models.User;
import com.bezkoder.springjwt.account.repository.UserRepository;
import com.bezkoder.springjwt.client.converter.ClientConverter;
import com.bezkoder.springjwt.client.dto.ClientDto;
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
    private JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;


    public String sendSimpleMail(String email) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(email);
            mailMessage.setText(" This message means that you are a dedicated follower of Big Brother ALBANIA #meLuizin  ");
            mailMessage.setSubject("Lion-Team");
            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        } catch (Exception e) {
            return "Error while Sending Mail";
        }
    }
    @Override
    public String addClient(ClientDto clientDto) {

        Client client = ClientConverter.toEntity(clientDto);
        User user = userRepository.findByEmail(clientDto.getCreatedBy().getEmail());
        if (user == null ){
            user = UserConverter.toEntity(clientDto.getCreatedBy());
            user.setPassword(passwordEncode.encode(user.getPassword()));
            client.setCreatedBy(user);

        }else{
            client.setCreatedBy(user);
        }
        if (clientRepository.existsByEmail(client.getEmail())){
            client.setFullName(clientDto.getFullName());
            client.setEmail(clientDto.getEmail());
        }
        clientRepository.save(client);


      //  sendSimpleMail(client.getEmail());clientRepository.save(client);
        return "client is registered ";
    }

    @Override
    public List<ClientResponseDto> getAll(int page, int size) {
        Page<Client> allClient = clientRepository.findAll(PageRequest.of(page,size));
        List<Client>clientList = allClient.getContent();

        List<ClientResponseDto>dtoList = ClientConverter.toDtoList(clientList);
        return dtoList;
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

        return "Updating Client with id" + id +" is done";
    }
}
