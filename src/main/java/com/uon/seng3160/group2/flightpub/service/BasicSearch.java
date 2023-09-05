// package com.uon.seng3160.group2.flightpub.service;

// import java.time.LocalDateTime;
// import java.time.LocalTime;
// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.Comparator;
// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.Iterator;
// import java.util.LinkedList;
// import java.util.List;
// import java.util.Locale;
// import java.util.Map;
// import java.util.PriorityQueue;
// import java.util.Queue;
// import java.util.Set;

// import org.hibernate.Remove;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.stereotype.Service;

// import com.uon.seng3160.group2.flightpub.entity.Destination;
// import com.uon.seng3160.group2.flightpub.entity.Distance;
// import com.uon.seng3160.group2.flightpub.entity.Flight;
// import com.uon.seng3160.group2.flightpub.formatter.LocalDateTimeFormatter;
// import com.uon.seng3160.group2.flightpub.repository.FlightRepository;
// import com.uon.seng3160.group2.flightpub.util.ShortestPath;

// @Service
// @Transactional
// public class BasicSearch implements FlightService {

// @Autowired
// private final FlightRepository flightRepository;

// int magnitude = 4;
// List<Destination> searchDestinationsList = new ArrayList<>();
// Destination startDestination = null;
// Destination endDestination = null;
// List<List<Destination>> searchResults = new ArrayList<>();

// public BasicSearch(FlightRepository flightRepository) {
// this.startDestination = null;
// this.endDestination = null;
// this.flightRepository = flightRepository;
// // createGraph(this.startDestination);
// }

// public List<List<Flight>> getJourneys(Destination start, Destination end,
// LocalDateTime departureTime) {
// List<List<Destination>> kShortest = this.YensShortestPaths(start, end, 3);
// List<List<Flight>> flightJourneys = new ArrayList<List<Flight>>();

// List<Flight> flights;
// for (List<Destination> path : kShortest) {
// flights = realisePath(path, departureTime);
// flightJourneys.add(flights);
// }
// return flightJourneys;
// }

// // instantiate empty journey
// // if u is the last node
// // add the journey to the list of journeys

// // if u is the first node
// // dont add anything to start date
// // max date is the end of specified day

// // else
// // start date = start date + minLayover
// // max date = start date + maxLayover

// // get flights from path[u] to path[v] that are wihin range(startDate,
// maxDate)
// // for each flight
// // duplicate journey
// // add self to journey
// // set start date to self.arrivalTime
// // add 1 to u and v (not increment)
// // call recursive func with changed parameters
// // collate returned journey list with local journey list

// // return journey list

// public List<List<Flight>> makeJourneys(List<Flight> journey,
// List<Destination> path, int u, int v, int minLayover,
// int maxLayover, LocalDateTime startTime) {
// List<List<Flight>> journeys = new ArrayList<List<Flight>>();
// System.out.println(String.format("u: %d, v: %d, startTime: %s", u, v,
// startTime));
// System.out.println("--- Journey ---");
// printJourney(journey);

// if (journey.size() > 0) {
// Flight lastLeg = journey.get(journey.size() - 1);
// Destination destination = path.get(path.size() - 1);
// if (lastLeg.getStopOver() != null &&
// lastLeg.getStopOver().equals(destination)) {
// journeys.add(journey);
// return journeys;
// }
// if (lastLeg.getDestination().equals(destination)) {
// journeys.add(journey);
// return journeys;
// }
// }

// if (u == path.size() - 1) {
// journeys.add(journey);
// return journeys;
// }

// LocalDateTime upperTime;
// if (u == 0) { // Start from the beginning of the day
// upperTime = startTime.with(LocalTime.MAX); // End at the end of the day
// upperTime = upperTime.plusDays(1);
// } else {
// upperTime = startTime.plusHours(maxLayover);
// startTime = startTime.plusHours(minLayover);
// }

// System.out.println(String.format("dest_u: %s, dest_v: %s, startTime: %s,
// upperTime: %s",
// path.get(u).getDestinationCode(), path.get(v).getDestinationCode(),
// startTime, upperTime));
// List<Flight> flights = flightRepository.findFlightsBetweenTimes(path.get(u),
// path.get(v), startTime, upperTime);

// for (Flight flight : flights) {
// List<Flight> newJourney = new ArrayList<>(journey);
// newJourney.add(flight);

// LocalDateTime newStartTime;
// if (flight.getStopOver() != null && flight.getStopOver().equals(path.get(v)))
// {
// newStartTime = flight.getArrivalTimeStopOver();
// } else {
// newStartTime = flight.getArrivalTime();
// }

