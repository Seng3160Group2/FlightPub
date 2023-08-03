package com.uon.seng3160.group2.flightpub.entity;

import java.time.LocalDateTime;

import com.uon.seng3160.group2.flightpub.entity.compositekey.AvailabilityId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import jakarta.persistence.Table;


@Entity
@Table(name = "Availability")
public class Availability {

    @EmbeddedId
    AvailabilityId availabilityId;

    @Column(nullable = false)
    private int numberAvailableSeatsLeg1;

    @Column
    private int numberAvailableSeatsLeg2;

    public Availability() {
    }

    public Availability(Airline airline, String flightNumber, LocalDateTime departureTime, TicketClass classCode,
            TicketType ticketCode, int numberAvailableSeatsLeg1, int numberAvailableSeatsLeg2) {

        this.availabilityId = new AvailabilityId(airline, flightNumber, departureTime, classCode, ticketCode);
        this.numberAvailableSeatsLeg1 = numberAvailableSeatsLeg1;
        this.numberAvailableSeatsLeg2 = numberAvailableSeatsLeg2;
        
    }

    public AvailabilityId getAvailabilityId() {
        return this.availabilityId;
    }

    public void setAvailabilityId(AvailabilityId availabilityId) {
        this.availabilityId = availabilityId;
    }

    public int getNumberAvailableSeatsLeg1() {
        return this.numberAvailableSeatsLeg1;
    }

    public void setNumberAvailableSeatsLeg1(int numberAvailableSeatsLeg1) {
        this.numberAvailableSeatsLeg1 = numberAvailableSeatsLeg1;
    }

    public int getNumberAvailableSeatsLeg2() {
        return this.numberAvailableSeatsLeg2;
    }

    public void setNumberAvailableSeatsLeg2(int numberAvailableSeatsLeg2) {
        this.numberAvailableSeatsLeg2 = numberAvailableSeatsLeg2;
    }

}
