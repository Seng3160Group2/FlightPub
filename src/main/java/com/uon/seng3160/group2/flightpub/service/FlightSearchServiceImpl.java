package com.uon.seng3160.group2.flightpub.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uon.seng3160.group2.flightpub.entity.Destination;
import com.uon.seng3160.group2.flightpub.entity.Flight;
import com.uon.seng3160.group2.flightpub.entity.compositekey.FlightId;
import com.uon.seng3160.group2.flightpub.repository.DestinationRepository;
import com.uon.seng3160.group2.flightpub.repository.FlightRepository;

@Service
@Transactional
public class FlightSearchServiceImpl implements FlightSearchService {
    @Autowired
    private final FlightRepository flightRepository;
    @Autowired
    private final DestinationRepository destinationRepository;

    public FlightSearchServiceImpl(FlightRepository flightRepository, DestinationRepository destinationRepository) {
        this.flightRepository = flightRepository;
        this.destinationRepository = destinationRepository;
    }

    public Optional<Flight> getFlight(String airlineCode, String flightNumber, LocalDateTime departureTime) {
        FlightId flightId = new FlightId(airlineCode, flightNumber, departureTime);

        return this.flightRepository.findById(flightId);
    }

    public List<List<Flight>> getFlight(String start, String end, LocalDateTime departureTime, LocalDateTime arrivalTime, boolean returnFlight) {
        List<List<Flight>> flights = new ArrayList<List<Flight>>();

        Optional<Destination> optDestination = destinationRepository.findById(start);

        if (optDestination.isEmpty()){
            return flights;
        }
        Destination tempDestination = optDestination.get();
        if (returnFlight){
            flights.add(this.flightRepository.findTop10ByDeparture(tempDestination));
            flights.add(this.flightRepository.findTop10ByDeparture(tempDestination));
            return flights;
        }

        flights.add(this.flightRepository.findTop10ByDeparture(tempDestination));
        flights.add(new ArrayList<Flight>());
        return flights;

    }
    public List<List<Flight>> getReturnFlight(String start, boolean returnFlight){
        List<List<Flight>> flights = new ArrayList<List<Flight>>();
        return flights;
    };
}
