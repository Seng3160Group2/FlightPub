package com.uon.seng3160.group2.flightpub.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.uon.seng3160.group2.flightpub.entity.compositekey.PriceId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Price")
public class Price {

    @EmbeddedId
    PriceId priceId;

    @MapsId("airlineCode")
    @ManyToOne
    @JoinColumn(name = "AirlineCode", referencedColumnName = "AirlineCode", columnDefinition = "CHAR(2)")
    private Airline airline;

    @MapsId("classCode")
    @ManyToOne
    @JoinColumn(name = "ClassCode", referencedColumnName = "ClassCode", columnDefinition = "CHAR(3)")
    private TicketClass ticketClass;

    @MapsId("ticketCode")
    @ManyToOne
    @JoinColumn(name = "TicketCode", referencedColumnName = "TicketCode", columnDefinition = "CHAR(1)")
    private TicketType ticketType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private BigDecimal priceLeg1;

    @Column
    private BigDecimal priceLeg2;

    public Price() {
    }

    public Price(PriceId priceId, Airline airline, TicketClass ticketClass, TicketType ticketType,
            LocalDateTime endDate, BigDecimal price, BigDecimal priceLeg1, BigDecimal priceLeg2) {
        this.priceId = priceId;
        this.airline = airline;
        this.airline.addPrice(this);
        this.ticketClass = ticketClass;
        this.ticketClass.addPrice(this);
        this.ticketType = ticketType;
        this.ticketType.addPrice(this);
        this.endDate = endDate;
        this.price = price;
        this.priceLeg1 = priceLeg1;
        this.priceLeg2 = priceLeg2;
    }

    public PriceId getPriceId() {
        return this.priceId;
    }

    public void setPriceId(PriceId priceId) {
        this.priceId = priceId;
    }

    public Airline getAirline() {
        return this.airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
        this.airline.addPrice(this);
    }

    public TicketClass getTicketClass() {
        return this.ticketClass;
    }

    public void setTicketClass(TicketClass ticketClass) {
        this.ticketClass = ticketClass;
        this.ticketClass.addPrice(this);
    }

    public TicketType getTicketType() {
        return this.ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
        this.ticketType.addPrice(this);
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceLeg1() {
        return this.priceLeg1;
    }

    public void setPriceLeg1(BigDecimal priceLeg1) {
        this.priceLeg1 = priceLeg1;
    }

    public BigDecimal getPriceLeg2() {
        return this.priceLeg2;
    }

    public void setPriceLeg2(BigDecimal priceLeg2) {
        this.priceLeg2 = priceLeg2;
    }

}