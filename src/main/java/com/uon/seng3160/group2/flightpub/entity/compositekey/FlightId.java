package com.uon.seng3160.group2.flightpub.entity.compositekey;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.uon.seng3160.group2.flightpub.entity.Airline;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Embeddable
public class FlightId implements Serializable {

    // @Column(columnDefinition = "CHAR(2)")
    // private String airlineCode;

    @ManyToOne
    @JoinColumn(name = "AirlineCode", referencedColumnName = "AirlineCode")
    private Airline airline;

    @Column(columnDefinition = "VARCHAR(6)")
    private String flightNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime departureTime;

    public FlightId() {
    }

    public FlightId(Airline airline, String flightNumber, LocalDateTime departureTime) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
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
    

}
