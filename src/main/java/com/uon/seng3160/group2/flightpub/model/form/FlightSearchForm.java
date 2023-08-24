package com.uon.seng3160.group2.flightpub.model.form;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.uon.seng3160.group2.flightpub.entity.Destination;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FlightSearchForm {

    //@NotNull
    //@NotEmpty
    //@Size(min = 2, max = 2)
    private String airlineCode;

    //@NotNull
    //@NotEmpty
    //@Size(max = 6)
    private String flightNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivalTime;

    @NotNull
    @NotEmpty
    @Size(max = 3)
    @NotNull
    private String endDestination;

    @NotNull
    @NotEmpty
    @Size(max = 3)
    @NotNull
    private String startDestination;

    @NotNull
    private boolean returnFlight;

    public FlightSearchForm() {
        this.airlineCode = "AA";
        this.flightNumber = "123456";
        this.departureTime = LocalDateTime.now();
        this.arrivalTime = LocalDateTime.now();
        this.startDestination = "AUS";
        this.endDestination = "ARG";
        this.returnFlight = true;
    }

    public FlightSearchForm(String startDestination, String endDestination, LocalDateTime departureTime, LocalDateTime arrivalTime, boolean returnFlight) {
        this.startDestination = startDestination;
        this.endDestination = endDestination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.returnFlight = returnFlight;
    }

    public FlightSearchForm(String airlineCode, String flightNumber, LocalDateTime departureTime) {
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

    public LocalDateTime getArrivalTime() {
        return this.arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getEndDestination() {
        return this.endDestination;
    }

    public void setEndDestination(String endDestination) {
        this.endDestination = endDestination;
    }

    public String getStartDestination() {
        return this.startDestination;
    }

    public void setStartDestination(String startDestination) {
        this.startDestination = startDestination;
    }

    public boolean isReturnFlight() {
        return this.returnFlight;
    }

    public boolean getReturnFlight() {
        return this.returnFlight;
    }

    public void setReturnFlight(boolean returnFlight) {
        this.returnFlight = returnFlight;
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
