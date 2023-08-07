package com.uon.seng3160.group2.flightpub.entity.compositekey;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.uon.seng3160.group2.flightpub.entity.Airline;
import com.uon.seng3160.group2.flightpub.entity.TicketType;
import com.uon.seng3160.group2.flightpub.entity.TicketClass;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Embeddable
public class AvailabilityId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "AirlineCode", referencedColumnName = "AirlineCode")
    private Airline airline;

    @Column(columnDefinition = "VARCHAR(6)")
    private String flightNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime departureTime;

    @ManyToOne
    @JoinColumn(name = "ClassCode", referencedColumnName = "ClassCode")
    private TicketClass classCode;

    @ManyToOne
    @JoinColumn(name = "TicketCode", referencedColumnName = "TicketCode")
    private TicketType ticketCode;

    public AvailabilityId() {
    }

    public Airline getAirline() {
        return this.airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public String getFlightNumber() {
        return this.flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalDateTime getDepartureTime() {
        return this.departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public TicketClass getClassCode() {
        return this.classCode;
    }

    public void setClassCode(TicketClass classCode) {
        this.classCode = classCode;
    }

    public TicketType getTicketCode() {
        return this.ticketCode;
    }

    public void setTicketCode(TicketType ticketCode) {
        this.ticketCode = ticketCode;
    }

    public AvailabilityId(Airline airline, String flightNumber, LocalDateTime departureTime, 
    TicketClass classCode, TicketType ticketCode) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.classCode = classCode;
        this.ticketCode = ticketCode;
    }
}

