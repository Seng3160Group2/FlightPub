package com.uon.seng3160.group2.flightpub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uon.seng3160.group2.flightpub.entity.Destination;
import com.uon.seng3160.group2.flightpub.entity.Distance;
import com.uon.seng3160.group2.flightpub.entity.compositekey.DistanceId;

public interface DistanceRepository extends JpaRepository<Distance, DistanceId> {
}