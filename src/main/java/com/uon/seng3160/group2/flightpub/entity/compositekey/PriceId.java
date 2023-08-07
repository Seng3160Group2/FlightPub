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
public class PriceId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "AirlineCode", referencedColumnName = "AirlineCode")
    private Airline airline;

    @Column(name = "FlightNumber", columnDefinition = "VARCHAR(6)")
    private String flightNumber;

    @ManyToOne
    @JoinColumn(name = "ClassCode", referencedColumnName = "ClassCode")
    private TicketClass classCode;

    @ManyToOne
    @JoinColumn(name = "TicketCode", referencedColumnName = "TicketCode")
    private TicketType ticketCode;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "StartDate", nullable = false)
    private LocalDateTime startDate;

    public PriceId() {
    }

    public PriceId(Airline airline, String flightNumber, LocalDateTime startDate, 
    TicketClass classCode, TicketType ticketCode) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.startDate = startDate;
        this.classCode = classCode;
        this.ticketCode = ticketCode;
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

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

}

