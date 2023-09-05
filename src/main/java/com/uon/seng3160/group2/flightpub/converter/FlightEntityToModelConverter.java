package com.uon.seng3160.group2.flightpub.converter;

import java.time.LocalDateTime;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.uon.seng3160.group2.flightpub.entity.Flight;
import com.uon.seng3160.group2.flightpub.model.FlightModel;

@Component
public class FlightEntityToModelConverter implements Converter<Flight, FlightModel> {

        @Autowired
        Formatter<LocalDateTime> formatter;

        @Override
        public FlightModel convert(Flight flight) {
                String stopOverCode = (flight.getStopOver() != null) ? flight.getStopOver().getDestinationCode() : "";
                String arrivalTimeStopOver = (flight.getArrivalTimeStopOver() != null)
                                ? formatter.print(flight.getArrivalTimeStopOver(), Locale.ENGLISH)
                                : "";
                String departureTimeStopOver = (flight.getDepartureTimeStopOver() != null)
                                ? formatter.print(flight.getDepartureTimeStopOver(), Locale.ENGLISH)
                                : "";

                return new FlightModel(
                                flight.getFlightId().getAirlineCode(),
                                flight.getFlightId().getFlightNumber(),
                                flight.getDeparture().getDestinationCode(),
                                stopOverCode,
                                flight.getDestination().getDestinationCode(),
                                formatter.print(flight.getFlightId().getDepartureTime(), Locale.ENGLISH),
                                arrivalTimeStopOver,
                                departureTimeStopOver,
                                formatter.print(flight.getArrivalTime(), Locale.ENGLISH),
                                flight.getPlaneType().getPlaneCode(),
                                flight.getDuration(),
                                flight.getDurationSecondLeg(),
                                flight.getGroupFlight());
        }
}
