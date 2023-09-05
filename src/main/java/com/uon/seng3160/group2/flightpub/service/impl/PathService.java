package com.uon.seng3160.group2.flightpub.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uon.seng3160.group2.flightpub.entity.Destination;
import com.uon.seng3160.group2.flightpub.entity.Trip;
import com.uon.seng3160.group2.flightpub.service.PathAlgorithmRevised;

@Service
public class PathService {
    private Map<Trip, List<List<Destination>>> trips;

    @Autowired
    private final PathAlgorithmRevised pathAlgorithmRevised;

    public PathService(PathAlgorithmRevised pathAlgorithmRevised) {
        this.pathAlgorithmRevised = pathAlgorithmRevised;
        this.trips = new HashMap<Trip, List<List<Destination>>>();
    }

    public void addTrip(Destination A, Destination B, List<List<Destination>> paths) {
        Trip trip = new Trip(A, B);
        this.trips.put(trip, paths);
    }

    public void populate(List<Destination> destinations) {
        Trip trip;
        System.out.println("matrix in pathservice");
        this.pathAlgorithmRevised.printMatrix();
        List<List<Destination>> paths;
        for (Destination a : destinations) {
            for (Destination b : destinations) {
                if (a == b)
                    continue;
                trip = new Trip(a, b);
                if (this.trips.containsKey(trip))
                    continue;

                // System.out.println(String.format("%s -> %s", a, b));
                // paths = this.pathAlgorithmRevised.YensShortestPaths(a, b, 3);
                paths = null;
                System.out.println(paths);
                this.trips.put(trip, paths);

                trip = new Trip(b, a);
                // paths = reversePaths(paths);
                paths = null;
                this.trips.put(trip, paths);
            }
        }
    }

    // Helper method to reverse paths from A to B to get B to A
    private List<List<Destination>> reversePaths(List<List<Destination>> paths) {
        List<List<Destination>> reversedPaths = new ArrayList<List<Destination>>();
        for (List<Destination> path : paths) {
            List<Destination> reversed = new ArrayList<Destination>(path);
            Collections.reverse(reversed);
            reversedPaths.add(reversed); // You may need to adjust the constructor
        }
        return reversedPaths;
    }

    public List<List<Destination>> getPaths(Destination a, Destination b) {
        List<List<Destination>> paths = this.trips.get(new Trip(a, b));
        if (paths == null)
            return new ArrayList<List<Destination>>();
        return paths;
    }
}
