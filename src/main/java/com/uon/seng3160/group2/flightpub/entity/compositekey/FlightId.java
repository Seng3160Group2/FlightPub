package com.uon.seng3160.group2.flightpub.entity.compositekey;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Embeddable
public class FlightId implements Serializable {

    private String airlineCode;

    @Column(columnDefinition = "VARCHAR(6)")
    private String flightNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime departureTime;

    public FlightId() {
    }

    public FlightId(String airlineCode, String flightNumber, LocalDateTime departureTime) {
        this.airlineCode = airlineCode;
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
    }
}
