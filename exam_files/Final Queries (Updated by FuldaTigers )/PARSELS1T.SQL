-- set echo on;

-- alter session set nls_language = english;
-- alter session set nls_date_format = 'DD-MON-YYYY';
-- alter session set nls_date_language = english;


/* SELECT ONE TUPLE */
SELECT PASSAGIER.PNR, PASSAGIER.NAME FROM PASSAGIER WHERE PASSAGIER.PNR = 16;

/* SELECT FROM TWO PARTITONS */
SELECT PASSAGIER.PNR, PASSAGIER.NAME FROM PASSAGIER WHERE PASSAGIER.PNR = 16 OR PASSAGIER.PNR = 38;

/* SELECT FROM ALL PARTITONS */
SELECT FLUG.FLC, FLUG.FNR, FLUG.AB FROM FLUG WHERE FLUG.FLC = 'DL';

/* SELECT ALL */
SELECT FLUGLINIE.FLC, FLUGLINIE.NAME, FLUGLINIE.LAND FROM FLUGLINIE;

/* SELECT NOTHING */
SELECT * FROM FLUGLINIE WHERE FLUGLINIE.LAND = 'CZ ';

/* SELECT NOTHING AND */
SELECT * FROM FLUGLINIE WHERE (FLUGLINIE.LAND = 'D  ') AND (FLUGLINIE.ALLIANZ = 'Star');

/* SELECT NOTHING OR */
SELECT * FROM FLUGLINIE WHERE (FLUGLINIE.LAND = 'X  ') OR (FLUGLINIE.LAND = 'Y  ');

-- rollback;
  
