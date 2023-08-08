package com.uon.seng3160.group2.flightpub.entity.compositekey;

import java.io.Serializable;

import com.uon.seng3160.group2.flightpub.entity.Destination;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Embeddable
public class DistancesId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "DestinationCode1", referencedColumnName = "DestinationCode", nullable = false)
    private Destination destination1;

    @ManyToOne
    @JoinColumn(name = "DestinationCode2", referencedColumnName = "DestinationCode", nullable = false)
    private Destination destination2;

    public DistancesId() {
    }

    public DistancesId(Destination destination1, Destination destination2) {
        this.destination1 = destination1;
        this.destination2 = destination2;
    }

    public Destination getDestination1() {
        return this.destination1;
    }

    public void setDestination1(Destination destination1) {
        this.destination1 = destination1;
    }

    public Destination getDestination2() {
        return this.destination2;
    }

    public void setDestination2(Destination destination2) {
        this.destination2 = destination2;
    }

}
