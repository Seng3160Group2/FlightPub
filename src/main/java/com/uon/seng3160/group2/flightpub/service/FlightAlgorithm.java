package com.uon.seng3160.group2.flightpub.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uon.seng3160.group2.flightpub.entity.Destination;
import com.uon.seng3160.group2.flightpub.entity.Flight;
import com.uon.seng3160.group2.flightpub.repository.FlightRepository;

@Service
@Transactional
public class FlightAlgorithm {

    @Autowired
    private final FlightRepository flightRepository;

    public FlightAlgorithm(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<List<Flight>> makeJourneys(List<Flight> journey, List<Destination> path, int u, int v, int minLayover,
            int maxLayover, LocalDateTime startTime) {
        System.out.println("path\n" + path);
        System.out.println(String.format("u: %d, v: %d, startTime: %s", u, v, startTime));
        System.out.println(journey);
        List<List<Flight>> journeys = new ArrayList<List<Flight>>();

        if (journey.size() > 0) {
            Flight lastLeg = journey.get(journey.size() - 1);
            Destination destination = path.get(path.size() - 1);
            if (lastLeg.getStopOver() != null && lastLeg.getStopOver().equals(destination)) {
                journeys.add(journey);
                return journeys;
            }
            if (lastLeg.getDestination().equals(destination)) {
                journeys.add(journey);
                return journeys;
            }
        }

        if (u == path.size() - 1) {
            journeys.add(journey);
            return journeys;
        }

        LocalDateTime upperTime;
        if (u == 0) { // Start from the beginning of the day
            upperTime = startTime.with(LocalTime.MAX); // End at the end of the day
        } else {
            upperTime = startTime.plusHours(maxLayover);
            startTime = startTime.plusHours(minLayover);
        }
        System.out.println(String.format("u = %s, v = %s, start = %s, upper = %s", path.get(u), path.get(v), startTime,
                upperTime));
        List<Flight> flights = flightRepository.findFlightsBetweenTimes(path.get(u), path.get(v), startTime, upperTime);
        System.out.println("flights\n" + flights);
        for (Flight flight : flights) {
            List<Flight> newJourney = new ArrayList<>(journey);
            newJourney.add(flight);

            LocalDateTime newStartTime;
            if (flight.getStopOver() != null && flight.getStopOver().equals(path.get(v))) {
                newStartTime = flight.getArrivalTimeStopOver();
            } else {
                newStartTime = flight.getArrivalTime();
            }

            List<List<Flight>> returnedJourneys = makeJourneys(newJourney, path, u + 1, v + 1,
                    minLayover, maxLayover, newStartTime);

            journeys.addAll(returnedJourneys);
        }
        return journeys;
    }

    public void printJourney(List<Flight> journey) {
        System.out.println("journey - [");
        for (Flight flight : journey) {
            System.out.println(flight.toString());
        }
        System.out.println("]");
    }
}