// List<List<Flight>> returnedJourneys = makeJourneys(newJourney, path, u + 1, v
// + 1,
// minLayover, maxLayover, newStartTime);

// journeys.addAll(returnedJourneys);
// }
// return journeys;
// }

// public void printPath(List<Destination> path) {
// System.out.print("path - [");
// for (int i = 0; i < path.size(); i++) {
// if (i == path.size() - 1) {
// System.out.print(String.format("%s", path.get(i).getDestinationCode()));
// continue;
// }
// System.out.print(String.format("%s ->", path.get(i).getDestinationCode()));
// }
// System.out.println("]");
// }

// public void printJourney(List<Flight> journey) {
// System.out.println("journey - [");
// for (Flight flight : journey) {
// System.out.println(flight.toString());
// }
// System.out.println("]");
// }

// public List<Flight> realisePath(List<Destination> path, LocalDateTime
// departureTime) {
// LocalDateTime departureTimeUpper = departureTime.plusDays(5);
// LocalDateTimeFormatter dateFormatter = new LocalDateTimeFormatter();
// System.out.println(String.format("start date: %s",
// dateFormatter.print(departureTime, Locale.ENGLISH)));
// System.out.println(String.format("end date: %s",
// dateFormatter.print(departureTimeUpper, Locale.ENGLISH)));

// Destination curr = path.get(0);
// Destination next = path.get(1);

// return flightRepository.findFlightsBetweenTimes(curr, next, departureTime,
// departureTimeUpper);
// }

// public List<List<Destination>> YensShortestPaths(Destination start,
// Destination end, int K) {
// // shortest paths
// List<List<Destination>> A = new ArrayList<List<Destination>>();
// // potential shortest paths distances
// Map<List<Destination>, Integer> distB = new HashMap<>();
// // potential shortest paths
// PriorityQueue<List<Destination>> B = new
// PriorityQueue<>(Comparator.comparingInt(distB::get));
// Set<List<Destination>> setB = new HashSet<List<Destination>>();
// // illegal edges (distances)
// List<Destination[]> illegalDistances = new ArrayList<Destination[]>();
// // illegal nodes
// Set<Destination> illegalDestinations = new HashSet<Destination>();
// // find and add shortest path
// A.add(djikstras(start, end, new ArrayList<Destination[]>(), new
// HashSet<Destination>()).getPath());

// // until there are K shortest paths
// for (int k = 1; k < K; k++) {
// //
// int spurRange = A.get(k - 1).size() - 1;
// for (int i = 0; i < spurRange; i++) {
// Destination spurNode = A.get(k - 1).get(i);
// List<Destination> rootPath = A.get(k - 1).subList(0, i + 1); // may need to
// checK the (i+1), could be //
// // dangerous

// // Remove destination connected to the spur node for all shortest paths if
// the
// // rootpath is the same
// for (List<Destination> path : A) {
// System.out.println("path:\n" + path);
// System.out.println("rootPath:\n" + rootPath);
// List<Destination> diff = compareListsAndReturnDifference(path, rootPath);
// System.out.println("diff:\n" + diff);

// if (!diff.isEmpty()) {
// Destination[] edge = { spurNode, diff.get(0) };
// illegalDistances.add(edge);
// }
// }
// // for each root path node except spur node, remove from pool of distances
// for (int j = 0; j < i; j++)
// illegalDestinations.add(rootPath.get(j));
// System.out.println("IllegalDistances:\n");
// for (Destination[] distance : illegalDistances) {
// System.out.println("start: " + distance[0]);
// System.out.println("stop: " + distance[1]);
// }
// System.out.println("Illegal Destinations:\n" + illegalDestinations);

// System.out.println("got to djikstas");
// ShortestPath shortestPath = djikstras(spurNode, end, illegalDistances,
// illegalDestinations);

// illegalDistances.clear();
// illegalDestinations.clear();

// if (shortestPath.getPath().isEmpty())
// continue;
// System.out.println("djikstras:\n " + shortestPath.getPath());
// List<Destination> spurPath = shortestPath.getPath();
// List<Destination> totalPath = new ArrayList<Destination>();
// totalPath.addAll(rootPath.subList(0, i));
// totalPath.addAll(spurPath);

// int distance = distanceCalc(totalPath);
// distB.put(totalPath, distance);

// if (!setB.contains(totalPath)) {
// B.add(totalPath);
// setB.add(totalPath);
// }

// }

// if (B.isEmpty())
// break;

