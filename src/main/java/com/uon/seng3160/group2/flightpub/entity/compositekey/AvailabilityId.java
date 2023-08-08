package com.uon.seng3160.group2.flightpub.entity.compositekey;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Embeddable
public class AvailabilityId implements Serializable {

    private FlightId flightId;

    @Column(columnDefinition = "CHAR(3)")
    private String classCode;

    @Column(columnDefinition = "CHAR(1)")
    private String ticketCode;

    public AvailabilityId() {
    }

    public AvailabilityId(FlightId flightId, String classCode, String ticketCode) {
        this.flightId = flightId;
        this.classCode = classCode;
        this.ticketCode = ticketCode;
    }
}
