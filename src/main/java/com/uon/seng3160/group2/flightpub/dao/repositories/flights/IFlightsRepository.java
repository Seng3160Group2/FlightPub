package com.hossein.FlightBooking.dao.repositories.flights;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hossein.FlightBooking.dao.repositories.impl.flights.FlightsCustomRepo;
import com.hossein.FlightBooking.models.flights.Flights;

public interface IFlightsRepository extends JpaRepository<Flights, Long>, FlightsCustomRepo {

}
