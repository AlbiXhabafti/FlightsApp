package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.BookedFlight;
import com.bezkoder.springjwt.models.Flight;
import com.bezkoder.springjwt.security.services.BookedService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@AllArgsConstructor
public class BookedController {

    BookedService bookedService;

    @GetMapping(path = "/getBookedFlight")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Iterable<BookedFlight> getAFlights() {
        return bookedService.get();
    }

    @PostMapping(path = "/addBookedFlight")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BookedFlight getAFlights(@RequestBody BookedFlight bookedFlight) {

        return bookedService.add(bookedFlight);
    }

    @GetMapping(path = "/getPageOne")
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<BookedFlight> getBooked (){
        return bookedService.getPageOne() ;
    }



}