set echo on;

alter session set nls_language = english;
alter session set nls_date_format = 'DD-MON-YYYY';
alter session set nls_date_language = english;

-- ======================================================================= 
--                  BOOKINGS
-- ======================================================================= 


SELECT COUNT(*) FROM BUCHUNG;

/* DELETE ONE TUPLE */
DELETE FROM BUCHUNG WHERE BNR = 80;             
SELECT COUNT(*) FROM BUCHUNG;

/* DELETE FROM ONE PARTITIONS */
DELETE FROM BUCHUNG WHERE VON = 'CDG';
SELECT COUNT(*) FROM BUCHUNG;

/* DELETE FROM TWO PARTITIONS */
DELETE FROM BUCHUNG WHERE FLC = 'DL';
SELECT COUNT(*) FROM BUCHUNG;

/* DELETE OVER ALL PARTITONS */
DELETE FROM BUCHUNG WHERE FLC = 'DB';             
SELECT COUNT(*) FROM BUCHUNG;

/* DELETE ALL */
DELETE FROM BUCHUNG;             
SELECT COUNT(*) FROM BUCHUNG;

rollback;
  