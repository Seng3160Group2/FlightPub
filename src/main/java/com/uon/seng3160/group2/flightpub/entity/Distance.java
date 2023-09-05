package com.uon.seng3160.group2.flightpub.entity;

import com.uon.seng3160.group2.flightpub.entity.compositekey.DistanceId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "Distances")
public class Distance {

    @EmbeddedId
    DistanceId distanceId;

    @MapsId("destinationCodeFrom")
    @ManyToOne
    @JoinColumn(name = "DestinationCode1", referencedColumnName = "DestinationCode", columnDefinition = "CHAR(3)")
    private Destination destinationFrom;

    @MapsId("destinationCodeTo")
    @ManyToOne
    @JoinColumn(name = "DestinationCode2", referencedColumnName = "DestinationCode", columnDefinition = "CHAR(3)")
    private Destination destinationTo;

    @Column(nullable = false)
    private int distancesInKms;

    public Distance() {
    }

    public Distance(DistanceId distanceId, Destination destinationFrom, Destination destinationTo, int distancesInKms) {
        this.distanceId = distanceId;
        this.destinationFrom = destinationFrom;
        this.destinationTo = destinationTo;
        this.distancesInKms = distancesInKms;
    }

    public DistanceId getDistanceId() {
        return this.distanceId;
    }

    public void setDistanceId(DistanceId distanceId) {
        this.distanceId = distanceId;
    }

    public Destination getDestinationFrom() {
        return this.destinationFrom;
    }

    public void setDestinationFrom(Destination destinationFrom) {
        this.destinationFrom = destinationFrom;
    }

    public Destination getDestinationTo() {
        return this.destinationTo;
    }

    public void setDestinationTo(Destination destinationTo) {
        this.destinationTo = destinationTo;
    }

    public int getDistancesInKms() {
        return this.distancesInKms;
    }

    public void setDistancesInKms(int distancesInKms) {
        this.distancesInKms = distancesInKms;
    }

    /*
     * A destination is mapped to another only one way so when looking at a
     * destinations distances to another destination, sometimes some of those are
     * in the distanesTo list and the distancesFrom list. Thus we must look over
     * both
     * distance arrays to find all the destination a destination is connected to.
     * But in the distancesTo
     * array, the destinations will be switched, thus requiring the method below.
     */
    public Destination getOtherDestination(Destination departure) {
        if (this.destinationTo != departure) // may need to check if reference equality is the way to go
            return this.destinationTo;
        return this.destinationFrom;
    }

    @Override
    public String toString() {
        return "{" +
                " distanceId='" + getDistanceId() + "'" +
                ", destinationFrom='" + getDestinationFrom().getDestinationCode() + "'" +
                ", destinationTo='" + getDestinationTo().getDestinationCode() + "'" +
                ", distancesInKms='" + getDistancesInKms() + "'" +
                "}";
    }

}