package com.uon.seng3160.group2.flightpub.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.uon.seng3160.group2.flightpub.entity.Destination;
import com.uon.seng3160.group2.flightpub.entity.Flight;

public interface FlightSearchService {
    public Optional<Flight> getFlight(String airlineCode, String flightNumber, LocalDateTime departureTime);

    public List<List<List<Flight>>> getFlights(String departureAirport, String destinationAirport,
            LocalDateTime departureTime, LocalDateTime returnTime, boolean isReturn);

    public List<List<Flight>> findAllFlights(Destination departure, Destination destination,
            LocalDateTime departureTime, int numPaths);
}
