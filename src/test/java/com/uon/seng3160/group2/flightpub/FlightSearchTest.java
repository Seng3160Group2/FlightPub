package com.uon.seng3160.group2.flightpub;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.uon.seng3160.group2.flightpub.repository.DestinationRepository;
import com.uon.seng3160.group2.flightpub.entity.Destination;
import com.uon.seng3160.group2.flightpub.entity.Distance;
import com.uon.seng3160.group2.flightpub.entity.Flight;
import com.uon.seng3160.group2.flightpub.service.FlightSearchService;
import com.uon.seng3160.group2.flightpub.service.FlightService;
import com.uon.seng3160.group2.flightpub.util.ShortestPath;

@SpringBootTest
public class FlightSearchTest {

    @Autowired
    FlightService flightService;

    @Autowired
    FlightSearchService flightSearchService;

    @Autowired
    DestinationRepository destinationRepository;

    @Transactional
    public void getDestinationGraph() {
        Optional<Destination> result = destinationRepository.findById("ADL");

        if (result.isEmpty())
            return;

        Destination startDestination = result.get();
        System.out.println(startDestination);
        System.out.println("distances to ADL");
        for (Distance distance : startDestination.getDistances())
            System.out.println(String.format("%s %s)",
                    distance.getDestinationFrom().getDestinationCode(),
                    distance.getDestinationTo().getDestinationCode()));

        result = destinationRepository.findById("SYD");

        if (result.isEmpty())
            return;

        Destination endDestination = result.get();
        System.out.println(endDestination);

        ShortestPath shortestPath = flightService.djikstras(startDestination,
                endDestination,
                new ArrayList<Destination[]>(), new HashSet<Destination>());
        for (Destination destination : shortestPath.getPath()) {
            System.out.println(destination);
        }

        int calcedDistance = flightService.distanceCalc(shortestPath.getPath());

        System.out.println(
                String.format("djikstras dist: %d, calced dist: %d",
                        shortestPath.getLength(), calcedDistance));

        List<List<Destination>> kShortest = flightService.YensShortestPaths(startDestination, endDestination, 15);
        System.out.println(" -- K shortest paths --" + kShortest.size());
        for (List<Destination> path : kShortest) {
            flightService.printPath(path);
        }
        LocalDateTime startDateTime = LocalDateTime.of(2014, 9, 23, 0, 0, 0, 0);

        List<List<Flight>> journeys = new ArrayList<List<Flight>>();
        for (List<Destination> path : kShortest) {
            journeys.addAll(flightService.makeJourneys(new ArrayList<Flight>(), path, 0,
                    1, 1, 24,
                    startDateTime));
        }

        for (List<Flight> journey : journeys) {
            flightService.printJourney(journey);
        }
    }

    @Test
    @Transactional
    public void test2() {
        LocalDateTime startDateTime = LocalDateTime.of(2014, 9, 23, 0, 0, 0, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2014, 9, 27, 0, 0, 0, 0);

        List<List<List<Flight>>> flights = this.flightSearchService.getFlights("Adelaide", "Sydney", startDateTime,
                endDateTime, false);
        System.out.println(flights);

    }
}
