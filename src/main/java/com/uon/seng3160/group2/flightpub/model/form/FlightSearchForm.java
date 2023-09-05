package com.uon.seng3160.group2.flightpub.model.form;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class FlightSearchForm {

    @NotNull
    @NotEmpty
    private String departure;

    @NotNull
    @NotEmpty
    private String destination;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime returnTime;

    public FlightSearchForm() {
        this.departure = "Adelaide";
        this.destination = "Sydney";
        this.departureTime = LocalDateTime.of(2014, 9, 23, 0, 0, 0, 0);
        this.returnTime = LocalDateTime.of(2014, 9, 27, 0, 0, 0, 0);
    }

    public FlightSearchForm(String departure, String destination, LocalDateTime departureTime,
            LocalDateTime returnTime) {
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
        this.returnTime = returnTime;
    }

    public String getDeparture() {
        return this.departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return this.departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getReturnTime() {
        return this.returnTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }
}
