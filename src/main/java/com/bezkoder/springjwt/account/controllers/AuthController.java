package com.bezkoder.springjwt.account.controllers;

import javax.validation.Valid;

import com.bezkoder.springjwt.account.dto.UserDto;
import com.bezkoder.springjwt.account.services.impl.AuthServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.account.dto.LoginRequest;
import com.bezkoder.springjwt.account.dto.response.JwtResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {

  @Autowired
  private AuthServiceImpl authService;


  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    return  ResponseEntity.ok(authService.signIn(loginRequest));
  }

  @PostMapping("/signup")
  public String signUp(@RequestBody UserDto userDto){

    return authService.signUp(userDto);
  }


}
