package com.bezkoder.springjwt.flight.controller;

import com.bezkoder.springjwt.account.security.CurrentUser;
import com.bezkoder.springjwt.account.security.UserDetailsImpl;
import com.bezkoder.springjwt.flight.dto.FlightDto;
import com.bezkoder.springjwt.flight.dto.FlightDtoResponse;
import com.bezkoder.springjwt.flight.model.Flight;
import com.bezkoder.springjwt.flight.repository.FlightRepository;
import com.bezkoder.springjwt.flight.service.FlightService;
import com.bezkoder.springjwt.flight.service.impl.FlightServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping(path = "/getAllFlights")
    public List<FlightDtoResponse> getAllFlights(){
        return flightService.getAllFlights();
    }
    @PostMapping(path = "/addFlight")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String addFlight(@RequestBody FlightDto flightDto){
        return flightService.createFlight(flightDto);
    }
    @DeleteMapping("/deleteById/{id}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String deleteClientById(@PathVariable(name = "id")Long id){
        return flightService.deleteById(id);
    }



}