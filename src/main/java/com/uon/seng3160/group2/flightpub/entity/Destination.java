package com.uon.seng3160.group2.flightpub.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Destinations")
public class Destination {
    @Id // nullable = false is implicit with @id
    @Column(columnDefinition = "CHAR(3)")
    private String destinationCode;

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private String airport;

    @ManyToOne
    @JoinColumn(name = "CountryCode2", referencedColumnName = "CountryCode2", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "departure")
    Set<Flight> departures;

    @OneToMany(mappedBy = "stopOver")
    Set<Flight> stopOvers;

    @OneToMany(mappedBy = "destination")
    Set<Flight> arrivals;

    @OneToMany(mappedBy = "distancesId.destination1")
    Set<Distances> destination1;

    @OneToMany(mappedBy = "distancesId.destination2")
    Set<Distances> destination2;

    public Destination() {
    }

    public Destination(String destinationCode, String airport, Country country) {
        this.destinationCode = destinationCode;
        this.airport = airport;
        this.country = country;
        this.departures = new HashSet<Flight>();
        this.stopOvers = new HashSet<Flight>();
        this.arrivals = new HashSet<Flight>();
        this.destination1 = new HashSet<Distances>();
        this.destination2 = new HashSet<Distances>();
    }


    public String getDestinationCode() {
        return this.destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
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
        this.country = country;
    }

    public Set<Flight> getDepartures() {
        return this.departures;
    }

    public void setDepartures(Set<Flight> departures) {
        this.departures = departures;
    }

    public Set<Flight> getStopOvers() {
        return this.stopOvers;
    }

    public void setStopOvers(Set<Flight> stopOvers) {
        this.stopOvers = stopOvers;
    }

    public Set<Flight> getArrivals() {
        return this.arrivals;
    }

    public void setArrivals(Set<Flight> arrivals) {
        this.arrivals = arrivals;
    }

    public Set<Distances> getDestination1() {
        return this.destination1;
    }

    public void setDestination1(Set<Distances> destination1) {
        this.destination1 = destination1;
    }

    public Set<Distances> getDestination2() {
        return this.destination2;
    }

    public void setDestination2(Set<Distances> destination2) {
        this.destination2 = destination2;
    }

}
