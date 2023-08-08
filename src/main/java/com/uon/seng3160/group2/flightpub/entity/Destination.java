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
    @JoinColumn(name = "CountryCode3", referencedColumnName = "CountryCode3", nullable = false)
    private Country country;

    // referencing tables

    @OneToMany(mappedBy = "departure")
    Set<Flight> departures;

    @OneToMany(mappedBy = "stopOver")
    Set<Flight> stopOvers;

    @OneToMany(mappedBy = "destination")
    Set<Flight> arrivals;

    @OneToMany(mappedBy = "distancesId.destination1")
    Set<Distances> distancesFrom;

    @OneToMany(mappedBy = "distancesId.destination2")
    Set<Distances> distancesTo;

    public Destination() {
    }

    public Destination(String destinationCode, String airport, Country country) {
        this.destinationCode = destinationCode;
        this.airport = airport;
        this.country = country;
        this.departures = new HashSet<Flight>();
        this.stopOvers = new HashSet<Flight>();
        this.arrivals = new HashSet<Flight>();
        this.distancesFrom = new HashSet<Distances>();
        this.distancesTo = new HashSet<Distances>();
    }
}