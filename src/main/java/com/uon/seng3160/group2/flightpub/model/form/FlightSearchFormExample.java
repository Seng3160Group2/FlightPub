package com.uon.seng3160.group2.flightpub.model.form;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FlightSearchFormExample {

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 2)
    private String airlineCode;

    @NotNull
    @NotEmpty
    @Size(max = 6)
    private String flightNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureTime;

    public FlightSearchFormExample() {
        this.airlineCode = "AA";
        this.flightNumber = "123456";
        this.departureTime = LocalDateTime.now();
    }

    public FlightSearchFormExample(String airlineCode, String flightNumber, LocalDateTime departureTime) {
        this.airlineCode = airlineCode;
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
    }

    public String getAirlineCode() {
        return this.airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getFlightNumber() {
        return this.flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalDateTime getDepartureTime() {
        return this.departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return "{" +
                " flightNumber='" + getFlightNumber() + "'" +
                ", airlineCode='" + getAirlineCode() + "'" +
                ", departureTime='" + getDepartureTime() + "'" +
                "}";
    }

}
