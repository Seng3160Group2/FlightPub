package com.uon.seng3160.group2.flightpub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uon.seng3160.group2.flightpub.entity.Destination;
import com.uon.seng3160.group2.flightpub.entity.Flight;
import com.uon.seng3160.group2.flightpub.entity.compositekey.FlightId;

public interface FlightRepository extends JpaRepository<Flight, FlightId> {

    //List<Flight> getByDepartureAndDestinationAndStopOver(Destination departure, Destination destination, Destination stopOver);
    //methods for both one way and two way flights
    public List<Flight> findTop10ByDeparture(Destination departure);
}
 