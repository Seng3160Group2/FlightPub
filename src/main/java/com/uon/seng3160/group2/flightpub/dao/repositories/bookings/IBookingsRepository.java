package com.uon.seng3160.group2.flightpub.dao.repositories.bookings;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uon.seng3160.group2.flightpub.dao.repositories.impl.bookings.BookingsCustomRepo;
import com.uon.seng3160.group2.flightpub.models.bookings.Bookings;


public interface IBookingsRepository extends JpaRepository<Bookings, Long>, BookingsCustomRepo{

}
