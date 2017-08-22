-- QL Queries
SELECT COUNT(*) FROM test_table;
SELECT test.abc, COUNT(*) FROM test_table GROUP BY test_table.col_name;
SELECT test.abc, count(*) FROM test_table GROUP BY test_table.col_name;
SELECT test_table.abc, SUM(test_table.sd) FROM test_table GROUP BY test_table.col_name;
SELECT test_table.abc, sum(test_table.sd) FROM test_table GROUP BY test_table.col_name;
SELECT PERS.ANR, COUNT(*) FROM PERS GROUP BY PERS.ANR;
SELECT PERS.ANR, SUM(test_table.SALARY) FROM PERS GROUP BY PERS.ANR;

SELECT * FROM test_table;
SELECT * FROM test_table1,test_table2;
SELECT test.abc, asd.as, sdd.sdf FROM test_table;
SELECT * FROM test_table WHERE (test_table.PNR = test_table.PNR) AND (test_table.ORT != 'Mainz');
SELECT * FROM test_table WHERE (test_table.ORT != 'Mainz') AND (asd.asd >= 10) or (asd.asd <= 10);
SELECT test.abc, asd.as, sdd.sdf FROM test_table,daas where (asd.asd = sdf.sdf);
SELECT test.abc, asd.as, sdd.sdf FROM test_table,daas where (asd.asd = sdf.sdf) AND (asd.asd >= 10) or (asd.asd <= 10);
SELECT PERS.PNR, PERS.PNAME, ABT.ANAME FROM PERS, ABT WHERE (PERS.PNR = ABT.PNR) AND (ABT.ORT != 'Mainz');
SELECT * FROM PERS, ABT WHERE (PERS.PNR = ABT.PNR) AND (PERS.NAME = 'Meier');

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


