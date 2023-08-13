package com.uon.seng3160.group2.flightpub.model;

public class FlightModel {
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

    public FlightModel() {
    }

    public FlightModel(String airlineCode, String flightNumber, String departureCode, String stopOverCode,
            String destinationCode, String departureTime, String arrivalTimeStopOver, String departureTimeStopOver,
            String arrivalTime, String planeCode, int duration, int durationSecondLeg, boolean groupFlight) {
        this.airlineCode = airlineCode;
        this.flightNumber = flightNumber;
        this.departureCode = departureCode;
        this.stopOverCode = stopOverCode;
        this.destinationCode = destinationCode;
        this.departureTime = departureTime;
        this.arrivalTimeStopOver = arrivalTimeStopOver;
        this.departureTimeStopOver = departureTimeStopOver;
        this.arrivalTime = arrivalTime;
        this.planeCode = planeCode;
        this.duration = duration;
        this.durationSecondLeg = durationSecondLeg;
        this.groupFlight = groupFlight;
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

    public String getDepartureCode() {
        return this.departureCode;
    }

    public void setDepartureCode(String departureCode) {
        this.departureCode = departureCode;
    }

    public String getStopOverCode() {
        return this.stopOverCode;
    }

    public void setStopOverCode(String stopOverCode) {
        this.stopOverCode = stopOverCode;
    }

    public String getDestinationCode() {
        return this.destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public String getDepartureTime() {
        return this.departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTimeStopOver() {
        return this.arrivalTimeStopOver;
    }

    public void setArrivalTimeStopOver(String arrivalTimeStopOver) {
        this.arrivalTimeStopOver = arrivalTimeStopOver;
    }

    public String getDepartureTimeStopOver() {
        return this.departureTimeStopOver;
    }

    public void setDepartureTimeStopOver(String departureTimeStopOver) {
        this.departureTimeStopOver = departureTimeStopOver;
    }

    public String getArrivalTime() {
        return this.arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getPlaneCode() {
        return this.planeCode;
    }

    public void setPlaneCode(String planeCode) {
        this.planeCode = planeCode;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDurationSecondLeg() {
        return this.durationSecondLeg;
    }

    public void setDurationSecondLeg(int durationSecondLeg) {
        this.durationSecondLeg = durationSecondLeg;
    }

    public boolean isGroupFlight() {
        return this.groupFlight;
    }

    public boolean getGroupFlight() {
        return this.groupFlight;
    }

    public void setGroupFlight(boolean groupFlight) {
        this.groupFlight = groupFlight;
    }

    @Override
    public String toString() {
        return "{" +
                " airlineCode='" + getAirlineCode() + "'" +
                ", flightNumber='" + getFlightNumber() + "'" +
                ", departureCode='" + getDepartureCode() + "'" +
                ", stopOverCode='" + getStopOverCode() + "'" +
                ", destinationCode='" + getDestinationCode() + "'" +
                ", departureTime='" + getDepartureTime() + "'" +
                ", arrivalTimeStopOver='" + getArrivalTimeStopOver() + "'" +
                ", departureTimeStopOver='" + getDepartureTimeStopOver() + "'" +
                ", arrivalTime='" + getArrivalTime() + "'" +
                ", planeCode='" + getPlaneCode() + "'" +
                ", duration='" + getDuration() + "'" +
                ", durationSecondLeg='" + getDurationSecondLeg() + "'" +
                ", groupFlight='" + isGroupFlight() + "'" +
                "}";
    }
}
