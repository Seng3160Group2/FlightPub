package com.uon.seng3160.group2.flightpub.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.uon.seng3160.group2.flightpub.entity.compositekey.AvailabilityId;
import com.uon.seng3160.group2.flightpub.entity.compositekey.FlightId;

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

    public Availability(FlightId flightId, String classCode, String ticketCode, int numberAvailableSeatsLeg1,
            int numberAvailableSeatsLeg2) {

        this.availabilityId = new AvailabilityId(flightId, classCode, ticketCode);
        this.numberAvailableSeatsLeg1 = numberAvailableSeatsLeg1;
        this.numberAvailableSeatsLeg2 = numberAvailableSeatsLeg2;
    }
}
