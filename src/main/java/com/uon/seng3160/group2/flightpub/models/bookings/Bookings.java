package com.uon.seng3160.group2.flightpub.models.bookings;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.uon.seng3160.group2.flightpub.models.flights.Flights;
import com.uon.seng3160.group2.flightpub.models.infrastructure.UserModel;


@Entity
@Table(
		name = "BOOKINGS",
		uniqueConstraints = @UniqueConstraint(columnNames= {"USER_ID", "FLIGHT_ID"})
		)
public class Bookings {

	@Id
	@SequenceGenerator(
				sequenceName = "seq_bookings",
				name = "seq_bookings",
				allocationSize = 1
			)
	@GeneratedValue(
				strategy = GenerationType.SEQUENCE,
				generator = "seq_bookings"
			)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private UserModel user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FLIGHT_ID")
	private Flights flight;
	
	private Integer persons;

	@Transient
	private Integer totalPrice;
	
	public Integer getTotalPrice() {
		return ((this.getPersons() + 1) * this.flight.getPrice());
	}
	
	public Bookings() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public Flights getFlight() {
		return flight;
	}

	public void setFlight(Flights flight) {
		this.flight = flight;
	}

	public Integer getPersons() {
		return persons;
	}

	public void setPersons(Integer persons) {
		this.persons = persons;
	}
	
}
