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

    @Column(columnDefinition = "CHAR(3)", nullable = false)
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

    @OneToMany(mappedBy = "country")
    private Set<Airline> airlines;

    // referencing tables

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
}