// A.add(B.poll());
// }
// return A;
// }

// // create rootpath (alt)
// // boolean spurFound = false;
// // for (Destination d : rootPath) {
// // if (d.equals(spurNode)) {
// // spurFound = true;
// // continue;
// // }
// // if (spurFound) {
// // rootPath.remove(d);
// // }
// // }
// public List<Destination> compareListsAndReturnDifference(List<Destination>
// path, List<Destination> rootPath) {
// if (path.size() < rootPath.size() || path.size() == rootPath.size())
// return new ArrayList<Destination>();

// for (int i = 0; i < rootPath.size(); i++) {
// if (!path.get(i).equals(rootPath.get(i)))
// return new ArrayList<Destination>();

// }
// List<Destination> diff = new ArrayList<Destination>(path);
// diff.removeAll(rootPath);
// return diff;
// }

// public ShortestPath djikstras(Destination start, Destination end,
// List<Destination[]> illegalDistances, Set<Destination> illegalDestinations) {
// Map<Destination, Integer> distances = new HashMap<>();
// Map<Destination, List<Destination>> previousNodes = new HashMap<>();
// Set<Destination> visited = new HashSet<>();

// PriorityQueue<Destination> priorityQueue = new
// PriorityQueue<>(Comparator.comparingInt(distances::get));
// previousNodes.put(end, new
// ArrayList<Destination>(Collections.singletonList(end)));
// previousNodes.put(start, Collections.singletonList(start));
// distances.put(start, 0);
// priorityQueue.add(start);

// if (start.equals(end))
// return new ShortestPath(previousNodes.get(end), 0);

// while (!priorityQueue.isEmpty()) {
// Destination current = priorityQueue.poll();
// if (current.equals(end)) {
// break;
// }

// if (visited.contains(current)) {
// continue;
// }
// visited.add(current);

// for (Distance distance : current.getDistances()) {
// if (distanceIsIllegal(distance, illegalDistances))
// continue;

// Destination neighbor = distance.getOtherDestination(current);
// if (illegalDestinations.contains(neighbor))
// continue;

// int newDistance = distances.get(current) + distance.getDistancesInKms();

// if (newDistance < distances.getOrDefault(neighbor, Integer.MAX_VALUE)) {
// distances.put(neighbor, newDistance);
// // Update the path to the neighbor
// List<Destination> newPath = previousNodes.get(current);
// if (newPath == null)
// newPath = new ArrayList<>();
// else
// newPath = new ArrayList<>(newPath);

// newPath.add(neighbor);
// previousNodes.put(neighbor, newPath);
// priorityQueue.add(neighbor);
// }
// }
// }
// return new ShortestPath(previousNodes.get(end), distances.get(end));
// }

// public boolean distanceIsIllegal(Distance distance, List<Destination[]>
// illegalDistances) {
// Destination destTo = distance.getDestinationTo();
// Destination destFrom = distance.getDestinationFrom();

// for (Destination[] illegalDistance : illegalDistances) {
// boolean destinationToEqual = (destTo.equals(illegalDistance[0]) ||
// destTo.equals(illegalDistance[1]));
// boolean destinationFromEqual = (destFrom.equals(illegalDistance[0]) ||
// destFrom.equals(illegalDistance[1]));

// if (destinationFromEqual && destinationToEqual)
// return true;
// }

// return false;
// }

// public int distanceCalc(List<Destination> path) {
// int distance = 0;
// List<Distance> distances;
// for (int i = 0; i < path.size() - 1; i++) {
// distances = new ArrayList<Distance>(path.get(i).getDistances());
// for (Distance dist : distances) {
// if (dist.getOtherDestination(path.get(i)).equals(path.get(i + 1))) {
// distance += dist.getDistancesInKms();
// break;
// }
// }
// }

// return distance;
// }

// @Transactional
// public List<Destination> createGraph(Destination startDestination) {
// searchDestinationsList.add(startDestination);
// Iterator<Distance> distances;
// for (int j = 0; j < searchDestinationsList.size(); j++) {
// distances = searchDestinationsList.get(j).getDistancesFrom().iterator();
// while (distances.hasNext()) {
// searchDestinationsList.add(distances.next().getDestinationTo());
// }
// distances = searchDestinationsList.get(j).getDistancesTo().iterator();
// while (distances.hasNext()) {
// searchDestinationsList.add(distances.next().getDestinationFrom());
// }
// }

// return this.searchDestinationsList;
// }

