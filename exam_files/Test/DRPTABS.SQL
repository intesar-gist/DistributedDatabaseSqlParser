set echo on;

alter session set nls_language = english;
alter session set nls_date_format = 'DD-MON-YYYY';
alter session set nls_date_language = english;

drop table BUCHUNG cascade constraints;

drop table PASSAGIER cascade constraints;

drop table FLUG cascade constraints;

drop table FLUGLINIE cascade constraints;

drop table FLUGHAFEN cascade constraints;