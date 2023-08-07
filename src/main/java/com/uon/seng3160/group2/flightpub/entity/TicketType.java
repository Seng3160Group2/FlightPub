package com.uon.seng3160.group2.flightpub.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TicketType")
public class TicketType {
    @Id 
    @Column(columnDefinition = "CHAR(1)")
    private String ticketCode;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String name;

    @OneToMany(mappedBy = "availabilityId.ticketCode")
    private Set<Availability> availabilities;

    @OneToMany(mappedBy = "price.ticketCode")
    private Set<Price> prices;


    public TicketType() {
    }

    public TicketType(String ticketCode, String name) {
        this.ticketCode = ticketCode;
        this.name = name;
        this.availabilities = new HashSet<Availability>();
        this.prices = new HashSet<Price>();
    }


    public String getTicketCode() {
        return this.ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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