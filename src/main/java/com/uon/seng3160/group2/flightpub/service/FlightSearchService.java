package com.uon.seng3160.group2.flightpub.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.uon.seng3160.group2.flightpub.entity.Destination;
import com.uon.seng3160.group2.flightpub.entity.Flight;

public interface FlightSearchService {
    public Optional<Flight> getFlight(String airlineCode, String flightNumber, LocalDateTime departureTime);
    public List<List<Flight>> getFlight(String start, String end, LocalDateTime departureTime, LocalDateTime arrivalTime, boolean returnFlight);
    //public Optional<Flight> getByDepartureAndDestinationAndStopOver(String departure, String destination, String stopOver);

}
