package com.uon.seng3160.group2.flightpub.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uon.seng3160.group2.flightpub.entity.Destination;
import com.uon.seng3160.group2.flightpub.entity.Flight;
import com.uon.seng3160.group2.flightpub.entity.compositekey.FlightId;

public interface FlightRepository extends JpaRepository<Flight, FlightId> {

    List<Flight> getByDepartureAndDestinationAndStopOver(Destination departure, Destination destination,
            Destination stopOver);

    @Query("SELECT f FROM Flight f " +
            "WHERE ((f.departure = :departure AND f.destination = :destination " +
            "AND f.flightId.departureTime BETWEEN :startDateTime AND :endDateTime)" +
            "OR (f.departure = :departure AND f.stopOver = :destination " +
            "AND f.flightId.departureTime BETWEEN :startDateTime AND :endDateTime)" +
            "OR (f.stopOver = :departure AND f.destination = :destination " +
            "AND f.departureTimeStopOver BETWEEN :startDateTime AND :endDateTime))")
    List<Flight> findFlightsBetweenTimes(
            @Param("departure") Destination departure,
            @Param("destination") Destination destination,
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime);

    // if departure = departureDest && destination = destinationDest
    // if departure = departureDest && stopOver = destinationDest
    // if stopOver = departureDest && destination = destinationDest
}
