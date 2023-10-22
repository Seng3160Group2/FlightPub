package com.uon.seng3160.group2.flightpub.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Country", indexes = @Index(columnList = "CountryCode3"))
public class Country {

    @Id // nullable = false is implicit with @id
    @Column(columnDefinition = "CHAR(2)")
    private String countryCode2;

    @Column(columnDefinition = "CHAR(3)", nullable = false, unique = true)
    private String countryCode3;

    @Column(columnDefinition = "VARCHAR(80) default ''", nullable = false)
    private String countryName = "";

    @Column
    private String alternateName1 = "";

    @Column
    private String alternateName2 = "";

    @Column(columnDefinition = "CHAR(3)")
    private String motherCountryCode3;

    @Column
    private String motherCountryComment = "";

    // referencing tables

    @OneToMany(mappedBy = "country")
    private Set<Airline> airlines;

    @OneToMany(mappedBy = "country")
    private Set<Destination> destinations;

    public Country() {
    }

    public Country(String countryCode2, String countryCode3, String countryName) {
        this.countryCode2 = countryCode2;
        this.countryCode3 = countryCode3;
        this.countryName = countryName;
        this.airlines = new HashSet<Airline>();
        this.destinations = new HashSet<Destination>();
    }

    public String getCountryCode2() {
        return this.countryCode2;
    }

    public String getCountryCode3() {
        return this.countryCode3;
    }

    public void setCountryCode3(String countryCode3) {
        this.countryCode3 = countryCode3;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getAlternateName1() {
        return this.alternateName1;
    }

    public void setAlternateName1(String alternateName1) {
        this.alternateName1 = alternateName1;
    }

    public String getAlternateName2() {
        return this.alternateName2;
    }

    public void setAlternateName2(String alternateName2) {
        this.alternateName2 = alternateName2;
    }

    public String getMotherCountryCode3() {
        return this.motherCountryCode3;
    }

    public void setMotherCountryCode3(String motherCountryCode3) {
        this.motherCountryCode3 = motherCountryCode3;
    }

    public String getMotherCountryComment() {
        return this.motherCountryComment;
    }

    public void setMotherCountryComment(String motherCountryComment) {
        this.motherCountryComment = motherCountryComment;
    }

    public Set<Airline> getAirlines() {
        return this.airlines;
    }

    public void addAirline(Airline airline) {
        this.airlines.add(airline);
    }

    public void removeAirline(Airline airline) {
        if (airline != null)
            this.airlines.remove(airline);
    }

    public Set<Destination> getDestinations() {
        return this.destinations;
    }

    public void AddDestination(Destination destination) {
        this.destinations.add(destination);
    }

    public void removeDestination(Destination destination) {
        if (destination != null)
            this.destinations.remove(destination);
    }
}
