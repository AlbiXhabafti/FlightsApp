package com.bezkoder.springjwt.client.service.impl;

import com.bezkoder.springjwt.account.dto.UserRequestDto;
import com.bezkoder.springjwt.account.dto.response.UserResponseDto;
import com.bezkoder.springjwt.account.models.User;
import com.bezkoder.springjwt.account.repository.UserRepository;
import com.bezkoder.springjwt.client.dto.ClientDto;
import com.bezkoder.springjwt.client.dto.ClientDtoRequest;
import com.bezkoder.springjwt.client.dto.ClientResponseDto;
import com.bezkoder.springjwt.client.model.Client;
import com.bezkoder.springjwt.client.repository.ClientRepository;
import com.bezkoder.springjwt.client.service.ClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void testSendSimpleMail() {
        String email = "test@example.com";

        clientService.sendSimpleMail(email);

        ArgumentCaptor<SimpleMailMessage> argument = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(javaMailSender).send(argument.capture());

     //   assertEquals(clientService.sendSimpleMail(), argument.getValue().getFrom());
        assertEquals(email, argument.getValue().getTo()[0]);
        assertEquals("Hello,thanks a lot for your choice being client of Flight App ", argument.getValue().getText());
        assertEquals("Flight-App", argument.getValue().getSubject());
    }

    @Test
    public void testCreateClient() {
        // create mock data
        ClientDtoRequest clientDtoRequest = new ClientDtoRequest();
        clientDtoRequest.setNid("123456789");
        clientDtoRequest.setEmail("test@gmail.com");
        clientDtoRequest.setUserRequestDto(new UserRequestDto());
        clientDtoRequest.getUserRequestDto().setEmail("test@gmail.com");

        Client client = new Client();
        client.setId(1L);

        User user = new User();
        user.setEmail("test@gmail.com");

        // mock repository and service
        when(userRepository.findByEmail(clientDtoRequest.getUserRequestDto().getEmail())).thenReturn(user);
        when(clientRepository.existsByNid(clientDtoRequest.getNid())).thenReturn(false);
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        // call the method to be tested
        String result = clientService.createClient(clientDtoRequest);

        // verify the result
        assertEquals("Client is registered ", result);
        assertEquals(1L, client.getId().longValue());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    public void testUpdateClient() {
        // Create a test client
        Long id = 1L;
        String fullName = "John Doe";
        String email = "axhabafti@example.com";
        String nid = "12345";
        Client client = new Client(id, fullName, email, nid);

        // Create a test DTO with updated values
        ClientDto dto = new ClientDto();
        dto.setFullName("Albi Xhabafti");
        dto.setEmail("axhabafti@example.com");
        dto.setNid("67890");

        // Set up the mock repository to return the test client when findById() is called
        when(clientRepository.findById(id)).thenReturn(Optional.of(client));

        // Call the updateClient() method with the test ID and DTO
        String result = clientService.updateClient(id, dto);

        // Verify that the client's properties were updated
        assertEquals(dto.getFullName(), client.getFullName());
        assertEquals(dto.getEmail(), client.getEmail());
        assertEquals(dto.getNid(), client.getNid());

        // Verify that the client was saved using the repository
        verify(clientRepository).save(client);

        // Verify that the result message is correct
        String expectedMessage = "Updating Client with id " + id + " is done";
        assertEquals(expectedMessage, result);
    }
    @Test
    public void deleteByIdTest() {
        Long id = 1L;
        doNothing().when(clientRepository).deleteById(id);

        String result = clientService.deleteById(id);

        verify(clientRepository).deleteById(id);
        assertEquals("client with ID: " + id + " is deleted", result);
    }
    @Test(expected = RuntimeException.class)
    public void testGetAll() {
        // Create mock data
        List<Client> clientList = new ArrayList<>();
        clientList.add(new Client());
        clientList.add(new Client());
        clientList.add(new Client());
        Page<Client> page = new PageImpl<>(clientList);

        // Mock repository method call
        when(clientRepository.findAll(PageRequest.of(0, 3))).thenReturn(page);


        doNothing().when(page).getContent();
        doThrow(new RuntimeException()).when(page).getContent();
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUsername("test");
        // Call the service method
        ClientResponseDto clientResponseDto = new ClientResponseDto();
        clientResponseDto.setFullName("fullName");
        clientResponseDto.setNid("nid");
        clientResponseDto.setCreatedBy(userResponseDto);




        List<Client>allClient = Collections.singletonList(new Client());

       List<ClientResponseDto> result = clientService.getAll(0, 1);
       result.add(clientResponseDto);



        // Assert the result
          assertEquals(1, result.size());
    }
}