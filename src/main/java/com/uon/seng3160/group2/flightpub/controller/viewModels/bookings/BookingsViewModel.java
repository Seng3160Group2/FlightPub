package com.hossein.FlightBooking.controllers.viewModels.bookings;

public class BookingsViewModel {

	private Long id;
	private Long userId;
	private Long flightId;
	private Integer persons;
	private String flightOriginCountry;
	private String flightOriginCity;
	private String flightDestinationCountry;
	private String flightDestinationCity;
	private Integer flightNumber;
	private String flightDate;
	private String flightTime;
	private Integer totalPrice;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getFlightId() {
		return flightId;
	}
	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}
	public Integer getPersons() {
		return persons;
	}
	public void setPersons(Integer persons) {
		this.persons = persons;
	}
	public String getFlightOriginCountry() {
		return flightOriginCountry;
	}
	public void setFlightOriginCountry(String flightOriginCountry) {
		this.flightOriginCountry = flightOriginCountry;
	}
	public String getFlightOriginCity() {
		return flightOriginCity;
	}
	public void setFlightOriginCity(String flightOriginCity) {
		this.flightOriginCity = flightOriginCity;
	}
	public String getFlightDestinationCountry() {
		return flightDestinationCountry;
	}
	public void setFlightDestinationCountry(String flightDestinationCountry) {
		this.flightDestinationCountry = flightDestinationCountry;
	}
	public String getFlightDestinationCity() {
		return flightDestinationCity;
	}
	public void setFlightDestinationCity(String flightDestinationCity) {
		this.flightDestinationCity = flightDestinationCity;
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
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
