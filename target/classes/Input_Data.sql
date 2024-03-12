CREATE VIEW LoginData AS SELECT login,password,brand FROM ACCOUNT WHERE confirmed=1 AND active=1;

DELETE FROM USERB.DATAADMINISTRATOR;
DELETE FROM USERB.DATAUSER;
DELETE FROM USERB.DATAPERSONAL;
DELETE FROM USERB.ACCOUNT;
DELETE FROM USERB.EQUIPMENT;
DELETE FROM USERB.EQUIPMENTCOMPONENTS;
DELETE FROM USERB.LAB;
DELETE FROM USERB.RESERVATION;
DELETE FROM USERB.BOOKINGLAB;

--  hasło to user1p
INSERT INTO USERB.ACCOUNT (ID, BRAND, ACTIVE, PASSWORD, LOGIN, CONFIRMED, VERSION) VALUES (1, 'USER', 1, '48fbdc1c1132039e15d43973573275d3', 'user1', 1, 1);
INSERT INTO USERB.DATAPERSONAL (ID, EMAIL, "NAME", SURNAME, PHONE) VALUES (1, 'user1@o.com', 'Stefan', 'Kowalski', '111222333');
INSERT INTO USERB.DATAUSER (ID, NIP) VALUES (1, '1234567890');

-- hasło to user2p
INSERT INTO USERB.ACCOUNT (ID, BRAND, ACTIVE, PASSWORD, LOGIN, CONFIRMED, VERSION) VALUES (2, 'USER', 1, '589318a13e7e601ee7d2256adbb086f0', 'user2', 1, 1);
INSERT INTO USERB.DATAPERSONAL (ID, EMAIL, "NAME", SURNAME, PHONE) VALUES (2, 'user2@o.com', 'Arek', 'Gacek', '333222111');
INSERT INTO USERB.DATAUSER (ID, NIP) VALUES (2, '1234567891');

-- hasło to admin1p
INSERT INTO USERB.ACCOUNT (ID, BRAND, ACTIVE, PASSWORD, LOGIN, CONFIRMED, VERSION) VALUES (3, 'ADMIN', 1, '4c46d66d774f8bcb8f4872213e9215f8', 'admin1', 1, 1);
INSERT INTO USERB.DATAPERSONAL (ID, EMAIL, "NAME", SURNAME, PHONE) VALUES (3, 'admin1@o.com', 'Piotr', 'Nowak', '444555666');
INSERT INTO USERB.DATAADMINISTRATOR (ID, ALARMNUMBER) VALUES (3, '444555666');


INSERT INTO USERB.LAB (ID, FLOOR, "NUMBER", VERSION) VALUES (1, '1', '11', 1 );
INSERT INTO USERB.LAB (ID, FLOOR, "NUMBER", VERSION) VALUES (2, '2', '21', 1 );
INSERT INTO USERB.LAB (ID, FLOOR, "NUMBER", VERSION) VALUES (3, '3', '31', 1 );
INSERT INTO USERB.LAB (ID, FLOOR, "NUMBER", VERSION) VALUES (4, '4', '41', 1 );
INSERT INTO USERB.LAB (ID, FLOOR, "NUMBER", VERSION) VALUES (5, '4', '42', 1 );



