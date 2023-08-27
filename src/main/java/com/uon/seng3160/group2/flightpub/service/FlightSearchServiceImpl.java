package com.uon.seng3160.group2.flightpub.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uon.seng3160.group2.flightpub.entity.Destination;
import com.uon.seng3160.group2.flightpub.entity.Flight;
import com.uon.seng3160.group2.flightpub.entity.compositekey.FlightId;
import com.uon.seng3160.group2.flightpub.repository.FlightRepository;

@Service
@Transactional
public class FlightSearchServiceImpl implements FlightSearchService {
    @Autowired
    private final FlightRepository flightRepository;

    @Autowired
    private final DestinationService destinationService;

    @Autowired
    private final PathAlgorithm pathAlgorithm;

    @Autowired
    private final FlightAlgorithm flightAlgorithm;

    public FlightSearchServiceImpl(FlightRepository flightRepository, DestinationService destinationService,
            FlightAlgorithm flightAlgorithm, PathAlgorithm pathAlgorithm) {
        this.flightRepository = flightRepository;
        this.destinationService = destinationService;
        this.flightAlgorithm = flightAlgorithm;
        this.pathAlgorithm = pathAlgorithm;
    }

    public Optional<Flight> getFlight(String airlineCode, String flightNumber, LocalDateTime departureTime) {
        FlightId flightId = new FlightId(airlineCode, flightNumber, departureTime);

        return this.flightRepository.findById(flightId);
    }

    public List<List<List<Flight>>> getFlights(String departureAirport, String destinationAirport,
            LocalDateTime departureTime, LocalDateTime returnTime, boolean isReturn) {
        Optional<Destination> depResult = this.destinationService.getByAirport(departureAirport);
        Optional<Destination> destResult = this.destinationService.getByAirport(destinationAirport);

        List<List<List<Flight>>> results = new ArrayList<List<List<Flight>>>();

        if (depResult.isEmpty() || destResult.isEmpty()) {
            results.add(new ArrayList<List<Flight>>());
            results.add(new ArrayList<List<Flight>>());
            return results;
        }

        Destination departure = depResult.get();
        Destination destination = destResult.get();

        List<List<Flight>> departureJourneys = this.findAllFlights(departure, destination, departureTime, 5);
        results.add(departureJourneys);

        if (!isReturn)
            return results;

        List<List<Flight>> returnJourneys = this.findAllFlights(destination, departure, returnTime, 5);

        results.add(returnJourneys);

        return results;
    }

    public List<List<Flight>> findAllFlights(Destination departure, Destination destination,
            LocalDateTime departureTime, int numPaths) {
        List<List<Destination>> paths = new ArrayList<List<Destination>>();
        List<List<Flight>> journeys = new ArrayList<List<Flight>>();

        paths = this.pathAlgorithm.YensShortestPaths(departure, destination, numPaths);
        int tries = 0;
        while (paths.isEmpty()) {
            if (tries == 3)
                return journeys;

            paths = this.pathAlgorithm.YensShortestPaths(departure, destination, numPaths);
            tries++;
            numPaths += 2;
        }

        for (List<Destination> path : paths) {
            this.pathAlgorithm.printPath(path);
        }

        int pathExpansions = 0;
        int layoverExpansions = 0;
        int maxLayover = 12;
        List<List<Flight>> newJourneys = new ArrayList<List<Flight>>();
        while (journeys.size() < 2) {
            if (pathExpansions == 3)
                return journeys;

            while (journeys.size() < 2 && layoverExpansions < 3) {
                for (List<Destination> path : paths) {
                    newJourneys = this.flightAlgorithm.makeJourneys(new ArrayList<Flight>(), path, 0, 1, 1, maxLayover,
                            departureTime);
                    journeys.addAll(newJourneys);
                }
                maxLayover *= 2;
                layoverExpansions++;
            }
            numPaths += 2;
            pathExpansions++;
        }

        for (List<Flight> journey : journeys) {
            this.flightAlgorithm.printJourney(journey);
        }
        return journeys;
    }
}
