package com.uon.seng3160.group2.flightpub.dao.repositories.flights;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uon.seng3160.group2.flightpub.dao.repositories.impl.flights.FlightsCustomRepo;
import com.uon.seng3160.group2.flightpub.models.flights.Flights;


public interface IFlightsRepository extends JpaRepository<Flights, Long>, FlightsCustomRepo {

}
