package com.uon.seng3160.group2.flightpub.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.uon.seng3160.group2.flightpub.entity.Destination;
import com.uon.seng3160.group2.flightpub.entity.Distance;
import com.uon.seng3160.group2.flightpub.entity.Flight;
import com.uon.seng3160.group2.flightpub.util.ShortestPath;

public interface FlightService {
    public List<Destination> createGraph(Destination startDestination);

    public List<List<Flight>> getJourneys(Destination start, Destination end, LocalDateTime departureTime);

    public List<Flight> realisePath(List<Destination> path, LocalDateTime departureTime);

    public List<List<Destination>> YensShortestPaths(Destination start, Destination end, int K);

    public ShortestPath djikstras(Destination start, Destination end,
            List<Destination[]> illegalDistances, Set<Destination> illegalDestinations);

    public boolean distanceIsIllegal(Distance distance, List<Destination[]> illegalDistances);

    public int distanceCalc(List<Destination> path);

    public List<List<Flight>> makeJourneys(List<Flight> journey, List<Destination> path, int u, int v, int minLayover,
            int maxLayover, LocalDateTime startTime);

    public void printPath(List<Destination> path);

    public void printJourney(List<Flight> journey);
}