package com.uon.seng3160.group2.flightpub.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.uon.seng3160.group2.flightpub.entity.Destination;
import com.uon.seng3160.group2.flightpub.entity.Distance;
import com.uon.seng3160.group2.flightpub.util.ShortestPath;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PathAlgorithm {

    public PathAlgorithm() {

    }

    public List<List<Destination>> YensShortestPaths(Destination start, Destination end, int K) {
        List<List<Destination>> A = new ArrayList<List<Destination>>(); // shortest paths
        Map<List<Destination>, Integer> distB = new HashMap<>(); // potential shortest paths distances
        PriorityQueue<List<Destination>> B = new PriorityQueue<>(Comparator.comparingInt(distB::get)); // potential
                                                                                                       // shortest paths
        Set<List<Destination>> setB = new HashSet<List<Destination>>(); // unique potential paths
        List<Destination[]> illegalDistances = new ArrayList<Destination[]>(); // illegal edges (distances)
        Set<Destination> illegalDestinations = new HashSet<Destination>(); // illegal nodes

        // find and add shortest path
        A.add(djikstras(start, end, new ArrayList<Destination[]>(), new HashSet<Destination>()).getPath());

        for (int k = 1; k < K; k++) {
            //
            int spurRange = A.get(k - 1).size() - 1;
            for (int i = 0; i < spurRange; i++) {
                Destination spurNode = A.get(k - 1).get(i);
                List<Destination> rootPath = A.get(k - 1).subList(0, i + 1);

                // Remove destination connected to the spur node for all shortest paths if the
                // rootpath is the same
                for (List<Destination> path : A) {
                    List<Destination> diff = compareListsAndReturnDifference(path, rootPath);

                    if (!diff.isEmpty()) {
                        Destination[] edge = { spurNode, diff.get(0) };
                        illegalDistances.add(edge);
                    }
                }
                // for each root path node except spur node, remove from pool of distances
                for (int j = 0; j < i; j++)
                    illegalDestinations.add(rootPath.get(j));

                ShortestPath shortestPath = djikstras(spurNode, end, illegalDistances, illegalDestinations);

                illegalDistances.clear();
                illegalDestinations.clear();

                if (shortestPath.getPath().isEmpty())
                    continue;

                List<Destination> spurPath = shortestPath.getPath();
                List<Destination> totalPath = new ArrayList<Destination>();
                totalPath.addAll(rootPath.subList(0, i));
                totalPath.addAll(spurPath);

                int distance = distanceCalc(totalPath);
                distB.put(totalPath, distance);

                if (!setB.contains(totalPath)) {
                    B.add(totalPath);
                    setB.add(totalPath);
                }
            }

            if (B.isEmpty())
                break;

            A.add(B.poll());
        }
        return A;
    }

    public List<Destination> compareListsAndReturnDifference(List<Destination> path, List<Destination> rootPath) {
        if (path.size() < rootPath.size() || path.size() == rootPath.size())
            return new ArrayList<Destination>();

        for (int i = 0; i < rootPath.size(); i++) {
            if (!path.get(i).equals(rootPath.get(i)))
                return new ArrayList<Destination>();

        }
        List<Destination> diff = new ArrayList<Destination>(path);
        diff.removeAll(rootPath);
        return diff;
    }

    public int distanceCalc(List<Destination> path) {
        int distance = 0;
        List<Distance> distances;
        for (int i = 0; i < path.size() - 1; i++) {
            distances = new ArrayList<Distance>(path.get(i).getDistances());
            for (Distance dist : distances) {
                if (dist.getOtherDestination(path.get(i)).equals(path.get(i + 1))) {
                    distance += dist.getDistancesInKms();
                    break;
                }
            }
        }

        return distance;
    }

    public ShortestPath djikstras(Destination start, Destination end,
            List<Destination[]> illegalDistances, Set<Destination> illegalDestinations) {
        Map<Destination, Integer> distances = new HashMap<>();
        Map<Destination, List<Destination>> previousNodes = new HashMap<>();
        Set<Destination> visited = new HashSet<>();

        PriorityQueue<Destination> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        previousNodes.put(end, new ArrayList<Destination>(Collections.singletonList(end)));
        previousNodes.put(start, Collections.singletonList(start));
        distances.put(start, 0);
        priorityQueue.add(start);

        if (start.equals(end))
            return new ShortestPath(previousNodes.get(end), 0);

        while (!priorityQueue.isEmpty()) {
            Destination current = priorityQueue.poll();
            if (current.equals(end)) {
                break;
            }

            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);

            for (Distance distance : current.getDistances()) {
                if (distanceIsIllegal(distance, illegalDistances))
                    continue;

                Destination neighbor = distance.getOtherDestination(current);
                if (illegalDestinations.contains(neighbor))
                    continue;

                int newDistance = distances.get(current) + distance.getDistancesInKms();

                if (newDistance < distances.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    distances.put(neighbor, newDistance);

                    List<Destination> newPath = previousNodes.get(current);
                    if (newPath == null)
                        newPath = new ArrayList<>();
                    else
                        newPath = new ArrayList<>(newPath);

                    newPath.add(neighbor);
                    previousNodes.put(neighbor, newPath);
                    priorityQueue.add(neighbor);
                }
            }
        }
        return new ShortestPath(previousNodes.get(end), distances.get(end));
    }

    public boolean distanceIsIllegal(Distance distance, List<Destination[]> illegalDistances) {
        Destination destTo = distance.getDestinationTo();
        Destination destFrom = distance.getDestinationFrom();

        for (Destination[] illegalDistance : illegalDistances) {
            boolean destinationToEqual = (destTo.equals(illegalDistance[0]) || destTo.equals(illegalDistance[1]));
            boolean destinationFromEqual = (destFrom.equals(illegalDistance[0]) || destFrom.equals(illegalDistance[1]));

            if (destinationFromEqual && destinationToEqual)
                return true;
        }

        return false;
    }

    public void printPath(List<Destination> path) {
        System.out.print("path - [");
        for (int i = 0; i < path.size(); i++) {
            if (i == path.size() - 1) {
                System.out.print(String.format("%s", path.get(i).getDestinationCode()));
                continue;
            }
            System.out.print(String.format("%s ->", path.get(i).getDestinationCode()));
        }
        System.out.println("]");
    }
}
