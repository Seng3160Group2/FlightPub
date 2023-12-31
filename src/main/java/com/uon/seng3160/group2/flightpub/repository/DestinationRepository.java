package com.uon.seng3160.group2.flightpub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uon.seng3160.group2.flightpub.entity.Destination;

public interface DestinationRepository extends JpaRepository<Destination, String> {
    public Optional<Destination> findByAirport(String airport);
}