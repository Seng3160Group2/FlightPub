package com.uon.seng3160.group2.flightpub.entity.compositekey;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Embeddable
public class DistanceId implements Serializable {

    private String destinationCodeFrom;
    private String destinationCodeTo;

    public DistanceId() {
    }

    public DistanceId(String destinationCodeFrom, String destinationCodeTo) {
        this.destinationCodeFrom = destinationCodeFrom;
        this.destinationCodeTo = destinationCodeTo;
    }

    public String getDestinationCodeFrom() {
        return this.destinationCodeFrom;
    }

    public void setDestinationCodeFrom(String destinationCodeFrom) {
        this.destinationCodeFrom = destinationCodeFrom;
    }

    public String getDestinationCodeTo() {
        return this.destinationCodeTo;
    }

    public void setDestinationCodeTo(String destinationCodeTo) {
        this.destinationCodeTo = destinationCodeTo;
    }
}
