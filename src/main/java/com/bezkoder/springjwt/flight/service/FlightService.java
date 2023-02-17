package com.bezkoder.springjwt.flight.service;

import com.bezkoder.springjwt.flight.dto.FlightDto;
import com.bezkoder.springjwt.flight.dto.FlightDtoResponse;

import java.util.List;

public interface FlightService {
    public List<FlightDtoResponse> getAllFlights();
    public String createFlight(FlightDto flightDto);
}
