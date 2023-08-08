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

    @Column(columnDefinition = "BIT(1) default 1")
    private boolean transferrable;

    @Column(columnDefinition = "BIT(1) default 1")
    private boolean refundable;

    @Column(columnDefinition = "BIT(1) default 0")
    private boolean exchangeable;

    @Column(columnDefinition = "BIT(1) default 1")
    private boolean frequentFlyerPoints;

    // referencing tables

    @OneToMany(mappedBy = "ticketType")
    private Set<Availability> availabilities;

    @OneToMany(mappedBy = "priceId.ticketType")
    private Set<Price> prices;

    public TicketType() {
    }

    public TicketType(String ticketCode, String name) {
        this.ticketCode = ticketCode;
        this.name = name;
        this.availabilities = new HashSet<Availability>();
        this.prices = new HashSet<Price>();
    }
}