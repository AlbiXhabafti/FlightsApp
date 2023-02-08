package com.bezkoder.springjwt.flight.service;

import com.bezkoder.springjwt.flight.dto.FlightDto;

import java.util.List;

public interface FlightService {
    public List<FlightDto> getAllFlights();
    public String addFlight(FlightDto dto);
}
