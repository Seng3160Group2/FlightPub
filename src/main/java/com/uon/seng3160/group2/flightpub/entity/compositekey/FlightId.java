package com.uon.seng3160.group2.flightpub.entity.compositekey;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.uon.seng3160.group2.flightpub.entity.Airline;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Embeddable
public class FlightId implements Serializable {

    // @Column(columnDefinition = "CHAR(2)")
    // private String airlineCode;

    @ManyToOne
    @JoinColumn(name = "AirlineCode", referencedColumnName = "AirlineCode")
    private Airline airline;

    @Column(columnDefinition = "VARCHAR(6)")
    private String flightNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime departureTime;

    public FlightId() {
    }

    public FlightId(Airline airline, String flightNumber, LocalDateTime departureTime) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
    }
    // what lombok overrides

    // @Override
    // public boolean equals(Object o) {
    // if (o == this)
    // return true;
    // if (!(o instanceof FlightId))
    // return false;
    // FlightId other = (FlightId) o;
    // boolean airlineCodeEquals = (this.airlineCode == null && other.airlineCode ==
    // null)
    // || (this.airlineCode != null && this.airlineCode.equals(other.airlineCode));
    // boolean flightNumberEquals = (this.flightNumber == null && other.flightNumber
    // == null)
    // || (this.flightNumber != null &&
    // this.flightNumber.equals(other.flightNumber));
    // boolean departureTimeEquals = (this.departureTime == null &&
    // other.departureTime == null)
    // || (this.departureTime != null &&
    // this.departureTime.equals(other.departureTime));

    // return airlineCodeEquals && flightNumberEquals && departureTimeEquals;
    // }

    // @Override
    // public int hashCode() {
    // final int prime = 31;
    // int result = 1;
    // result = prime * result + ((this.airlineCode == null) ? 0 :
    // this.airlineCode.hashCode());
    // result = prime * result + ((this.flightNumber == null) ? 0 :
    // this.flightNumber.hashCode());
    // result = prime * result + ((this.departureTime == null) ? 0 :
    // this.departureTime.hashCode());
    // return result;
    // }
}
