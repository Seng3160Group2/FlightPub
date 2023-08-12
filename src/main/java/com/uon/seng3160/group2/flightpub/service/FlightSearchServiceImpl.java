package com.uon.seng3160.group2.flightpub.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uon.seng3160.group2.flightpub.entity.Flight;
import com.uon.seng3160.group2.flightpub.entity.compositekey.FlightId;
import com.uon.seng3160.group2.flightpub.repository.FlightRepository;

@Service
@Transactional
public class FlightSearchServiceImpl implements FlightSearchService {
    @Autowired
    private final FlightRepository flightRepository;

    public FlightSearchServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Optional<Flight> getFlight(String airlineCode, String flightNumber, LocalDateTime departureTime) {
        FlightId flightId = new FlightId(airlineCode, flightNumber, departureTime);

        return this.flightRepository.findById(flightId);
    }
}
