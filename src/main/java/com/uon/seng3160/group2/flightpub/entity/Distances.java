package com.uon.seng3160.group2.flightpub.entity;

import com.uon.seng3160.group2.flightpub.entity.compositekey.DistancesId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import jakarta.persistence.Table;

@Entity
@Table(name = "Distances")
public class Distances {

    @EmbeddedId
    DistancesId distancesId;

    @Column(nullable = false)
    private int distancesInKms;

    public Distances() {
    }

    public Distances(Destination destinationCode1, Destination destinationCode2, int distancesInKms) {
        this.distancesId = new DistancesId(destinationCode1, destinationCode2);
        this.distancesInKms = distancesInKms;
    }
}