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
        this.country = country;
    }

    public Set<Flight> getFlights() {
        return this.flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }

    public Set<Availability> getAvailabilities() {
        return this.availabilities;
    }

    public void setAvailabilities(Set<Availability> availabilities) {
        this.availabilities = availabilities;
    }

    public Set<Price> getPrices() {
        return this.prices;
    }

    public void setPrices(Set<Price> prices) {
        this.prices = prices;
    }
    

}
