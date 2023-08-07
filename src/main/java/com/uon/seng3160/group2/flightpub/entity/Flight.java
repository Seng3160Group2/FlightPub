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

    public FlightId getFlightId() {
        return this.flightId;
    }

    public void setFlightId(FlightId flightId) {
        this.flightId = flightId;
    }

    public Destination getDeparture() {
        return this.departure;
    }

    public void setDeparture(Destination departure) {
        this.departure = departure;
    }

    public Destination getStopOver() {
        return this.stopOver;
    }

    public void setStopOver(Destination stopOver) {
        this.stopOver = stopOver;
    }

    public Destination getDestination() {
        return this.destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public LocalDateTime getArrivalTimeStopOver() {
        return this.arrivalTimeStopOver;
    }

    public void setArrivalTimeStopOver(LocalDateTime arrivalTimeStopOver) {
        this.arrivalTimeStopOver = arrivalTimeStopOver;
    }

    public LocalDateTime getDepartureTimeStopOver() {
        return this.departureTimeStopOver;
    }

    public void setDepartureTimeStopOver(LocalDateTime departureTimeStopOver) {
        this.departureTimeStopOver = departureTimeStopOver;
    }

    public LocalDateTime getArrivalTime() {
        return this.arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getPlaneCode() {
        return this.planeCode;
    }

    public void setPlaneCode(String planeCode) {
        this.planeCode = planeCode;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDurationSecondLeg() {
        return this.durationSecondLeg;
    }

    public void setDurationSecondLeg(int durationSecondLeg) {
        this.durationSecondLeg = durationSecondLeg;
    }

    public boolean isGroupFlight() {
        return this.groupFlight;
    }

    public boolean getGroupFlight() {
        return this.groupFlight;
    }

    public void setGroupFlight(boolean groupFlight) {
        this.groupFlight = groupFlight;
    }

}