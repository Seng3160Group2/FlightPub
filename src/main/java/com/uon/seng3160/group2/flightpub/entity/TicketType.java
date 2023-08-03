package com.uon.seng3160.group2.flightpub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TicketType")
public class TicketType {
    @Id 
    @Column(columnDefinition = "CHAR(1)")
    private String ticketCode;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String name;

    public TicketType() {
    }

    public TicketType(String ticketCode, String name) {
        this.ticketCode = ticketCode;
        this.name = name;
    }

    public String getTicketCode() {
        return this.ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}