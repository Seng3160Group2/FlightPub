package com.uon.seng3160.group2.flightpub.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.uon.seng3160.group2.flightpub.entity.compositekey.AvailabilityId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;

@Entity
@Table(name = "Availability")
public class Availability {

    @EmbeddedId
    public AvailabilityId availabilityId;

    @MapsId("flightId")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "AirlineCode", referencedColumnName = "AirlineCode", columnDefinition = "CHAR(2)"),
            @JoinColumn(name = "FlightNumber", referencedColumnName = "FlightNumber", columnDefinition = "VARCHAR(6)"),
            @JoinColumn(name = "DepartureTime", referencedColumnName = "DepartureTime", columnDefinition = "DATETIME"),
    })
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Flight flight;

    @MapsId("classCode")
    @ManyToOne
    @JoinColumn(name = "ClassCode", referencedColumnName = "ClassCode", columnDefinition = "CHAR(3)")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public TicketClass ticketClass;

    @MapsId("ticketCode")
    @ManyToOne
    @JoinColumn(name = "TicketCode", referencedColumnName = "TicketCode", columnDefinition = "CHAR(1)")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public TicketType ticketType;

    @Column(nullable = false)
    private int numberAvailableSeatsLeg1;

    @Column
    private int numberAvailableSeatsLeg2;

    public Availability() {
    }

    public Availability(AvailabilityId availabilityId, Flight flight, TicketClass ticketClass, TicketType ticketType,
            int numberAvailableSeatsLeg1,
            int numberAvailableSeatsLeg2) {

        this.availabilityId = availabilityId;
        this.flight = flight;
        this.flight.addAvailability(this);
        this.ticketClass = ticketClass;
        this.ticketClass.addAvailability(this);
        this.ticketType = ticketType;
        this.ticketType.addAvailability(this);
        this.numberAvailableSeatsLeg1 = numberAvailableSeatsLeg1;
        this.numberAvailableSeatsLeg2 = numberAvailableSeatsLeg2;
    }

    public AvailabilityId getAvailabilityId() {
        return this.availabilityId;
    }

    public void setAvailabilityId(AvailabilityId availabilityId) {
        this.availabilityId = availabilityId;
    }

    public Flight getFlight() {
        return this.flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
        this.flight.addAvailability(this);
    }

    public TicketClass getTicketClass() {
        return this.ticketClass;
    }

    public void setTicketClass(TicketClass ticketClass) {
        this.ticketClass = ticketClass;
        this.ticketClass.addAvailability(this);
    }

    public TicketType getTicketType() {
        return this.ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
        this.ticketType.addAvailability(this);
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
