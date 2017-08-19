set echo on;

alter session set nls_language = english;
alter session set nls_date_format = 'DD-MON-YYYY';
alter session set nls_date_language = english;

-- ======================================================================= 
--                  BOOKINGS
-- ======================================================================= 

SELECT COUNT(*) FROM BUCHUNG;

/* UPDATE ONE TUPLE */
UPDATE BUCHUNG SET TAG = '01-JAN-2014' WHERE BNR = 80;             
SELECT COUNT(*) FROM BUCHUNG;

/* UPDATE IN ONE PARTITION */
UPDATE BUCHUNG SET TAG = '01-FEB-2015' WHERE VON = 'FRA';             
SELECT COUNT(*) FROM BUCHUNG;

/* UPDATE ALL */
UPDATE BUCHUNG SET MEILEN = 1000;             
SELECT COUNT(*) FROM BUCHUNG;

SELECT COUNT(*) FROM FLUG;

/* UPDATE CHANGE OF PARTITION 1500 --> 0700 */
UPDATE FLUG SET AB = 0700 WHERE FNR = 52;                                   
SELECT COUNT(*) FROM FLUG;

/* UPDATE CHANGE OF PARTITION 1310 --> 1120 */
UPDATE FLUG SET AB = 1120 WHERE FNR = 3;
SELECT COUNT(*) FROM FLUG;

rollback;
  