INSERT INTO USERB.EQUIPMENT (ID, STATIONNUMBER, STATIONTYPE, CONDITIONEQUIPMENT, VERSION, LAB_ID) VALUES (1, '1', 'Komputer stacjonarny', 1, 1, 1);
INSERT INTO USERB.EQUIPMENT (ID, STATIONNUMBER, STATIONTYPE, CONDITIONEQUIPMENT, VERSION, LAB_ID) VALUES (2, '2', 'Komputer stacjonarny', 1, 1, 1 );
INSERT INTO USERB.EQUIPMENT (ID, STATIONNUMBER, STATIONTYPE, CONDITIONEQUIPMENT, VERSION, LAB_ID) VALUES (3, '3', 'Komputer niestacjonarny', 1, 1, 2);
INSERT INTO USERB.EQUIPMENT (ID, STATIONNUMBER, STATIONTYPE, CONDITIONEQUIPMENT, VERSION, LAB_ID) VALUES (4, '4', 'Komputer niestacjonarny', 1, 1, 2);
INSERT INTO USERB.EQUIPMENT (ID, STATIONNUMBER, STATIONTYPE, CONDITIONEQUIPMENT, VERSION, LAB_ID) VALUES (5, '5', 'Komputer niestacjonarny', 1, 1, 3);
INSERT INTO USERB.EQUIPMENT (ID, STATIONNUMBER, STATIONTYPE, CONDITIONEQUIPMENT, VERSION, LAB_ID) VALUES (6, '6', 'Komputer niestacjonarny', 1, 1, 3);
INSERT INTO USERB.EQUIPMENT (ID, STATIONNUMBER, STATIONTYPE, CONDITIONEQUIPMENT, VERSION, LAB_ID) VALUES (7, '7', 'Komputer niestacjonarny', 1, 1, 4);
INSERT INTO USERB.EQUIPMENT (ID, STATIONNUMBER, STATIONTYPE, CONDITIONEQUIPMENT, VERSION, LAB_ID) VALUES (8, '8', 'Komputer niestacjonarny', 1, 1, 4);
INSERT INTO USERB.EQUIPMENT (ID, STATIONNUMBER, STATIONTYPE, CONDITIONEQUIPMENT, VERSION, LAB_ID) VALUES (9, '9', 'Komputer niestacjonarny', 1, 1, 5);
INSERT INTO USERB.EQUIPMENT (ID, STATIONNUMBER, STATIONTYPE, CONDITIONEQUIPMENT, VERSION, LAB_ID) VALUES (10, '10', 'Komputer niestacjonarny', 1, 1, 5);

INSERT INTO USERB.EQUIPMENTCOMPONENTS (ID, TYPECOMPONENT, NAMECOMPONENT, VERSION) VALUES (1, 'Procesor', 'Intel Pentium J2900', 1);
INSERT INTO USERB.EQUIPMENTCOMPONENTS (ID, TYPECOMPONENT, NAMECOMPONENT, VERSION) VALUES (2, 'Pamiec RAM', '8 GB', 1);
INSERT INTO USERB.EQUIPMENTCOMPONENTS (ID, TYPECOMPONENT, NAMECOMPONENT, VERSION) VALUES (3, 'Karta graficzna', 'NVIDIA GeForce GT 730', 1);
INSERT INTO USERB.EQUIPMENTCOMPONENTS (ID, TYPECOMPONENT, NAMECOMPONENT, VERSION) VALUES (4, 'Karta dzwiekowa', 'SonicMaster', 1);

INSERT INTO USERB.EQUIPMENTCOMPONENTS (ID, TYPECOMPONENT, NAMECOMPONENT, VERSION) VALUES (5, 'Procesor', 'Intel Celeron G1840', 1);
INSERT INTO USERB.EQUIPMENTCOMPONENTS (ID, TYPECOMPONENT, NAMECOMPONENT, VERSION) VALUES (6, 'Pamiec RAM', '16 GB', 1);
INSERT INTO USERB.EQUIPMENTCOMPONENTS (ID, TYPECOMPONENT, NAMECOMPONENT, VERSION) VALUES (7, 'Karta graficzna', 'Zintegrowana', 1);
INSERT INTO USERB.EQUIPMENTCOMPONENTS (ID, TYPECOMPONENT, NAMECOMPONENT, VERSION) VALUES (8, 'Karta dzwiekowa', 'High Definition Audio 7.1', 1);


INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (1,1);
INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (1,2);
INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (1,3);
INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (1,4);

INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (2,5);
INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (2,6);
INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (2,7);
INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (2,8);

INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (3,1);
INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (3,2);
INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (3,3);
INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (3,4);

INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (4,5);
INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (4,6);
INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (4,7);
INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (4,8);

INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (5,5);
INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (5,6);
INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (5,7);
INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (5,8);

INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (6,5);
INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (6,6);
INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (6,7);
INSERT INTO USERB.EQUIPMENTS_EQUIPMENTCOMPONENTS (EQUIPMENTS_ID, EQUIPMENTCOMPONENTS_ID) VALUES (6,8);


