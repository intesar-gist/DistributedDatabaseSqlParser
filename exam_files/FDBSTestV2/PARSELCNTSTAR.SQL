set echo on;

alter session set nls_language = english;
alter session set nls_date_format = 'DD-MON-YYYY';
alter session set nls_date_language = english;

/* SELECT COUNT ROWS IN ALL TABLES */
SELECT COUNT(*) FROM FLUGLINIE;
SELECT COUNT(*) FROM FLUGHAFEN;
SELECT COUNT(*) FROM FLUG;
SELECT COUNT(*) FROM PASSAGIER;
SELECT COUNT(*) FROM BUCHUNG;
