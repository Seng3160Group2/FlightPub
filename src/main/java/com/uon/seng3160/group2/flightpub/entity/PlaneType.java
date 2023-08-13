package com.uon.seng3160.group2.flightpub.entity;

import java.util.HashSet;
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
        this.flights = new HashSet<Flight>();
    }

    public String getPlaneCode() {
        return this.planeCode;
    }

    public void setPlaneCode(String planeCode) {
        this.planeCode = planeCode;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getNumFirstClass() {
        return this.numFirstClass;
    }

    public void setNumFirstClass(int numFirstClass) {
        this.numFirstClass = numFirstClass;
    }

    public int getNumBusiness() {
        return this.numBusiness;
    }

    public void setNumBusiness(int numBusiness) {
        this.numBusiness = numBusiness;
    }

    public int getNumPremiumEconomy() {
        return this.numPremiumEconomy;
    }

    public void setNumPremiumEconomy(int numPremiumEconomy) {
        this.numPremiumEconomy = numPremiumEconomy;
    }

    public int getEconomy() {
        return this.economy;
    }

    public void setEconomy(int economy) {
        this.economy = economy;
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
}