INSERT INTO USERB.RESERVATION (ID, CONFIRMED, LASTMODIFICATION, STARTRESERVATION, STOPRESERVATION, VERSION, EQUIPMENT_ID, WHOMADERESERVATION_ID) VALUES (1, 1, '2016-09-20 08:00:00.000','2016-09-21 10:00:00.000', '2016-09-21 16:00:00.000', 0, 1, 1 );
INSERT INTO USERB.RESERVATION (ID, CONFIRMED, LASTMODIFICATION, STARTRESERVATION, STOPRESERVATION, VERSION, EQUIPMENT_ID, WHOMADERESERVATION_ID) VALUES (2, 1, '2016-09-20 08:00:00.000','2016-09-22 10:00:00.000', '2016-09-22 16:00:00.000', 0, 1, 2 );

INSERT INTO USERB.RESERVATION (ID, CONFIRMED, LASTMODIFICATION, STARTRESERVATION, STOPRESERVATION, VERSION, EQUIPMENT_ID, WHOMADERESERVATION_ID) VALUES (3, 1, '2016-09-20 08:00:00.000','2016-09-24 10:00:00.000', '2016-09-24 16:00:00.000', 0, 2, 1 );
INSERT INTO USERB.RESERVATION (ID, CONFIRMED, LASTMODIFICATION, STARTRESERVATION, STOPRESERVATION, VERSION, EQUIPMENT_ID, WHOMADERESERVATION_ID) VALUES (4, 1, '2016-09-20 08:00:00.000','2016-09-20 10:00:00.000', '2016-09-20 16:00:00.000', 0, 2, 2 );

INSERT INTO USERB.RESERVATION (ID, CONFIRMED, LASTMODIFICATION, STARTRESERVATION, STOPRESERVATION, VERSION, EQUIPMENT_ID, WHOMADERESERVATION_ID) VALUES (5, 1, '2016-09-20 08:00:00.000','2016-09-22 10:00:00.000', '2016-09-22 16:00:00.000', 0, 3, 1 );
INSERT INTO USERB.RESERVATION (ID, CONFIRMED, LASTMODIFICATION, STARTRESERVATION, STOPRESERVATION, VERSION, EQUIPMENT_ID, WHOMADERESERVATION_ID) VALUES (6, 1, '2016-09-20 08:00:00.000','2016-09-20 10:00:00.000', '2016-09-20 16:00:00.000', 0, 3, 2 );

INSERT INTO USERB.RESERVATION (ID, CONFIRMED, LASTMODIFICATION, STARTRESERVATION, STOPRESERVATION, VERSION, EQUIPMENT_ID, WHOMADERESERVATION_ID) VALUES (7, 1, '2016-09-20 08:00:00.000','2016-09-20 10:00:00.000', '2016-09-20 16:00:00.000', 0, 4, 1 );
INSERT INTO USERB.RESERVATION (ID, CONFIRMED, LASTMODIFICATION, STARTRESERVATION, STOPRESERVATION, VERSION, EQUIPMENT_ID, WHOMADERESERVATION_ID) VALUES (8, 1, '2016-09-20 08:00:00.000','2016-09-24 10:00:00.000', '2016-09-24 16:00:00.000', 0, 4, 2 );
INSERT INTO USERB.RESERVATION (ID, CONFIRMED, LASTMODIFICATION, STARTRESERVATION, STOPRESERVATION, VERSION, EQUIPMENT_ID, WHOMADERESERVATION_ID) VALUES (9, 1, '2016-09-20 08:00:00.000','2016-09-20 10:00:00.000', '2016-09-20 16:00:00.000', 0, 5, 2 );


INSERT INTO USERB.BOOKINGLAB (ID, CONFIRMED, LASTMODIFICATION, STARTBOOKINGLAB, STOPBOOKINGLAB, VERSION, LAB_ID, WHOMADE_ID, DESCRIPTION) VALUES (1, 1, '2016-09-20 08:00:00.000','2016-09-25 08:15:00.000', '2016-09-25 10:00:00.000', 0, 1, 3, 'Wstęp do programowania');
INSERT INTO USERB.BOOKINGLAB (ID, CONFIRMED, LASTMODIFICATION, STARTBOOKINGLAB, STOPBOOKINGLAB, VERSION, LAB_ID, WHOMADE_ID, DESCRIPTION) VALUES (2, 1, '2016-09-20 10:00:00.000','2016-09-26 10:15:00.000', '2016-09-26 12:00:00.000', 0, 1, 3, 'Wstęp do programowania');
INSERT INTO USERB.BOOKINGLAB (ID, CONFIRMED, LASTMODIFICATION, STARTBOOKINGLAB, STOPBOOKINGLAB, VERSION, LAB_ID, WHOMADE_ID, DESCRIPTION) VALUES (3, 1, '2016-09-20 10:00:00.000','2016-09-26 12:15:00.000', '2016-09-26 14:00:00.000', 0, 1, 3, 'Programowanie1 w C++');
INSERT INTO USERB.BOOKINGLAB (ID, CONFIRMED, LASTMODIFICATION, STARTBOOKINGLAB, STOPBOOKINGLAB, VERSION, LAB_ID, WHOMADE_ID, DESCRIPTION) VALUES (4, 1, '2016-09-20 10:00:00.000','2016-09-26 14:00:00.000', '2016-09-26 16:00:00.000', 0, 1, 3, 'Programowanie1 w C++');

