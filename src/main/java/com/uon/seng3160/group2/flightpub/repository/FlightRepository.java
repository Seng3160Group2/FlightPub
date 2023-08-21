package com.uon.seng3160.group2.flightpub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uon.seng3160.group2.flightpub.entity.Flight;
import com.uon.seng3160.group2.flightpub.entity.compositekey.FlightId;

public interface FlightRepository extends JpaRepository<Flight, FlightId> {

    List<Flight> getByDepartureAndDestinationAndStopOver(String departure, String destination, String stopOver);

}
 