package com.uon.seng3160.group2.flightpub.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class FlightModel implements Serializable {
    private String airlineCode;
    private String flightNumber;
    private String departureCode;
    private String stopOverCode;
    private String destinationCode;
    private String departureTime;
    private String arrivalTimeStopOver;
    private String departureTimeStopOver;
    private String arrivalTime;
    private String planeCode;
    private int duration;
    private int durationSecondLeg;
    private boolean groupFlight;
}