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
@Table(name = "Airlines")
public class Airline {
    @Id // nullable = false is implicit with @id
    @Column(columnDefinition = "CHAR(2)")
    private String airlineCode;

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    private String airlineName;

    @ManyToOne
    @JoinColumn(name = "CountryCode3", referencedColumnName = "CountryCode3", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "flightId.airline")
    Set<Flight> flights;

    @OneToMany(mappedBy = "availabilityId.airline")
    Set<Availability> availabilities;

    @OneToMany(mappedBy = "priceId.airline")
    Set<Price> prices;

    public Airline() {
    }

    public Airline(String airlineCode, String airlineName, Country country) {
        this.airlineCode = airlineCode;
        this.airlineName = airlineName;
        this.country = country;
        this.flights = new HashSet<Flight>();
        this.availabilities = new HashSet<Availability>();
        this.prices = new HashSet<Price>();
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }

    public void removeFlight(Flight flight) {
        flights.remove(flight);
    }

    public Set<Flight> getFlights() {
        return flights;
    }

}
