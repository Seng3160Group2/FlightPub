package com.uon.seng3160.group2.flightpub.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TicketClass")
public class TicketClass {
    @Id
    @Column(columnDefinition = "CHAR(3)")
    private String classCode;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String details;

    // referencing tables

    @OneToMany(mappedBy = "ticketClass")
    private Set<Availability> availabilities;

    @OneToMany(mappedBy = "priceId.ticketClass")
    private Set<Price> prices;

    public TicketClass() {
    }

    public TicketClass(String classCode, String details) {
        this.classCode = classCode;
        this.details = details;
        this.availabilities = new HashSet<Availability>();
        this.prices = new HashSet<Price>();
    }
}
