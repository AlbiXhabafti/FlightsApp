package com.bezkoder.springjwt.account.services.impl;

import com.bezkoder.springjwt.account.dto.LoginRequest;
import com.bezkoder.springjwt.account.dto.UserDto;
import com.bezkoder.springjwt.account.dto.response.JwtResponse;
import com.bezkoder.springjwt.account.models.User;
import com.bezkoder.springjwt.account.repository.RoleRepository;
import com.bezkoder.springjwt.account.repository.UserRepository;
import com.bezkoder.springjwt.account.security.UserDetailsImpl;
import com.bezkoder.springjwt.account.security.jwt.JwtUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceImplTest {
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    public void testSignIn() {
        // create mock objects
        LoginRequest loginRequest = new LoginRequest("username", "password");
        UserDetailsImpl userDetails = new UserDetailsImpl("email", "password", new ArrayList<>());
        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(jwtUtils.generateJwtToken(authentication)).thenReturn("jwtToken");


        // perform the test
       // JwtResponse jwtResponse = authService.signIn(loginRequest);

        // assert the results
//        assertEquals("jwtToken", jwtResponse.getToken());
//        assertEquals("email", jwtResponse.getEmail());
//        assertTrue(jwtResponse.getRoles().isEmpty());
    }

    @Test
    public void testSignUp() throws NullPointerException {
        // create mock objects
        UserDto userDto = new UserDto("username", "password", new ArrayList<>());
        User user = new User("username", "password", new ArrayList<>());
        when(userRepository.existsByUsername(userDto.getUsername())).thenReturn(false);

        // perform the test
        String result = authService.signUp(userDto);

        // assert the results
        assertEquals("signUp of username with role [] is done  ", result);
        verify(userRepository, times(1)).save(user);
    }

    @Test(expected = RuntimeException.class)
    public void testSignUpWithExistingUsername() {
        // create mock objects
        UserDto userDto = new UserDto("username", "password", new ArrayList<>());
        when(userRepository.existsByUsername(userDto.getUsername())).thenReturn(true);

        // perform the test
        authService.signUp(userDto);
    }

}