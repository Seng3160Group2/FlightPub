package com.uon.seng3160.group2.flightpub.models.flights;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "FLIGHTS")
public class Flights {

	@Id
	@SequenceGenerator(
				sequenceName = "seq_flights",
				name = "seq_flights",
				allocationSize = 1
			)
	@GeneratedValue(
				strategy = GenerationType.SEQUENCE,
				generator = "seq_flights"
			)
	private Long id;
	
	@Column(name = "ORIGIN_COUNTRY")
	private String originCountry;
	
	@Column(name = "ORIGIN_CITY")
	private String originCity;
	
	@Column(name = "DESTINATION_COUNTRY")
	private String destinationCountry;
	
	@Column(name = "DESTINATION_CITY")
	private String destinationCity;
	
	@Column(name = "FLIGHT_NUMBER")
	private Integer flightNumber;
	
	@Column(name = "FLIGHT_DATE")
	private String flightDate;
	
	@Column(name = "FLIGHT_TIME")
	private String flightTime;
	
	@Column(name = "PRICE")
	private Integer price;

	public Flights() {
		super();
	}

	public Flights(String originCountry, String originCity, String destinationCountry, String destinationCity,
			Integer flightNumber, String flightDate, String flightTime, Integer price) {
		super();
		this.originCountry = originCountry;
		this.originCity = originCity;
		this.destinationCountry = destinationCountry;
		this.destinationCity = destinationCity;
		this.flightNumber = flightNumber;
		this.flightDate = flightDate;
		this.flightTime = flightTime;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getOriginCity() {
		return originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public Integer getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(Integer flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}

	public String getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(String flightTime) {
		this.flightTime = flightTime;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
}
