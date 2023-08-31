package com.uon.seng3160.group2.flightpub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uon.seng3160.group2.flightpub.entity.Availability;
import com.uon.seng3160.group2.flightpub.entity.compositekey.AvailabilityId;


public interface AvailabilityRepository extends JpaRepository<Availability, AvailabilityId> {

}