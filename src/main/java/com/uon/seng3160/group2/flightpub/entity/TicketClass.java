package com.uon.seng3160.group2.flightpub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TicketClass")
public class TicketClass {
    @Id 
    @Column(columnDefinition = "CHAR(3)")
    private String classCode;

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    private String details;

    public TicketClass() {
    }

    public TicketClass(String classCode, String details) {
        this.classCode = classCode;
        this.details = details;
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
