package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepo extends JpaRepository<Flight,Long> {
}