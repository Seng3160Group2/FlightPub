package com.uon.seng3160.group2.flightpub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uon.seng3160.group2.flightpub.entity.Flight;
import com.uon.seng3160.group2.flightpub.entity.compositekey.FlightId;

public interface FlightRepository extends JpaRepository<Flight, FlightId> {

}
