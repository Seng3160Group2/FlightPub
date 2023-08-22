package com.hossein.FlightBooking.dto.flights;

public class FlightsDto {
	
	private Long id;
	private String oCountry;
	private String oCity;
	private String dCountry;
	private String dCity;
	private Integer flightNum;
	private String flightDate;
	private String flightTime;
	private Integer price;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getoCountry() {
		return oCountry;
	}
	public void setoCountry(String oCountry) {
		this.oCountry = oCountry;
	}
	public String getoCity() {
		return oCity;
	}
	public void setoCity(String oCity) {
		this.oCity = oCity;
	}
	public String getdCountry() {
		return dCountry;
	}
	public void setdCountry(String dCountry) {
		this.dCountry = dCountry;
	}
	public String getdCity() {
		return dCity;
	}
	public void setdCity(String dCity) {
		this.dCity = dCity;
	}
	public Integer getFlightNum() {
		return flightNum;
	}
	public void setFlightNum(Integer flightNum) {
		this.flightNum = flightNum;
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
