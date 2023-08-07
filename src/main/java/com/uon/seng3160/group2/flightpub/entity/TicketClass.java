package com.uon.seng3160.group2.flightpub.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TicketClass")
public class TicketClass {
    @Id 
    @Column(columnDefinition = "CHAR(3)")
    private String classCode;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String details;

    @OneToMany(mappedBy = "availabilityId.classCode")
    private Set<Availability> availabilities;

    @OneToMany(mappedBy = "price.classCode")
    private Set<Price> prices;

    public TicketClass() {
    }

    public TicketClass(String classCode, String details) {
        this.classCode = classCode;
        this.details = details;
        this.availabilities = new HashSet<Availability>();
        this.prices = new HashSet<Price>();
    }

    public String getClassCode() {
        return this.classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    
}
