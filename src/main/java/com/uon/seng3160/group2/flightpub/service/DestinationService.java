package com.uon.seng3160.group2.flightpub.service;

import java.util.List;
import java.util.Optional;

import com.uon.seng3160.group2.flightpub.entity.Destination;

public interface DestinationService {
    public Optional<Destination> getByAirport(String airport);

    public List<Destination> getAll();
}