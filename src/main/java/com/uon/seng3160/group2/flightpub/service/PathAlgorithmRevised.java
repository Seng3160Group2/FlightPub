package com.uon.seng3160.group2.flightpub.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uon.seng3160.group2.flightpub.entity.Destination;
import com.uon.seng3160.group2.flightpub.entity.DestinationMatrix;
import com.uon.seng3160.group2.flightpub.entity.Distance;
import com.uon.seng3160.group2.flightpub.entity.Edge;
import com.uon.seng3160.group2.flightpub.util.ShortestPath;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PathAlgorithmRevised {

    @Autowired
    private DestinationMatrix destinationMatrix;

    public PathAlgorithmRevised(DestinationMatrix destinationMatrix) {
        this.destinationMatrix = destinationMatrix;
    }

    public List<List<Destination>> YensShortestPaths(Destination start, Destination end, int K) {
        List<List<Destination>> A = new ArrayList<List<Destination>>(K); // shortest paths
        Map<List<Destination>, Integer> distB = new HashMap<>(); // potential shortest paths distances
        PriorityQueue<List<Destination>> B = new PriorityQueue<>(Comparator.comparingInt(distB::get)); // potential
                                                                                                       // shortest paths
        Set<List<Destination>> setB = new HashSet<List<Destination>>(); // unique potential paths

        Destination spurNode;
        List<Destination> rootPath;
        List<Destination> diff;
        Optional<Edge> edge;
        List<Edge> illegalEdges = new ArrayList<Edge>(5);
        ShortestPath shortestPath;
        List<Destination> spurPath;
        List<Destination> totalPath;

        // find and add shortest path
        A.add(djikstras(start, end).getPath());

        for (int k = 1; k < K; k++) {
            //
            int spurRange = A.get(k - 1).size() - 1;
            for (int i = 0; i < spurRange; i++) {
                spurNode = A.get(k - 1).get(i);
                rootPath = A.get(k - 1).subList(0, i + 1);

                // Remove destination connected to the spur node for all shortest paths if the
                // rootpath is the same
                for (List<Destination> path : A) {
                    diff = compareListsAndReturnDifference(path, rootPath);

                    if (!diff.isEmpty()) {
                        edge = this.destinationMatrix.getEdge(spurNode, diff.get(0));
                        if (edge.isPresent()) {
                            edge.get().setIllegal(true);
                            illegalEdges.add(edge.get());
                        }
                    }
                }
                // for each root path node except spur node, remove from pool of distances
                for (int j = 0; j < i; j++)
                    rootPath.get(j).setIllegal(true);

                shortestPath = djikstras(spurNode, end);

                this.clearIllegalEdges(illegalEdges);
                this.clearIllegalNodes(rootPath, i);

                if (shortestPath.getPath().isEmpty())
                    continue;

                spurPath = shortestPath.getPath();
                totalPath = new ArrayList<Destination>();
                totalPath.addAll(rootPath.subList(0, i));
                totalPath.addAll(spurPath);

                // moved distance and distB.put inside condition cause it wouldnt get used if
                // oath isnt new
                if (!setB.contains(totalPath)) {
                    int distance = distanceCalc(totalPath);
                    distB.put(totalPath, distance);

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

    public void clearIllegalEdges(List<Edge> illegalEdges) {
        for (Edge edge : illegalEdges)
            edge.setIllegal(false);
    }

    public void clearIllegalNodes(List<Destination> illegalNodes, int range) {
        for (int i = 0; i < range; i++) {
            illegalNodes.get(i).setIllegal(false);
        }
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

    public ShortestPath djikstras(Destination start, Destination end) {
        Map<Destination, Integer> distances = new HashMap<>();
        Map<Destination, List<Destination>> previousNodes = new HashMap<>();
        Set<Destination> visited = new HashSet<>();

        Destination current;
        Destination neighbor;
        Map<Destination, Edge> edges;
        Edge edge;
        List<Destination> newPath;

        PriorityQueue<Destination> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        previousNodes.put(end, new ArrayList<Destination>());
        previousNodes.put(start, Collections.singletonList(start));
        distances.put(start, 0);
        priorityQueue.add(start);

        if (start.equals(end))
            return new ShortestPath(previousNodes.get(end), 0);

        while (!priorityQueue.isEmpty()) {
            current = priorityQueue.poll();
            if (current.equals(end)) {
                break;
            }

            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);

            edges = this.destinationMatrix.getEdges(current);
            for (Entry<Destination, Edge> edgeEntry : edges.entrySet()) {
                edge = edgeEntry.getValue();
                if (edge.isIllegal())
                    continue;

                neighbor = edgeEntry.getKey();

                if (neighbor.isIllegal())
                    continue;

                int newDistance = distances.get(current) + edge.getLength();

                if (newDistance < distances.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    distances.put(neighbor, newDistance);

                    newPath = previousNodes.get(current);
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

    public void printMatrix() {
        System.out.println(this.destinationMatrix);
    }
}
