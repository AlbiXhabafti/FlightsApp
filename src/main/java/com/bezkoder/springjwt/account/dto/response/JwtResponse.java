package com.bezkoder.springjwt.account.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class JwtResponse {
  private String token;
  private String email;
  private List<String> roles;

  public JwtResponse(String accessToken, String email, List<String> roles) {
    this.token = accessToken;
    this.email = email;
    this.roles = roles;
  }
}
