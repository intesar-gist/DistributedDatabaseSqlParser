-- QL Queries
SELECT COUNT(*) FROM test_table;
SELECT test.abc, COUNT(*) FROM test_table GROUP BY test_table.col_name;
SELECT test.abc, asd.as, sdd.sdf, COUNT(*) FROM test_table GROUP BY test_table.col_name;
SELECT test.abc, asd.as, sdd.sdf, count(*) FROM test_table GROUP BY test_table.col_name;
SELECT test_table.abc, SUM(test_table.sd) FROM test_table GROUP BY test_table.col_name;
SELECT test_table.abc, test_table.as, test_table.sdf, SUM(test_table.sd) FROM test_table GROUP BY test_table.col_name;
SELECT test_table.abc, test_table.as, test_table.sdf, sum(test_table.sd) FROM test_table GROUP BY test_table.col_name;
SELECT PERS.ANR, COUNT(*) FROM PERS GROUP BY PERS.ANR;
SELECT PERS.ANR, SUM(test_table.SALARY) FROM PERS GROUP BY PERS.ANR;


-- DML Queries
UPDATE PERS SET BONUS = 50, BONUS_1 = 100 WHERE PNR = 23 and PNR = 'sfsd' OR PNR > 23.5;
DELETE FROM PERSON WHERE PERSON_ID = 18 OR PERSON_NAME = 'INTESAR' AND AGE >= 90;
DELETE FROM PERSON;
INSERT INTO test_Table VALUES ('', 5.55,5.234234 ,'sdf',5.59898, 'sdf', .565, 65.6, null);

-- DDL Queries
DROP TABLE testl_22;

CREATE TABLE dd_STUDENT55
(
    StudentName VARCHAR (20),
    Class VARCHAR(10),
    Rnum INTEGER(55),
    CONSTRAINT esfsd1 UNIQUE (StudentName),
) HORIZONTAL (Rnum(2,5646));

create table FLUGHAFEN (
FHC		varchar(3),
LAND		varchar(3),
STADT		varchar(50) ,
NAME		varchar(50) ,
constraint FLUGHAFEN_PS
		primary key (FHC),
);

create table FLUG (
FNR             integer,
FLC		varchar(2),
FLNR		integer,
VON		varchar(3),
NACH		varchar(3),
AB		integer,
AN		integer,
constraint FLUG_PS
		primary key (FNR),
)
HORIZONTAL (AB(0800,1200));


