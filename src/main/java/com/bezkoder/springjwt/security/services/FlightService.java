package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Flight;
import com.bezkoder.springjwt.repository.FlightRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FlightService {
    FlightRepo flightRepo;

    public Iterable<Flight>getAllights(){
        return flightRepo.findAll();
    }
}
