package com.uon.seng3160.group2.flightpub.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.uon.seng3160.group2.flightpub.entity.compositekey.PriceId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Price")
public class Price {

    @EmbeddedId
    PriceId priceId;

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

    public Price(Airline airline, String flightNumber, TicketClass ticketClass, TicketType ticketType,
            LocalDateTime startDate, LocalDateTime endDate, BigDecimal price, BigDecimal priceLeg1,
            BigDecimal priceLeg2) {
        this.priceId = new PriceId(airline, flightNumber, ticketClass, ticketType, startDate);
        this.endDate = endDate;
        this.price = price;
        this.priceLeg1 = priceLeg1;
        this.priceLeg2 = priceLeg2;
    }
}
