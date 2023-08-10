package com.uon.seng3160.group2.flightpub.entity.compositekey;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Embeddable
public class AvailabilityId implements Serializable {

    private FlightId flightId;

    private String classCode;

    private String ticketCode;

    public AvailabilityId() {
    }

    public AvailabilityId(FlightId flightId, String classCode, String ticketCode) {
        this.flightId = flightId;
        this.classCode = classCode;
        this.ticketCode = ticketCode;
    }

    public FlightId getFlightId() {
        return this.flightId;
    }

    public void setFlightId(FlightId flightId) {
        this.flightId = flightId;
    }

    public String getClassCode() {
        return this.classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getTicketCode() {
        return this.ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }
}
