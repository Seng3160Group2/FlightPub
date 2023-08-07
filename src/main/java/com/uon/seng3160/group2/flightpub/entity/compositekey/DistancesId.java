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
    @JoinColumn(name = "DestinationCode", referencedColumnName = "DestinationCode")
    private Destination destination1;

    @ManyToOne
    @JoinColumn(name = "DestinationCode", referencedColumnName = "DestinationCode")
    private Destination destination2;

    public DistancesId() {
    }

    public DistancesId(Destination destination1, Destination destination2) {
        this.destination1 = destination1;
        this.destination2 = destination2;
    }
}
