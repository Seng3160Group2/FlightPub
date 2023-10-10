package com.uon.seng3160.group2.flightpub.entity;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class TripTest {

    // if a trip contains the same departure and arrival destinations as another
    // trip, theyre equal
    @Test
    public void testTripEquals() throws Exception {
        Country country = new Country("AU", "AUS", "Australia");
        Destination departure = new Destination("Sydney", "SYD", country);
        Destination arrival = new Destination("Melbourne", "MEL", country);
        Trip trip = new Trip(departure, arrival);
        Trip tripEqual = new Trip(departure, arrival);

        assertTrue(trip.equals(tripEqual));
    }
}
