--DROP SCHEMA IF EXISTS FlightPub;
CREATE SCHEMA FlightPub;
GO 

USE FlightPub;
GO

Bookings Related Data
CREATE TABLE Country (
  CountryCode2 char(2) NOT NULL,
  CountryCode3 char(3) NOT NULL,
  CountryName varchar(80) NOT NULL DEFAULT '',
  AlternateName1 varchar(255),
  AlternateName2 varchar(255),
  MotherCountryCode3 char(3),
  MotherCountryComment varchar(255),
  PRIMARY KEY (countryCode2) 
) 

CREATE TABLE Airlines (
  AirlineCode char(2) NOT NULL,
  AirlineName varchar(30) NOT NULL,
  CountryCode2 char(3) NOT NULL,
  PRIMARY KEY (AirlineCode),
  CONSTRAINT AirlinesCountryCode2_FK FOREIGN KEY (CountryCode2) REFERENCES Country (countryCode2)
)

CREATE TABLE Destinations (
  DestinationCode char(3) NOT NULL,
  Airport varchar(30) NOT NULL,
  CountryCode2 char(3) NOT NULL,
  PRIMARY KEY (DestinationCode),
  CONSTRAINT DestinationCountryCode_FK FOREIGN KEY (CountryCode2) REFERENCES Country (countryCode2)
) 

CREATE TABLE TicketClass (
  ClassCode char(3) NOT NULL,
  Details varchar(20) NOT NULL,
  PRIMARY KEY (ClassCode)
) 

CREATE TABLE TicketType (
  TicketCode char(1) NOT NULL,
  Name varchar(50) NOT NULL,
  PRIMARY KEY (TicketCode)
) 


Flight Related Data

CREATE TABLE Flights (
  AirlineCode char(2) NOT NULL,
  FlightNumber varchar(6) NOT NULL,
  DepartureCode char(3) NOT NULL,
  StopOverCode char(3) DEFAULT NULL,
  DestinationCode char(3) NOT NULL,
  DepartureTime datetime NOT NULL,
  ArrivalTimeStopOver datetime DEFAULT NULL,
  DepartureTimeStopOver datetime DEFAULT NULL,
  ArrivalTime datetime NOT NULL,
  PlaneCode varchar(20) NOT NULL,
  Duration int NOT NULL,
  DurationSecondLeg int DEFAULT NULL,
  GroupFlight bit DEFAULT 0,
  PRIMARY KEY (AirlineCode,FlightNumber,DepartureTime),
  CONSTRAINT FlightsAirlineCode_FK FOREIGN KEY (AirlineCode) REFERENCES Airlines (AirlineCode),
  CONSTRAINT FlightsDepartureCode_FK FOREIGN KEY (DepartureCode) REFERENCES Destinations (DestinationCode),
  CONSTRAINT FlightsDestinationCode_FK FOREIGN KEY (DestinationCode) REFERENCES Destinations (DestinationCode),
  CONSTRAINT FlightsStopOverCode_FK FOREIGN KEY (StopOverCode) REFERENCES Destinations (DestinationCode)
) 

CREATE TABLE Availability (
  AirlineCode char(2) NOT NULL,
  FlightNumber varchar(6) NOT NULL,
  DepartureTime datetime NOT NULL,
  ClassCode char(3) NOT NULL,
  TicketCode char(1) NOT NULL,
  NumberAvailableSeatsLeg1 int NOT NULL,
  NumberAvailableSeatsLeg2 int DEFAULT NULL,
  PRIMARY KEY (AirlineCode,FlightNumber,DepartureTime,ClassCode,TicketCode),
  CONSTRAINT AvailabilityTicketCode_FK FOREIGN KEY (TicketCode) REFERENCES TicketType (TicketCode),
  CONSTRAINT AvailabilityAirlineCode_FK FOREIGN KEY (AirlineCode) REFERENCES Airlines (AirlineCode),
  CONSTRAINT AvailabilityClassCode_FK FOREIGN KEY (ClassCode) REFERENCES TicketClass (ClassCode)
) 

CREATE TABLE Distances (
  DestinationCode1 char(3) NOT NULL,
  DestinationCode2 char(3) NOT NULL,
  DistancesInKms int NOT NULL,
  PRIMARY KEY (DestinationCode1,DestinationCode2),
  CONSTRAINT DestinationCode2_FK FOREIGN KEY (DestinationCode2) REFERENCES Destinations (DestinationCode),
  CONSTRAINT DestinationCode1_FK FOREIGN KEY (DestinationCode1) REFERENCES Destinations (DestinationCode)
) 

CREATE TABLE Price (
  AirlineCode char(2) NOT NULL,
  FlightNumber varchar(6) NOT NULL,
  ClassCode char(3) NOT NULL,
  TicketCode char(1) NOT NULL,
  StartDate datetime NOT NULL,
  EndDate datetime NOT NULL,
  Price decimal(10,2) NOT NULL,
  PriceLeg1 decimal(10,2) DEFAULT NULL,
  PriceLeg2 decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (AirlineCode,FlightNumber,ClassCode,TicketCode,StartDate),
  CONSTRAINT PriceAirlineCode_FK FOREIGN KEY (AirlineCode) REFERENCES Airlines (AirlineCode),
  CONSTRAINT PriceClassCode_FK FOREIGN KEY (ClassCode) REFERENCES TicketClass (ClassCode),
  CONSTRAINT PriceTicketCode_FK FOREIGN KEY (TicketCode) REFERENCES TicketType (TicketCode)
) 

--User related data
CREATE TABLE Users (
  AirlineCode char(2) NOT NULL,
  FlightNumber varchar(6) NOT NULL,
  ClassCode char(3) NOT NULL,
  TicketCode char(1) NOT NULL,
  StartDate datetime NOT NULL,
  EndDate datetime NOT NULL,
  Price decimal(10,2) NOT NULL,
  PriceLeg1 decimal(10,2) DEFAULT NULL,
  PriceLeg2 decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (AirlineCode,FlightNumber,ClassCode,TicketCode,StartDate),
  CONSTRAINT PriceAirlineCode_FK FOREIGN KEY (AirlineCode) REFERENCES Airlines (AirlineCode),
  CONSTRAINT PriceClassCode_FK FOREIGN KEY (ClassCode) REFERENCES TicketClass (ClassCode),
  CONSTRAINT PriceTicketCode_FK FOREIGN KEY (TicketCode) REFERENCES TicketType (TicketCode)
) 

