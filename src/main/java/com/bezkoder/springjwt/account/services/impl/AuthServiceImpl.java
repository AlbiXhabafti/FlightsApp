package com.bezkoder.springjwt.account.services.impl;

import com.bezkoder.springjwt.account.converter.UserConverter;
import com.bezkoder.springjwt.account.dto.LoginRequest;
import com.bezkoder.springjwt.account.dto.UserDto;
import com.bezkoder.springjwt.account.dto.response.JwtResponse;
import com.bezkoder.springjwt.account.models.User;
import com.bezkoder.springjwt.account.security.UserDetailsImpl;
import com.bezkoder.springjwt.account.repository.RoleRepository;
import com.bezkoder.springjwt.account.repository.UserRepository;
import com.bezkoder.springjwt.account.security.jwt.JwtUtils;
import com.bezkoder.springjwt.account.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthenticationService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    public JwtResponse signIn(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());


        return new JwtResponse(jwt, userDetails.getEmail(),
                roles);
    }

    public String signUp(UserDto userDto){
        User user = UserConverter.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setTokenCreationDate(LocalDateTime.now());
        if (userRepository.existsByUsername(userDto.getUsername())){
            throw new RuntimeException("username exist in database");
        }
        userRepository.save(user);
        return "signUp of "+userDto.getUsername()+" with role "+userDto.getRoleDto().stream().map(e->e.getRoleEnum()).collect(Collectors.toList())+" is done  ";
    }




}
