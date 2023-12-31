package com.uon.seng3160.group2.flightpub.entity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "Destinations")
@EqualsAndHashCode
public class Destination {
    @Id // nullable = false is implicit with @id
    @Column(columnDefinition = "CHAR(3)")
    private String destinationCode;

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private String airport;

    @ManyToOne
    @JoinColumn(name = "CountryCode3", referencedColumnName = "CountryCode3", nullable = false)
    private Country country;

    // referencing tables

    @OneToMany(mappedBy = "departure")
    Set<Flight> departures;

    @OneToMany(mappedBy = "stopOver")
    Set<Flight> stopOvers;

    @OneToMany(mappedBy = "destination")
    Set<Flight> arrivals;

    @OneToMany(mappedBy = "destinationFrom")
    Set<Distance> distancesFrom;

    @OneToMany(mappedBy = "destinationTo")
    Set<Distance> distancesTo;

    @Transient
    private boolean illegal = false;

    public Destination() {
    }

    public Destination(String destinationCode, String airport, Country country) {
        this.destinationCode = destinationCode;
        this.airport = airport;
        this.country = country;
        this.country.AddDestination(this);
        this.departures = new HashSet<Flight>();
        this.stopOvers = new HashSet<Flight>();
        this.arrivals = new HashSet<Flight>();
        this.distancesFrom = new HashSet<Distance>();
        this.distancesTo = new HashSet<Distance>();
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public String getDestinationCode() {
        return this.destinationCode;
    }

    public String getAirport() {
        return this.airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country.removeDestination(this);
        this.country = country;
        this.country.AddDestination(this);
    }

    public Set<Flight> getDepartures() {
        return this.departures;
    }

    public void addDeparture(Flight departure) {
        this.departures.add(departure);
    }

    public void removeDeparture(Flight departure) {
        if (departure != null)
            this.departures.remove(departure);
    }

    public Set<Flight> getStopOvers() {
        return this.stopOvers;
    }

    public void addStopOver(Flight stopOver) {
        this.stopOvers.add(stopOver);
    }

    public void removeStopOver(Flight stopOver) {
        if (stopOver != null)
            this.stopOvers.remove(stopOver);
    }

    public Set<Flight> getArrivals() {
        return this.arrivals;
    }

    public void addArrival(Flight arrival) {
        this.arrivals.add(arrival);
    }

    public void removeArrival(Flight arrival) {
        if (arrival != null)
            this.arrivals.remove(arrival);
    }

    public Set<Distance> getDistancesFrom() {
        return this.distancesFrom;
    }

    public void addDistanceFrom(Distance distanceFrom) {
        this.distancesFrom.add(distanceFrom);
    }

    public void removeDistanceFrom(Distance distanceFrom) {
        if (distanceFrom != null)
            this.distancesFrom.remove(distanceFrom);
    }

    public Set<Distance> getDistancesTo() {
        return this.distancesTo;
    }

    public void AddDistanceTo(Distance distanceTo) {
        this.distancesTo.add(distanceTo);
    }

    public void removeDistanceTo(Distance distanceTo) {
        if (distanceTo != null)
            this.distancesTo.remove(distanceTo);
    }

    public Set<Distance> getDistances() {
        HashSet<Distance> allDistances = new HashSet<>();
        allDistances.addAll(this.getDistancesFrom());
        allDistances.addAll(this.getDistancesTo());

        return allDistances;
    }

    public void setIllegal(boolean illegal) {
        this.illegal = illegal;
    }

    public boolean isIllegal() {
        return this.illegal;
    }

    @Override
    public String toString() {
        return "{" +
                "'" + getDestinationCode() + "'" +
                // ", airport='" + getAirport() + "'" +
                // ", country='" + getCountry() + "'" +
                // ", departures='" + getDepartures() + "'" +
                // ", stopOvers='" + getStopOvers() + "'" +
                // ", arrivals='" + getArrivals() + "'" +
                // ", distancesFrom='" + getDistancesFrom() + "'" +
                // ", distancesTo='" + getDistancesTo() + "'" +
                // ", visited='" + getVisited() + "'" +
                "}";
    }

}