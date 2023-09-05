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
import com.uon.seng3160.group2.flightpub.entity.DestinationMatrix;
import com.uon.seng3160.group2.flightpub.entity.Distance;
import com.uon.seng3160.group2.flightpub.entity.Flight;
import com.uon.seng3160.group2.flightpub.service.FlightAlgorithm;
import com.uon.seng3160.group2.flightpub.service.FlightSearchService;
import com.uon.seng3160.group2.flightpub.service.FlightService;
import com.uon.seng3160.group2.flightpub.service.PathAlgorithmRevised;
import com.uon.seng3160.group2.flightpub.util.ShortestPath;

@SpringBootTest
public class FlightSearchTest {

    // @Autowired
    // FlightService flightService;

    @Autowired
    FlightSearchService flightSearchService;

    @Autowired
    DestinationRepository destinationRepository;

    @Autowired
    PathAlgorithmRevised pathAlgorithmRevised;

    @Autowired
    DestinationMatrix destinationMatrix;

    @Autowired
    FlightAlgorithm flightAlgorithm;

    // @Transactional
    // public void getDestinationGraph() {
    // Optional<Destination> result = destinationRepository.findById("ADL");

    // if (result.isEmpty())
    // return;

    // Destination startDestination = result.get();
    // System.out.println(startDestination);
    // System.out.println("distances to ADL");
    // for (Distance distance : startDestination.getDistances())
    // System.out.println(String.format("%s %s)",
    // distance.getDestinationFrom().getDestinationCode(),
    // distance.getDestinationTo().getDestinationCode()));

    // result = destinationRepository.findById("SYD");

    // if (result.isEmpty())
    // return;

    // Destination endDestination = result.get();
    // System.out.println(endDestination);

    // ShortestPath shortestPath = flightService.djikstras(startDestination,
    // endDestination,
    // new ArrayList<Destination[]>(), new HashSet<Destination>());
    // for (Destination destination : shortestPath.getPath()) {
    // System.out.println(destination);
    // }

    // int calcedDistance = flightService.distanceCalc(shortestPath.getPath());

    // System.out.println(
    // String.format("djikstras dist: %d, calced dist: %d",
    // shortestPath.getLength(), calcedDistance));

    // List<List<Destination>> kShortest =
    // flightService.YensShortestPaths(startDestination, endDestination, 15);
    // System.out.println(" -- K shortest paths --" + kShortest.size());
    // for (List<Destination> path : kShortest) {
    // flightService.printPath(path);
    // }
    // LocalDateTime startDateTime = LocalDateTime.of(2014, 9, 23, 0, 0, 0, 0);

    // List<List<Flight>> journeys = new ArrayList<List<Flight>>();
    // for (List<Destination> path : kShortest) {
    // journeys.addAll(flightService.makeJourneys(new ArrayList<Flight>(), path, 0,
    // 1, 1, 24,
    // startDateTime));
    // }

    // for (List<Flight> journey : journeys) {
    // flightService.printJourney(journey);
    // }
    // }

    @Transactional
    public void getDestinationGraph2() {
        System.out.println("-- test start");
        long startTime = System.currentTimeMillis();
        Optional<Destination> result = this.destinationMatrix.getNode("HBA");

        if (result.isEmpty())
            return;

        Destination startDestination = result.get();

        result = this.destinationMatrix.getNode("ORD");

        if (result.isEmpty())
            return;

        Destination endDestination = result.get();

        List<List<Destination>> kShortest = pathAlgorithmRevised.YensShortestPaths(startDestination, endDestination, 3);

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Elapsed time: " + elapsedTime + " milliseconds");

        System.out.println(" -- K shortest paths --" + kShortest.size());
        for (List<Destination> path : kShortest) {
            pathAlgorithmRevised.printPath(path);
        }
        System.out.println("--test end");
    }

    @Test
    @Transactional
    public void getFlights() {
        System.out.println("-- test start");
        long startTime = System.currentTimeMillis();
        Optional<Destination> result = this.destinationMatrix.getNode("SYD");

        if (result.isEmpty())
            return;

        Destination startDestination = result.get();

        result = this.destinationMatrix.getNode("MEL");

        if (result.isEmpty())
            return;

        Destination endDestination = result.get();

        List<List<Destination>> kShortest = pathAlgorithmRevised.YensShortestPaths(startDestination, endDestination, 3);

        LocalDateTime startDateTime = LocalDateTime.of(2014, 9, 23, 0, 0, 0, 0);

        List<List<Flight>> journeys = this.flightSearchService.findAllFlights(startDestination, endDestination,
                startDateTime, 3);
        for (List<Flight> journey : journeys) {
            this.flightAlgorithm.printJourney(journey);
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Elapsed time: " + elapsedTime + " milliseconds");

        System.out.println("--test end");
    }

    @Transactional
    public void test2() {
        LocalDateTime startDateTime = LocalDateTime.of(2014, 9, 23, 0, 0, 0, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2014, 9, 27, 0, 0, 0, 0);

        List<List<List<Flight>>> flights = this.flightSearchService.getFlights("Adelaide", "Sydney", startDateTime,
                endDateTime, false);
        System.out.println(flights);

    }
}
