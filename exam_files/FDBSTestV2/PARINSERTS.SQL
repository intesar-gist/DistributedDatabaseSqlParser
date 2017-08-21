set echo on;

alter session set nls_language = english;
alter session set nls_date_format = 'DD-MON-YYYY';
alter session set nls_date_language = english;

-- ======================================================================= 
--                  BOOKINGS
-- ======================================================================= 


/* CHECK MEILEN > 0 */
INSERT INTO BUCHUNG VALUES (200, 80, 'LH', 54, 'FRA', 'CDG', '19-SEP-2012', -200, 243);              

/* CHECK PREIS NOT NULL */
INSERT INTO BUCHUNG VALUES (201, 80, 'LH', 54, 'FRA', 'CDG', '19-SEP-2012', 300, NULL);              

/* CHECK FK PNR */
INSERT INTO BUCHUNG VALUES (202, 500, 'DB', 90, 'TRU', 'FRA', '16-FEB-2011', 5000, 800);             

/* CHECK FK FLC */
INSERT INTO BUCHUNG VALUES (203, 80,  'ZZ', 90, 'TRU', 'FRA', '16-MAY-2011', 5000, 800);             

/* CHECK FK VON */
INSERT INTO BUCHUNG VALUES (204, 81, 'DB', 90, 'ZZZ', 'FRA', '12-FEB-2010', 5000, 800);             

/* CHECK FK NACH */
INSERT INTO BUCHUNG VALUES (205, 81, 'DB', 90, 'TRU', 'ZZZ', '11-FEB-2012', 5000, 800);             

/* CHECK PK BNR NOT DISTRIBUTED */
INSERT INTO BUCHUNG VALUES (184, 81, 'DB', 90, 'TRU', 'FRA', '16-FEB-2010', 5000, 800);             

/* CHECK PK BNR DISTRIBUTED CDG NOT IN SAME PARTITION AS TRU */
INSERT INTO BUCHUNG VALUES (184, 81, 'DB', 90, 'CDG', 'FRA', '16-FEB-2010', 5000, 800);             


-- ======================================================================= 
--                  FLIGHTS
-- ======================================================================= 


/* CHECK VON NOT NULL */
INSERT INTO FLUG VALUES (300, 'LH', 46, NULL, 'LHR', 2130, 2245);                                   

/* CHECK NACH NOT NULL */
INSERT INTO FLUG VALUES (301, 'LH', 47, 'LHR', NULL, 700, 820);                                     

/* CHECK AB NOT NULL */
INSERT INTO FLUG VALUES (302, 'LH', 50, 'FRA', 'CDG', NULL, 810);                                     

/* CHECK AN NOT NULL */
INSERT INTO FLUG VALUES (303, 'LH', 51, 'CDG', 'FRA', 930, NULL);                                   

/* CHECK AB RANGE */
INSERT INTO FLUG VALUES (304, 'LH', 52, 'FRA', 'CDG', 9000, 1310);                                   

/* CHECK AN RANGE */
INSERT INTO FLUG VALUES (305, 'LH', 53, 'CDG', 'FRA', 1500, 9000);                                   

/* CHECK VONNACH RANGE */
INSERT INTO FLUG VALUES (306, 'LH', 54, 'FRA', 'FRA', 1740, 1850);
                                   
/* CHECK FK FLC */
INSERT INTO FLUG VALUES (307, 'ZZ', 55, 'CDG', 'FRA', 2000, 2120);                                   

/* CHECK FK VON */
INSERT INTO FLUG VALUES (308, 'LH', 55, 'YYY', 'FRA', 2000, 2120);                                   

/* CHECK FK NACH */
INSERT INTO FLUG VALUES (309, 'LH', 55, 'CDG', 'YYY', 2000, 2120);                                   

/* CHECK PK FNR NOT DISTRIBUTED */
INSERT INTO FLUG VALUES (088, 'LH', 7, 'FRA', 'TXL', 1130, 1230);                                   

/* CHECK PK FNR DISTRIBUTED */
INSERT INTO FLUG VALUES (088, 'LH', 7, 'FRA', 'TXL', 1800, 1930);                                   

/* CHECK VONNACH DISTRIBUTED ON ALL SITES */
INSERT INTO FLUG VALUES (310, 'LH', 54, 'FRA', 'FRA', 0700, 0800);
INSERT INTO FLUG VALUES (311, 'LH', 54, 'FRA', 'FRA', 0900, 1000);
INSERT INTO FLUG VALUES (312, 'LH', 54, 'FRA', 'FRA', 1700, 1800);

/* CHECK FK FLC DISTRIBUTED ON ALL SITES */
INSERT INTO FLUG VALUES (313, 'ZZ', 54, 'FRA', 'FRA', 0700, 0800);
INSERT INTO FLUG VALUES (314, 'ZZ', 54, 'FRA', 'FRA', 0900, 1000);
INSERT INTO FLUG VALUES (315, 'ZZ', 54, 'FRA', 'FRA', 1700, 1800);
                                   

rollback;
  