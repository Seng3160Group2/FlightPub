package com.uon.seng3160.group2.flightpub.model;

import java.util.Date;

public class BookingModel {
    private Long id; // Unique identifier for the booking

    private UserModel user; // Reference to the user who made the booking

    private FlightModel flight; // Reference to the booked flight

    private Date bookingDate; // Date and time when the booking was made

    private int numberOfPassengers; // Number of passengers included in the booking

    private double totalPrice; // Total price of the booking

}
