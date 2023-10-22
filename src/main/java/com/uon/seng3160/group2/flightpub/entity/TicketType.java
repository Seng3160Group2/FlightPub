package com.uon.seng3160.group2.flightpub.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.type.NumericBooleanConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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

    @Convert(converter = NumericBooleanConverter.class)
    private Boolean transferrable = true;

    @Convert(converter = NumericBooleanConverter.class)
    private Boolean refundable = false;

    @Convert(converter = NumericBooleanConverter.class)
    private Boolean exchangeable = true;

    @Convert(converter = NumericBooleanConverter.class)
    private Boolean frequentFlyerPoints = true;

    // referencing tables

    @OneToMany(mappedBy = "ticketType")
    private Set<Availability> availabilities;

    @OneToMany(mappedBy = "ticketType")
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

    public boolean isTransferrable() {
        return this.transferrable;
    }

    public boolean getTransferrable() {
        return this.transferrable;
    }

    public void setTransferrable(boolean transferrable) {
        this.transferrable = transferrable;
    }

    public boolean isRefundable() {
        return this.refundable;
    }

    public boolean getRefundable() {
        return this.refundable;
    }

    public void setRefundable(boolean refundable) {
        this.refundable = refundable;
    }

    public boolean isExchangeable() {
        return this.exchangeable;
    }

    public boolean getExchangeable() {
        return this.exchangeable;
    }

    public void setExchangeable(boolean exchangeable) {
        this.exchangeable = exchangeable;
    }

    public boolean isFrequentFlyerPoints() {
        return this.frequentFlyerPoints;
    }

    public boolean getFrequentFlyerPoints() {
        return this.frequentFlyerPoints;
    }

    public void setFrequentFlyerPoints(boolean frequentFlyerPoints) {
        this.frequentFlyerPoints = frequentFlyerPoints;
    }

    public Set<Availability> getAvailabilities() {
        return this.availabilities;
    }

    public void addAvailability(Availability availability) {
        this.availabilities.add(availability);
    }

    public void removeAvailability(Availability availability) {
        if (availability != null)
            this.availabilities.remove(availability);
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