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

    public Distances(Destination DestinationCode1, Destination DestinationCode2, int distancesInKms) {

        this.distancesId = new DistancesId(DestinationCode1, DestinationCode2);
        this.distancesInKms = distancesInKms;
    }

    public DistancesId getDistancesId() {
        return this.distancesId;
    }

    public void setDistancesId(DistancesId distancesId) {
        this.distancesId = distancesId;
    }

    public int getDistancesInKms() {
        return this.distancesInKms;
    }

    public void setDistancesInKms(int distancesInKms) {
        this.distancesInKms = distancesInKms;
    }
    
}