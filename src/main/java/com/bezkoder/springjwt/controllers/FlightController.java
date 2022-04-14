package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Flight;
import com.bezkoder.springjwt.security.services.FlightService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class FlightController {
    FlightService flightService;
    @GetMapping(path = "/getAllFlights")
    public Iterable<Flight> getAFlights(){
        return flightService.getAllights();
    }


}