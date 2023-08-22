package com.hossein.FlightBooking.dao.repositories.bookings;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hossein.FlightBooking.dao.repositories.impl.bookings.BookingsCustomRepo;
import com.hossein.FlightBooking.models.bookings.Bookings;

public interface IBookingsRepository extends JpaRepository<Bookings, Long>, BookingsCustomRepo{

}
