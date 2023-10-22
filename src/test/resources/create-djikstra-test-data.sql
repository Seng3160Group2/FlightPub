DELETE FROM `Distances`;
DELETE FROM `Airlines`;
DELETE FROM `Planetype`;
DELETE FROM `Destinations`;
DELETE FROM `Country`;

INSERT INTO `Country` (`CountryCode2`, `CountryCode3`, `CountryName`, `AlternateName1`, `AlternateName2`, `MotherCountryCode3`, `MotherCountryComment`)
VALUES
('AU','AUS','Australia','Commonwealth of Australia','','','');

INSERT INTO `Destinations` (`DestinationCode`, `Airport`, `CountryCode3`)
VALUES
	('ADL','Adelaide','AUS'),
    ('BNE','Brisbane','AUS'),
	('CBR','Canberra','AUS'),
    ('CNS','Cairns','AUS'),
	('DRW','Darwin','AUS'),
    ('HBA','Hobart','AUS'),
    ('MEL','Melbourne','AUS'),
    ('SYD','Sydney','AUS'),
    ('OOL','Gold Coast','AUS'),
    ('PER','Perth','AUS');

INSERT INTO `Distances` (`DestinationCode1`, `DestinationCode2`, `DistancesInKms`)
VALUES
	('ADL','CBR',957),
	('ADL','DRW',2616),
	('ADL','PER',2129),
    ('BNE','CBR',939),
    ('BNE','DRW',2847),
    ('BNE','ADL',1597),
    ('BNE','PER',3601),
    ('CBR','DRW',3131),
	('CBR','OOL',892),
    ('MEL','ADL',651),
    ('MEL','BNE',1367),
    ('MEL','CBR',464),
    ('MEL','DRW',3143),
    ('MEL','PER',2719),
    ('PER','CBR',3086),
    ('PER','DRW',2649),
    ('SYD','ADL',1167),
    ('SYD','BNE',728),
    ('SYD','CBR',246),
    ('SYD','DRW',3146),
    ('SYD','MEL',713),
    ('SYD','OOL',679),
    ('SYD','PER',3286);

INSERT INTO `Airlines` (`AirlineCode`, `AirlineName`, `CountryCode3`)
VALUES
('QF','Qantas','AUS'),
('JQ','Jetstar Airlines','AUS');

INSERT INTO `PlaneType` (`PlaneCode`, `Details`, `NumFirstClass`, `NumBusiness`, `NumPremiumEconomy`, `Economy`)
VALUES
('747-100','Boeing 747-100',55,58,100,210),
('A380','Airbus A380',46,47,111,203);

