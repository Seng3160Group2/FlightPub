package com.uon.seng3160.group2.flightpub.entity;

import java.time.LocalDateTime;

import com.uon.seng3160.group2.flightpub.entity.compositekey.FlightId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Flights")
public class Flight {

    @EmbeddedId
    FlightId flightId;

    // @MapsId("airlineCode")
    // @ManyToOne
    // @JoinColumn(name = "AirlineCode", referencedColumnName = "AirlineCode")
    // private Airline airline;

    @ManyToOne
    @JoinColumn(name = "DepartureCode", referencedColumnName = "DestinationCode", nullable = false)
    private Destination departure;

    @ManyToOne
    @JoinColumn(name = "StopOverCode", referencedColumnName = "DestinationCode")
    private Destination stopOver;

    @ManyToOne
    @JoinColumn(name = "DestinationCode", referencedColumnName = "DestinationCode", nullable = false)
    private Destination destination;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime arrivalTimeStopOver;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime departureTimeStopOver;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String planeCode;

    @Column(nullable = false)
    private int duration;

    @Column
    private int durationSecondLeg;

    @Column(columnDefinition = "BIT(1) default 0")
    private boolean groupFlight = false;

    public Flight() {
    }

    public Flight(Airline airline, String flightNumber, Destination departure, Destination stopOver,
            Destination destination, LocalDateTime departureTime, LocalDateTime arrivalTimeStopOver,
            LocalDateTime departureTimeStopOver, LocalDateTime arrivalTime, String planeCode, int duration,
            int durationSecondLeg, boolean groupFlight) {
        this.flightId = new FlightId(airline, flightNumber, departureTime);

        this.departure = departure;
        this.stopOver = stopOver;
        this.destination = destination;
        this.arrivalTimeStopOver = arrivalTimeStopOver;
        this.departureTimeStopOver = departureTimeStopOver;
        this.arrivalTime = arrivalTime;
        this.planeCode = planeCode;
        this.duration = duration;
        this.durationSecondLeg = durationSecondLeg;
        this.groupFlight = groupFlight;
    }

}