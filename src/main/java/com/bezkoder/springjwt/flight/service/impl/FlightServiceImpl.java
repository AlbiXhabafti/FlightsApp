package com.bezkoder.springjwt.flight.service.impl;

import com.bezkoder.springjwt.account.models.User;
import com.bezkoder.springjwt.account.services.impl.UserDetailsServiceImpl;
import com.bezkoder.springjwt.client.converter.ClientConverter;
import com.bezkoder.springjwt.client.dto.ClientRequestForFlightDto;
import com.bezkoder.springjwt.client.model.Client;
import com.bezkoder.springjwt.client.repository.ClientRepository;
import com.bezkoder.springjwt.flight.converter.FlightConverter;
import com.bezkoder.springjwt.flight.dto.FlightDto;
import com.bezkoder.springjwt.flight.dto.FlightDtoResponse;
import com.bezkoder.springjwt.flight.model.Flight;
import com.bezkoder.springjwt.flight.repository.FlightRepository;
import com.bezkoder.springjwt.flight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {
    @Autowired
    FlightRepository flightRepository;

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public List<FlightDtoResponse> getAllFlights() {
       List<Flight>flightDtoResponses = flightRepository.findAll();

       return FlightConverter.toListDto(flightDtoResponses);
    }

    @Override
    public String createFlight(FlightDto dto) {
        Flight flight = FlightConverter.toEntity(dto);
        Client client = clientRepository.findByFullName(dto.getClientRequestForFlightDtos().getFullName());

        flight.setClient(client);

        flightRepository.save(flight);

        return "new flight is added";
    }

    @Override
    public String deleteById(Long id) {
        flightRepository.deleteById(id);
        return "client with ID: " +id +" is deleted";

    }
}
