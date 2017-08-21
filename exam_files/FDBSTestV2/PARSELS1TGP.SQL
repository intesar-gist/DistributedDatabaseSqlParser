set echo on;

alter session set nls_language = english;
alter session set nls_date_format = 'DD-MON-YYYY';
alter session set nls_date_language = english;


/* SELECT GROUP BY */
SELECT FLUG.FLC, COUNT(*) FROM FLUG GROUP BY FLUG.FLC;


/* SELECT GROUP BY WHERE */
SELECT FLUG.FLC, SUM(FLUG.FNR) FROM FLUG GROUP BY FLUG.FLC;


/* SELECT GROUP BY WHERE NOTHING */
SELECT FLUG.FLC, COUNT(*) FROM FLUG GROUP BY FLUG.FLC;


rollback;
  