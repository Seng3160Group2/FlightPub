package com.uon.seng3160.group2.flightpub.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uon.seng3160.group2.flightpub.service.DestinationService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DestinationMatrix {

    @Autowired
    private final DestinationService destinationService;

    private Map<Destination, Map<Destination, Edge>> matrix;

    private Map<String, Destination> dictionary;

    public DestinationMatrix(DestinationService destinationService) {
        this.destinationService = destinationService;
        this.matrix = new HashMap<Destination, Map<Destination, Edge>>();
        this.dictionary = new HashMap<String, Destination>();
    }

    public void populate() {
        System.out.println("Creating matrix");
        List<Destination> destinations = destinationService.getAll();
        for (Destination destination : destinations) {
            this.dictionary.put(destination.getDestinationCode(), destination);
            this.addNode(destination);
            Set<Distance> distancesFrom = destination.getDistancesFrom();

            Destination endDestination;
            int length;
            for (Distance distance : distancesFrom) {
                endDestination = distance.getOtherDestination(destination);
                length = distance.getDistancesInKms();
                this.addEdge(destination, endDestination, length);
            }
        }
        System.out.println(this.toString());
    }

    public void addNode(Destination node) {
        if (this.nodeExists(node))
            return;
        this.matrix.put(node, new HashMap<Destination, Edge>());
    }

    public void addEdge(Destination nodeA, Destination nodeB, int length) {
        this.addNode(nodeA);
        this.addNode(nodeB);
        Map<Destination, Edge> edgesA = this.getEdges(nodeA);
        Map<Destination, Edge> edgesB = this.getEdges(nodeB);

        Edge edge = new Edge(length, false);
        if (!edgesA.containsKey(nodeB)) {
            edgesA.put(nodeB, edge);
        }
        if (!edgesB.containsKey(nodeA)) {
            edgesB.put(nodeA, edge);
        }
    }

    public Optional<Edge> getEdge(Destination nodeA, Destination nodeB) {
        Map<Destination, Edge> edgesA = this.getEdges(nodeA);
        if (edgesA == null)
            return null;

        Edge edge = edgesA.get(nodeB);

        return Optional.ofNullable(edge);
    }

    public Map<Destination, Edge> getEdges(Destination node) {
        return this.matrix.get(node);
    }

    public boolean nodeExists(Destination node) {
        return this.matrix.containsKey(node);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Destination, Map<Destination, Edge>> entry : matrix.entrySet()) {
            Destination source = entry.getKey();
            Map<Destination, Edge> edges = entry.getValue();

            sb.append("Source Destination: ").append(source.getDestinationCode()).append("\n");

            for (Map.Entry<Destination, Edge> edgeEntry : edges.entrySet()) {
                Destination destination = edgeEntry.getKey();
                Edge edge = edgeEntry.getValue();

                sb.append("  Destination: ").append(destination.getDestinationCode());
                sb.append(", Edge Length: ").append(edge.getLength()).append("\n");
            }
        }

        return sb.toString();
    }

    public Optional<Destination> getNode(String code) {
        return Optional.ofNullable(this.dictionary.get(code));
    }

    public List<Destination> getDestinations() {
        return new ArrayList<Destination>(this.dictionary.values());
    }
}
