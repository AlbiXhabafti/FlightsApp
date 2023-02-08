package com.bezkoder.springjwt.account.services;

import com.bezkoder.springjwt.account.dto.LoginRequest;
import com.bezkoder.springjwt.account.dto.UserDto;
import com.bezkoder.springjwt.account.dto.response.JwtResponse;

public interface AuthenticationService {
    public JwtResponse signIn(LoginRequest loginRequest);
    public String signUp(UserDto userDto);

}
