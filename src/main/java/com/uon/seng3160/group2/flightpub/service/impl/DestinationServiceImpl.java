package com.uon.seng3160.group2.flightpub.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uon.seng3160.group2.flightpub.entity.Destination;
import com.uon.seng3160.group2.flightpub.repository.DestinationRepository;
import com.uon.seng3160.group2.flightpub.service.DestinationService;

@Service
@Transactional
public class DestinationServiceImpl implements DestinationService {

    @Autowired
    public final DestinationRepository destinationRepository;

    public DestinationServiceImpl(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public Optional<Destination> getByAirport(String airport) {
        return this.destinationRepository.findByAirport(airport);
    }

    public List<Destination> getAll() {
        return this.destinationRepository.findAll();
    }
}
