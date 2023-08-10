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

    // referencing tables

    @OneToMany(mappedBy = "airline")
    private Set<Flight> flights;

    @OneToMany(mappedBy = "airline")
    private Set<Price> prices;

    public Airline() {
    }

    public Airline(String airlineCode, String airlineName, Country country) {
        this.airlineCode = airlineCode;
        this.airlineName = airlineName;
        this.country = country;
        this.country.addAirline(this);
        this.flights = new HashSet<Flight>();
        this.prices = new HashSet<Price>();
    }

    public String getAirlineCode() {
        return this.airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirlineName() {
        return this.airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country.removeAirline(this);
        this.country = country;
        this.country.addAirline(this);
    }

    public Set<Flight> getFlights() {
        return this.flights;
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }

    public void removeFlight(Flight flight) {
        if (flight != null)
            this.flights.remove(flight);
    }

    public Set<Price> getPrices() {
        return this.prices;
    }

    public void addPrice(Price price) {
        this.prices.add(price);
    }

    public void removePrice(Price price) {
        if (price != null)
            this.prices.remove(price);
    }
}
