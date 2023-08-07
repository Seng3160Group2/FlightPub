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

    public Price(Airline airline, String flightNumber, TicketClass classCode, TicketType ticketCode, 
        LocalDateTime startDate, LocalDateTime endDate, BigDecimal price, BigDecimal priceLeg1, BigDecimal priceLeg2) {
        this.priceId = new PriceId(airline, flightNumber, startDate, classCode, ticketCode);
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
