package com.bezkoder.springjwt.flight.repository;

import com.bezkoder.springjwt.flight.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    public Flight findByFlightNumber(String flightNumber);
}