INSERT INTO USERB.BOOKINGLAB (ID, CONFIRMED, LASTMODIFICATION, STARTBOOKINGLAB, STOPBOOKINGLAB, VERSION, LAB_ID, WHOMADE_ID, DESCRIPTION) VALUES (5, 1, '2016-09-20 08:00:00.000','2016-09-25 08:15:00.000', '2016-09-25 10:00:00.000', 0, 2, 3, 'Wstęp do programowania');
INSERT INTO USERB.BOOKINGLAB (ID, CONFIRMED, LASTMODIFICATION, STARTBOOKINGLAB, STOPBOOKINGLAB, VERSION, LAB_ID, WHOMADE_ID, DESCRIPTION) VALUES (6, 1, '2016-09-20 10:00:00.000','2016-09-26 10:15:00.000', '2016-09-26 12:00:00.000', 0, 2, 3, 'Wstęp do programowania');
INSERT INTO USERB.BOOKINGLAB (ID, CONFIRMED, LASTMODIFICATION, STARTBOOKINGLAB, STOPBOOKINGLAB, VERSION, LAB_ID, WHOMADE_ID, DESCRIPTION) VALUES (7, 1, '2016-09-20 10:00:00.000','2016-09-26 12:15:00.000', '2016-09-26 14:00:00.000', 0, 2, 3, 'Programowanie2 w C++');
INSERT INTO USERB.BOOKINGLAB (ID, CONFIRMED, LASTMODIFICATION, STARTBOOKINGLAB, STOPBOOKINGLAB, VERSION, LAB_ID, WHOMADE_ID, DESCRIPTION) VALUES (8, 1, '2016-09-20 10:00:00.000','2016-09-26 14:00:00.000', '2016-09-26 16:00:00.000', 0, 2, 3, 'Programowanie2 w C++');

INSERT INTO USERB.BOOKINGLAB (ID, CONFIRMED, LASTMODIFICATION, STARTBOOKINGLAB, STOPBOOKINGLAB, VERSION, LAB_ID, WHOMADE_ID, DESCRIPTION) VALUES (9, 1, '2016-09-20 08:00:00.000','2016-09-25 08:15:00.000', '2016-09-25 10:00:00.000', 0, 3, 3, 'Wstęp do programowania');
INSERT INTO USERB.BOOKINGLAB (ID, CONFIRMED, LASTMODIFICATION, STARTBOOKINGLAB, STOPBOOKINGLAB, VERSION, LAB_ID, WHOMADE_ID, DESCRIPTION) VALUES (10, 1, '2016-09-20 10:00:00.000','2016-09-26 10:15:00.000', '2016-09-26 12:00:00.000', 0, 3, 3, 'Wstęp do programowania');
INSERT INTO USERB.BOOKINGLAB (ID, CONFIRMED, LASTMODIFICATION, STARTBOOKINGLAB, STOPBOOKINGLAB, VERSION, LAB_ID, WHOMADE_ID, DESCRIPTION) VALUES (11, 1, '2016-09-20 10:00:00.000','2016-09-26 12:15:00.000', '2016-09-26 14:00:00.000', 0, 3, 3, 'Programowanie3 w C++');
INSERT INTO USERB.BOOKINGLAB (ID, CONFIRMED, LASTMODIFICATION, STARTBOOKINGLAB, STOPBOOKINGLAB, VERSION, LAB_ID, WHOMADE_ID, DESCRIPTION) VALUES (12, 1, '2016-09-20 10:00:00.000','2016-09-26 14:00:00.000', '2016-09-26 16:00:00.000', 0, 3, 3, 'Programowanie3 w C++');

