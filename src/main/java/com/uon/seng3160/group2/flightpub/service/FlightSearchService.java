package com.uon.seng3160.group2.flightpub.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.uon.seng3160.group2.flightpub.entity.Flight;

public interface FlightSearchService {
    public Optional<Flight> getFlight(String airlineCode, String flightNumber, LocalDateTime departureTime);
}
