package com.bezkoder.springjwt.flight.service.impl;

import com.bezkoder.springjwt.account.models.User;
import com.bezkoder.springjwt.flight.converter.FlightConverter;
import com.bezkoder.springjwt.flight.dto.FlightDto;
import com.bezkoder.springjwt.flight.model.Flight;
import com.bezkoder.springjwt.flight.repository.FlightRepository;
import com.bezkoder.springjwt.flight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {
    @Autowired
    FlightRepository flightRepository;

    @Override
    public List<FlightDto> getAllFlights() {
       List<Flight>flightList = flightRepository.findAll().stream().collect(Collectors.toList());
       return FlightConverter.toListDto(flightList);
    }

    @Override
    public String addFlight(FlightDto dto ) {
    Flight flight = FlightConverter.toEntity(dto);

       flightRepository.save(flight);
        return "new flight is added";
    }
}
