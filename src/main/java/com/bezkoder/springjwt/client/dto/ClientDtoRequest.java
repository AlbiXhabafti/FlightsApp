package com.bezkoder.springjwt.client.dto;

import com.bezkoder.springjwt.account.dto.UserRequestDto;
import com.bezkoder.springjwt.flight.dto.FlightDtoRequest;
import com.bezkoder.springjwt.flight.dto.FlightDtoResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ClientDtoRequest {
    private String fullName;

    private String email;

    private String nid;

    private UserRequestDto userRequestDto;
    private List<FlightDtoRequest>flightDtoRequests = new ArrayList<>();
}
