package com.uon.seng3160.group2.flightpub.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.uon.seng3160.group2.flightpub.entity.compositekey.FlightId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Flights")
public class Flight {

    @EmbeddedId
    FlightId flightId;

    @MapsId("airlineCode")
    @ManyToOne
    @JoinColumn(name = "AirlineCode", referencedColumnName = "AirlineCode", columnDefinition = "CHAR(2)")
    private Airline airline;

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

    @ManyToOne
    @JoinColumn(name = "PlaneCode", referencedColumnName = "PlaneCode", nullable = false)
    private PlaneType planeType;

    @Column(nullable = false)
    private Integer duration;

    @Column
    private Integer durationSecondLeg = 0;

    @Column(columnDefinition = "BIT(1) default 0")
    private boolean groupFlight = false;

    // referencing tables

    @OneToMany(mappedBy = "flight")
    private Set<Availability> availabilities;

    public Flight() {
    }

    public Flight(FlightId flightId, Airline airline, Destination departure, Destination stopOver,
            Destination destination, LocalDateTime arrivalTimeStopOver,
            LocalDateTime departureTimeStopOver, LocalDateTime arrivalTime, PlaneType planeType, int duration,
            int durationSecondLeg, boolean groupFlight) {
        this.flightId = flightId;
        this.airline = airline;
        this.airline.addFlight(this);
        this.departure = departure;
        this.departure.addDeparture(this);
        this.stopOver = stopOver;
        this.stopOver.addStopOver(this);
        this.destination = destination;
        this.destination.addArrival(this);
        this.arrivalTimeStopOver = arrivalTimeStopOver;
        this.departureTimeStopOver = departureTimeStopOver;
        this.arrivalTime = arrivalTime;
        this.planeType = planeType;
        this.planeType.addFlight(this);
        this.duration = duration;
        this.durationSecondLeg = durationSecondLeg;
        this.groupFlight = groupFlight;
        this.availabilities = new HashSet<Availability>();
    }

    public FlightId getFlightId() {
        return this.flightId;
    }

    public void setFlightId(FlightId flightId) {
        this.flightId = flightId;
    }

    public Airline getAirline() {
        return this.airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
        this.airline.addFlight(this);
    }

    public Destination getDeparture() {
        return this.departure;
    }

    public void setDeparture(Destination departure) {
        this.departure.removeDeparture(this);
        this.departure = departure;
        this.departure.addDeparture(this);
    }

    public Destination getStopOver() {
        return this.stopOver;
    }

    public void setStopOver(Destination stopOver) {
        this.stopOver.removeStopOver(this);
        this.stopOver = stopOver;
        this.stopOver.addStopOver(this);
    }

    public Destination getDestination() {
        return this.destination;
    }

    public void setDestination(Destination destination) {
        this.destination.removeArrival(this);
        this.destination = destination;
        this.destination.addArrival(this);
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

    public PlaneType getPlaneType() {
        return this.planeType;
    }

    public void setPlaneType(PlaneType planeType) {
        this.planeType.removeFlight(this);
        this.planeType = planeType;
        this.planeType.addFlight(this);
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

    public Set<Availability> getAvailabilities() {
        return this.availabilities;
    }

    public void addAvailability(Availability availability) {
        this.availabilities.add(availability);
    }

    public void removeAvailability(Availability availability) {
        if (availability != null)
            this.availabilities.remove(availability);
    }

    @Override
    public String toString() {
        String stopOverCode = (getStopOver() != null) ? getStopOver().getDestinationCode() : "";
        return "{" +
                getFlightId().getFlightNumber() +
                // ", airline='" + getAirline() + "'" +
                "'" + getDeparture().getDestinationCode() + " -> " + stopOverCode + " -> "
                + getDestination().getDestinationCode() + "'" +
                ", depTime='" + getFlightId().getDepartureTime() + "'" +
                ", arrTimeStop='" + getArrivalTimeStopOver() + "'" +
                ", depTimeStop='" + getDepartureTimeStopOver() + "'" +
                ", arrTime='" + getArrivalTime() + "'" +
                // ", planeType='" + getPlaneType() + "'" +
                // ", duration='" + getDuration() + "'" +
                // ", durationSecondLeg='" + getDurationSecondLeg() + "'" +
                // ", groupFlight='" + isGroupFlight() + "'" +
                "}";
    }

}