
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