// public List<List<Destination>> bfs(Destination[] graph, Destination start,
// Destination end) {
// List<List<Destination>> pathsList = new ArrayList<>();
// Queue<List<Destination>> queue = new LinkedList<>();

// queue.add(new ArrayList<>(List.of(start)));

// while (!queue.isEmpty()) {
// List<Destination> currentPath = queue.poll();
// currentPath.get(currentPath.size() - 1).setVisited(true);
// Destination lastNode = currentPath.get(currentPath.size() - 1);

// if (lastNode == end) {
// pathsList.add(new ArrayList<>(currentPath));
// } else {
// // get shortest distance unvisited neighbour
// Iterator<Distance> distances = lastNode.getDistances().iterator();
// while (distances.hasNext()) {
// Distance current = distances.next();
// if (!current.getDestinationFrom().getVisited()) {
// List<Destination> newPath = new ArrayList<>(currentPath);
// newPath.add(current.getDestinationFrom());
// queue.add(newPath);
// }
// if (!current.getDestinationTo().getVisited()) {
// List<Destination> newPath = new ArrayList<>(currentPath);
// newPath.add(current.getDestinationTo());
// queue.add(newPath);
// }
// }
// }
// }
// return pathsList;
// }

// public void createDestinationPaths(Destination[] graph, Destination start,
// Destination end) {
// // createGraph();
// List<List<Destination>> pathsList = bfs(graph, start, end);
// List<Integer> pathDistances = new ArrayList<>();
// int pathDist = 0;
// for (int i = 0; i < pathsList.size(); i++) {
// for (int j = 0; j < pathsList.get(i).size() - 1; j++) {
// // get the distance from jth destination to j+1th destination and add to
// total
// pathDist += pathsList.get(i).get(j).getDistanceTo(pathsList.get(i).get(j +
// 1));
// }
// pathDistances.add(i, pathDist);
// }
// int tempDist = pathDistances.get(0);
// int indexOfTemp = 0;
// while (pathDistances.size() != 0) {
// for (int i = 1; i < pathDistances.size(); i++) {
// if (tempDist > pathDistances.get(i)) {
// tempDist = pathDistances.get(i);
// indexOfTemp = i;
// }
// }
// searchResults.add(pathsList.get(indexOfTemp));
// pathsList.remove(indexOfTemp);
// pathDistances.remove(indexOfTemp);
// }
// }

// // public List<List<List<Flight>>> createOutputSearchResult() {
// // List<Flight> flights = new ArrayList<Flight>();
// // List<List<List<Flight>>> flightPath = new ArrayList<>();
// // for (int i = 0; i < searchResults.size(); i++) {
// // for (int j = 0; j < searchResults.get(i).size() - 1; j++) {
// // if (j == 0) {
// // flights = flightRepository.getByDepartureAndDestinationAndStopOver(
// // searchResults.get(i).get(j).getDestinationCode(),
// // searchResults.get(i).get(j + 1).getDestinationCode(), null);
// // flightPath.get(i).add(flights);
// // if (searchResults.get(i).size() > 2) {
// // flightPath.get(i + 1).add(flights);
// // flights = flightRepository.getByDepartureAndDestinationAndStopOver(
// // searchResults.get(i).get(j).getDestinationCode(),
// // searchResults.get(i).get(j + 2).getDestinationCode(),
// // searchResults.get(i).get(j + 1).getDestinationCode());
// // flightPath.get(i + 2).add(flights);
// // }
// // } else {
// // flights = flightRepository.getByDepartureAndDestinationAndStopOver(
// // searchResults.get(i).get(j).getDestinationCode(),
// // searchResults.get(i).get(j + 1).getDestinationCode(), null);
// // for (int k = 1; k < flightPath.size(); k++) {
// // if (flightPath.get(k).get(flightPath.get(k).size() -
// // 1).get(0).getDestination() == flights
// // .get(0).getDeparture()) {
// // flightPath.get(k).add(flights);
// // }
// // }
// // if (j < searchResults.get(i).size() - 2) {
// // flights = flightRepository.getByDepartureAndDestinationAndStopOver(
// // searchResults.get(i).get(j).getDestinationCode(),
// // searchResults.get(i).get(j + 2).getDestinationCode(),
// // searchResults.get(i).get(j + 1).getDestinationCode());
// // for (int k = 1; k < flightPath.size(); k++) {
// // if (flightPath.get(k).get(flightPath.get(k).size() -
// // 1).get(0).getDestination() == flights
// // .get(0).getDeparture()) {
// // flightPath.get(k).add(flights);
// // }
// // }
// // }
// // }
// // }
// // }
// // }
// }