package com.uon.seng3160.group2.flightpub.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.uon.seng3160.group2.flightpub.entity.Destination;
import com.uon.seng3160.group2.flightpub.entity.Distance;
import com.uon.seng3160.group2.flightpub.entity.Flight;
import com.uon.seng3160.group2.flightpub.repository.FlightRepository;


public class BasicSearch implements FlightService{
    int magnitude = 4;
    List<Destination> searchDestinationsList = new ArrayList<>();
    Destination startDestination;
    Destination endDestination;
    List<List<Destination>> searchResults = new ArrayList<>();
    Destination[] graph;

    @Autowired 
    FlightRepository flightRepository;

    public BasicSearch(Destination start, Destination end){
        startDestination = start;
        endDestination = end;
        createGraph();
    }

    public BasicSearch() {
    }

    private void createGraph(){
        searchDestinationsList.add(startDestination);
        Iterator<Distance> distances;
        for(int j = 0; j < searchDestinationsList.size(); j++){
            distances = searchDestinationsList.get(j).getDistancesFrom().iterator();
            while(distances.hasNext()){
                searchDestinationsList.add(distances.next().getDestinationTo());
            }
            distances = searchDestinationsList.get(j).getDistancesTo().iterator();
            while(distances.hasNext()){
                searchDestinationsList.add(distances.next().getDestinationFrom());
            }
        }
    }

    public List<List<Destination>> bfs(Destination start, Destination end){
        List<List<Destination>> pathsList = new ArrayList<>();
        Queue<List<Destination>> queue = new LinkedList<>();

        queue.add(new ArrayList<>(List.of(start)));

        while(!queue.isEmpty()){
            List<Destination> currentPath = queue.poll();
            currentPath.get(currentPath.size()-1).setVisited(true);
            Destination lastNode = currentPath.get(currentPath.size()-1);

            if(lastNode == end){
                pathsList.add(new ArrayList<>(currentPath));
            }
            else {
                //get shortest distance unvisited neighbour
                Iterator<Distance> distances = lastNode.getDistances().iterator();
                while(distances.hasNext()){
                    Distance current = distances.next();
                    if(!current.getDestinationFrom().getVisited()){
                        List<Destination> newPath = new ArrayList<>(currentPath);
                        newPath.add(current.getDestinationFrom());
                        queue.add(newPath);
                    }
                    if(!current.getDestinationTo().getVisited()){
                        List<Destination> newPath = new ArrayList<>(currentPath);
                        newPath.add(current.getDestinationTo());
                        queue.add(newPath);
                    }
                }
            }
        }
        return pathsList;
    }

    public void createSearchResults(Destination start, Destination end){
        createGraph();
        List<List<Destination>> pathsList = bfs(start, end);
        List<Integer> pathDistances = new ArrayList<>();
        int pathDist = 0;
        for(int i = 0; i < pathsList.size(); i++){
            for(int j = 0; j < pathsList.get(i).size()-1; j++){
                //get the distance from jth destination to j+1th destination and add to total
                pathDist += pathsList.get(i).get(j).getDistanceTo(pathsList.get(i).get(j+1));
            }
            pathDistances.add(i, pathDist);
        }
        int tempDist = pathDistances.get(0);
        int indexOfTemp = 0;
        while(pathDistances.size() != 0){
            for(int i = 1; i < pathDistances.size(); i++){
                if(tempDist > pathDistances.get(i)){
                    tempDist = pathDistances.get(i);
                    indexOfTemp = i;
                }
            }
            searchResults.add(pathsList.get(indexOfTemp));
            pathsList.remove(indexOfTemp);
            pathDistances.remove(indexOfTemp);
        }
    }

    public int getMagnitude() {
        return this.magnitude;
    }

    public void setMagnitude(int magnitude) {
        this.magnitude = magnitude;
    }

    public List<Destination> getSearchDestinationsList() {
        return this.searchDestinationsList;
    }

    public void setSearchDestinationsList(List<Destination> searchDestinationsList) {
        this.searchDestinationsList = searchDestinationsList;
    }

    public Destination getStartDestination() {
        return this.startDestination;
    }

    public void setStartDestination(Destination startDestination) {
        this.startDestination = startDestination;
    }

    public Destination getEndDestination() {
        return this.endDestination;
    }

    public void setEndDestination(Destination endDestination) {
        this.endDestination = endDestination;
    }

    public List<List<Destination>> getSearchResults() {
        return this.searchResults;
    }

    public void setSearchResults(List<List<Destination>> searchResults) {
        this.searchResults = searchResults;
    }

    public void setFlightRepository(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

}