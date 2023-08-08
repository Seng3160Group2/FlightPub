package com.uon.seng3160.group2.flightpub.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PlaneType")
public class PlaneType {
    @Id
    @Column(columnDefinition = "VARCHAR(20)")
    private String planeCode;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String details;

    @Column(length = 11, nullable = false)
    private int numFirstClass;

    @Column(length = 11, nullable = false)
    private int numBusiness;

    @Column(length = 11, nullable = false)
    private int numPremiumEconomy;

    @Column(length = 11, nullable = false)
    private int economy;

    // referencing tables

    @OneToMany(mappedBy = "planeType")
    private Set<Flight> flights;

    public PlaneType() {
    }

    public PlaneType(String planeCode, String details, int numFirstClass, int numBusiness, int numPremiumEconomy,
            int economy) {
        this.planeCode = planeCode;
        this.details = details;
        this.numFirstClass = numFirstClass;
        this.numBusiness = numBusiness;
        this.numPremiumEconomy = numPremiumEconomy;
        this.economy = economy;
    }

}
