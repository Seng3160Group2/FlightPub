package com.uon.seng3160.group2.flightpub.mapper;

import com.uon.seng3160.group2.flightpub.entity.Airline;
import com.uon.seng3160.group2.flightpub.entity.Flight;
import com.uon.seng3160.group2.flightpub.entity.compositekey.FlightId;
import com.uon.seng3160.group2.flightpub.model.FlightModel;

public class FlightMapper {
    public static FlightModel mapToModel(Flight flight) {
        return new FlightModel(
                flight.getFlightId().getAirlineCode(),
                flight.getFlightId().getFlightNumber(),
                flight.getDeparture().getDestinationCode(),
                flight.getStopOver().getDestinationCode(),
                flight.getDestination().getDestinationCode(),
                flight.getFlightId().getDepartureTime(),
                flight.getArrivalTimeStopOver(),
                flight.getDepartureTimeStopOver(),
                flight.getArrivalTime(),
                flight.getPlaneType().getPlaneCode(),
                flight.getDuration(),
                flight.getDurationSecondLeg(),
                flight.getGroupFlight());
    }

    public static FlightId mapToId(FlightModel flightModel) {
        return new FlightId(
                flightModel.getAirlineCode(),
                flightModel.getFlightNumber(),
                flightModel.getDepartureTime());
    }
}
