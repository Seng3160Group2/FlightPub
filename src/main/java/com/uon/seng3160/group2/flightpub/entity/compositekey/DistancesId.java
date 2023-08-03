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
    private Destination destinationCode1;

    @ManyToOne
    @JoinColumn(name = "DestinationCode", referencedColumnName = "DestinationCode")
    private Destination destinationCode2;

    public DistancesId() {
    }

    public DistancesId(Destination destinationCode1, Destination destinationCode2) {
        this.destinationCode1 = destinationCode1;
        this.destinationCode2 = destinationCode2;
    }
}
