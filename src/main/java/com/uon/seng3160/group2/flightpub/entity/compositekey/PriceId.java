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
    @JoinColumn(name = "AirlineCode", referencedColumnName = "AirlineCode", nullable = false)
    private Airline airline;

    @Column(columnDefinition = "VARCHAR(6)", nullable = false)
    private String flightNumber;

    @ManyToOne
    @JoinColumn(name = "ClassCode", referencedColumnName = "ClassCode", nullable = false)
    private TicketClass ticketClass;

    @ManyToOne
    @JoinColumn(name = "TicketCode", referencedColumnName = "TicketCode", nullable = false)
    private TicketType ticketType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime startDate;

    public PriceId() {
    }

    public PriceId(Airline airline, String flightNumber,
            TicketClass ticketClass, TicketType ticketType, LocalDateTime startDate) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.ticketClass = ticketClass;
        this.ticketType = ticketType;
        this.startDate = startDate;
    }
}
