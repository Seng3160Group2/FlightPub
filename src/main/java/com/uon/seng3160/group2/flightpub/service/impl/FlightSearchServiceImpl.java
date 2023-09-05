package com.uon.seng3160.group2.flightpub.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uon.seng3160.group2.flightpub.entity.Destination;
import com.uon.seng3160.group2.flightpub.entity.DestinationMatrix;
import com.uon.seng3160.group2.flightpub.entity.Flight;
import com.uon.seng3160.group2.flightpub.entity.compositekey.FlightId;
import com.uon.seng3160.group2.flightpub.repository.FlightRepository;
import com.uon.seng3160.group2.flightpub.service.DestinationService;
import com.uon.seng3160.group2.flightpub.service.FlightAlgorithm;
import com.uon.seng3160.group2.flightpub.service.FlightSearchService;
import com.uon.seng3160.group2.flightpub.service.PathAlgorithm;
import com.uon.seng3160.group2.flightpub.service.PathAlgorithmRevised;

@Service
@Transactional
public class FlightSearchServiceImpl implements FlightSearchService {
    @Autowired
    private final FlightRepository flightRepository;

    @Autowired
    private final DestinationService destinationService;

    @Autowired
    private final PathAlgorithmRevised pathAlgorithmRevised;

    @Autowired
    private final FlightAlgorithm flightAlgorithm;

    @Autowired
    private final PathService pathService;

    @Autowired
    final DestinationMatrix destinationMatrix;

    public FlightSearchServiceImpl(FlightRepository flightRepository, DestinationService destinationService,
            FlightAlgorithm flightAlgorithm, PathAlgorithmRevised pathAlgorithmRevised, PathService pathService,
            DestinationMatrix destinationMatrix) {
        this.flightRepository = flightRepository;
        this.destinationService = destinationService;
        this.flightAlgorithm = flightAlgorithm;
        this.pathAlgorithmRevised = pathAlgorithmRevised;
        this.pathService = pathService;
        this.destinationMatrix = destinationMatrix;
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
        departure = this.destinationMatrix.getNode(departure.getDestinationCode()).get();
        destination = this.destinationMatrix.getNode(destination.getDestinationCode()).get();

        List<List<Flight>> departureJourneys = this.findAllFlights(departure, destination, departureTime, 3);
        results.add(departureJourneys);

        if (!isReturn)
            return results;

        List<List<Flight>> returnJourneys = this.findAllFlights(destination, departure, returnTime, 3);

        results.add(returnJourneys);

        return results;
    }

    public List<List<Flight>> findAllFlights(Destination departure, Destination destination,
            LocalDateTime departureTime, int numPaths) {
        List<List<Destination>> paths = new ArrayList<List<Destination>>();
        Set<List<Flight>> journeys = new HashSet<List<Flight>>();

        paths = pathService.getPaths(departure, destination);

        int layoverExpansions;
        int maxLayover;
        int pathExpansions = 0;
        List<List<Flight>> newJourneys = new ArrayList<List<Flight>>();
        List<List<Destination>> oldPaths = new ArrayList<List<Destination>>();
        while (journeys.size() < 3 && pathExpansions < 2) {
            if (pathExpansions != 0) {
                oldPaths.addAll(paths);
                paths = this.pathAlgorithmRevised.YensShortestPaths(departure, destination, numPaths);
                paths.removeAll(oldPaths);
            }

            // for (List<Destination> path : paths) {
            // pathAlgorithmRevised.printPath(path);
            // }
            // System.out.println("------");

            layoverExpansions = 0;
            maxLayover = 24;
            while (journeys.size() < 3 && layoverExpansions < 3) {
                for (List<Destination> path : paths) {
                    newJourneys = this.flightAlgorithm.makeJourneys(new ArrayList<Flight>(), path, 0, 1, 1, maxLayover,
                            departureTime);
                    journeys.addAll(newJourneys);
                }
                maxLayover *= 2;
                layoverExpansions++;
            }
            numPaths += 5;
            pathExpansions++;
        }

        for (List<Flight> journey : journeys) {
            this.flightAlgorithm.printJourney(journey);
        }
        return new ArrayList<List<Flight>>(journeys);
    }
}
