package com.uon.seng3160.group2.flightpub.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Country")
public class Country {

    @Column(columnDefinition = "CHAR(2)")
    private String countryCode1;

    @Id // nullable = false is implicit with @id
    @Column(columnDefinition = "CHAR(3)", nullable = false)
    private String countryCode2;

    @Column(columnDefinition = "VARCHAR(80) default ''", nullable = false)
    private String countryName = "";

    @OneToMany(mappedBy = "country")
    private Set<Airline> airlines;

    @OneToMany(mappedBy = "country")
    private Set<Destination> destinations;

    public Country() {
    }

    public Country(String countryCode1, String countryCode2, String countryName) {
        this.countryCode1 = countryCode1;
        this.countryCode2 = countryCode2;
        this.countryName = countryName;
        this.airlines = new HashSet<Airline>();
        this.destinations = new HashSet<Destination>();
    }
}
