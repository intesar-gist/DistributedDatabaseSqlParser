-- QL Queries
SELECT COUNT(*) FROM test_table;
SELECT R.A, COUNT(*) FROM R GROUP BY R.A;
SELECT test.abc, COUNT(*) FROM test_table GROUP BY test_table.col_name;
SELECT test.abc, count(*) FROM test_table GROUP BY test_table.col_name;
SELECT test_table.abc, SUM(test_table.sd) FROM test_table GROUP BY test_table.col_name;
SELECT test_table.abc, sum(test_table.sd) FROM test_table GROUP BY test_table.col_name;
SELECT PERS.ANR, COUNT(*) FROM PERS GROUP BY PERS.ANR;
SELECT PERS.ANR, SUM(test_table.SALARY) FROM PERS GROUP BY PERS.ANR;

SELECT * FROM test_table;
SELECT test_table.abc, test_table.as, test_table.sdf FROM test_table;
SELECT * FROM test_table WHERE (test_table.PNR = test_table.PPR);
SELECT * FROM R	WHERE (R.A = 10) AND (R.D = 'Kunz');
SELECT * FROM S	WHERE  (S.D = 'Kunz') OR (S.E = 100);
SELECT * FROM test_table1,test_table2;
SELECT * FROM R, S WHERE (R.A >= S.C);
SELECT * FROM test_table WHERE (test_table.PNR = test_table.PNR) AND (test_table.ORT != 'Mainz');
SELECT * FROM test_table WHERE (test_table.ORT != 'Mainz') AND (asd.asd >= 10) or (asd.asd <= 10);
SELECT test_table.abc, test_table.as, daas.sdf FROM test_table,daas where (daas.col_1 = test_table.col_1);
SELECT daas.abc, test_table.as, daas.sdf FROM test_table,daas where (asd.asd = sdf.sdf) AND (asd.asd >= 10) or (asd.asd <= 10);
SELECT PERS.PNR, PERS.PNAME, ABT.ANAME FROM PERS, ABT WHERE (PERS.PNR = ABT.PNR) AND (ABT.ORT != 'Mainz') AND (ABT.DFG = 34);
SELECT * FROM PERS, ABT WHERE (PERS.PNR = ABT.PNR) AND (PERS.NAME = 'Meier');

-- DML Queries
DELETE FROM PERSON WHERE PERSON_ID = 18 OR PERSON_NAME = 'INTESAR' AND AGE >= 90;
DELETE FROM PERSON;
INSERT INTO test_Table VALUES ('', 5.55,5.234234 ,'sdf',5.59898, 'sdf', .565, 65.6, null);

INSERT INTO FLUGLINIE VALUES ('AB', 'D  ', null, 'Air Berlin', null);                                   
INSERT INTO FLUGLINIE VALUES ('AC', 'CDN', null, 'Air Canada', 'Star');                               
INSERT INTO FLUGLINIE VALUES ('AF', 'F  ', null, 'Air France', 'SkyTeam');                            
INSERT INTO FLUGLINIE VALUES ('BA', 'GB ', null, 'British Airways', 'OneWorld');                      
INSERT INTO FLUGLINIE VALUES ('DB', 'D  ', null, 'Database Airlines', null);                            
INSERT INTO FLUGLINIE VALUES ('DI', 'D  ', null, 'Deutsche BA', null);                                  
INSERT INTO FLUGLINIE VALUES ('DL', 'USA', null, 'Delta Airlines', 'SkyTeam');                        
INSERT INTO FLUGLINIE VALUES ('JL', 'J  ', null, 'Japan Airlines', 'OneWorld');                       
INSERT INTO FLUGLINIE VALUES ('LH', 'D  ', null, 'Lufthansa', 'Star');                                
INSERT INTO FLUGLINIE VALUES ('NH', 'J  ', null, 'All Nippon Airways', 'Star');                       
INSERT INTO FLUGLINIE VALUES ('UA', 'USA', null, 'United Airlines', 'Star');                         

update FLUGLINIE set ALLIANZ = 'SkyTeam' where FLC = 'DL';
update FLUGLINIE set ALLIANZ = 'SkyTeam' where FLC = 'AF';
update FLUGLINIE set ALLIANZ = 'OneWorld' where FLC = 'JL';
update FLUGLINIE set ALLIANZ = 'OneWorld' where FLC = 'BA';
UPDATE PERS SET BONUS = 50, BONUS_1 = 100 WHERE PNR = 23 and PNR = 'sfsd' OR PNR > 23.5;
update FLUGLINIE set HUB = NULL;

-- DDL Queries
drop table BUCHUNG;

drop table PASSAGIER cascade constraints;

drop table FLUG cascade constraints;

drop table FLUGLINIE cascade constraints;

drop table FLUGHAFEN cascade constraints;

CREATE TABLE STUDENT_TEST1
(
    StudentName VARCHAR (20),
    Class VARCHAR(10),
    Rnum INTEGER(55)
);

CREATE TABLE STUDENT_TEST2
(
    StudentName VARCHAR (20),
    Class VARCHAR(10),
    Rnum INTEGER(55),
    CONSTRAINT esfsd1 UNIQUE (StudentName),
    CONSTRAINT esfsd1 PRIMARY KEY (Rnum)
) HORIZONTAL (Rnum(2,5646));

create table FLUGHAFEN_1 (
FHC		varchar(3),
LAND		varchar(3),
STADT		varchar(50) ,
NAME		varchar(50) ,
constraint FLUGHAFEN_PS primary key (FHC)
);

create table FLUG_1 (
FNR    integer,
FLC		varchar(2),
FLNR		integer,
VON		varchar(3),
NACH		varchar(3),
AB		integer(77),
AN		integer,
constraint FLUG_PS
		primary key (FNR)
)
HORIZONTAL (AB(0800,1200));


create table FLUGHAFEN (
FHC		varchar(3),
LAND		varchar(3),
STADT		varchar(50),
NAME		varchar(50),
constraint FLUGHAFEN_PS
		primary key (FHC)
);

create table FLUGLINIE (
FLC		varchar(2),
LAND		varchar(3),
HUB		varchar(3),
NAME		varchar(30),
ALLIANZ		varchar(20),
constraint FLUGLINIE_LAND_NN
		check (LAND is not null),
constraint FLUGLINIE_ALLIANZ_CHK
		check (ALLIANZ in ('Star','Excellence','Leftover','OneWorld','SkyTeam')),
constraint FLUGLINIE_PS
		primary key (FLC),
constraint FLUGLINIE_FS_HUB
		foreign key (HUB) references FLUGHAFEN(FHC)
);

create table FLUG (
FNR             integer,
FLC		varchar(2),
FLNR		integer,
VON		varchar(3),
NACH		varchar(3),
AB		integer,
AN		integer,
constraint FLUG_VON_NN
		check (VON is not null),
constraint FLUG_NACH_NN
		check (NACH is not null),
constraint FLUG_AB_NN
		check (AB is not null),
constraint FLUG_AN_NN
		check (AN is not null),
constraint FLUG_AB_CHK
		check (AB between 0 and 2400),
constraint FLUG_AN_CHK
		check (AN between 0 and 2400),
constraint FLUG_VONNACH_CHK
		check (VON != NACH),
constraint FLUG_PS
		primary key (FNR),
constraint FLUG_FS_FLC
		foreign key (FLC) references FLUGLINIE(FLC),
constraint FLUG_FS_VON
		foreign key (VON) references FLUGHAFEN(FHC),
constraint FLUG_FS_NACH
		foreign key (NACH) references FLUGHAFEN(FHC)
)
HORIZONTAL (AB(1000,1500));


create table PASSAGIER (
PNR		integer,
NAME		varchar(40),
VORNAME		varchar(40),
LAND		varchar(3),
constraint PASSAGIER_NAME_NN
                check (NAME is not null),
constraint PASSAGIER_PS
		primary key (PNR)
)
HORIZONTAL (PNR(35,70));


create table BUCHUNG (
BNR             integer,
PNR		integer,
FLC		varchar(2),
FLNR		integer,
VON		varchar(3),
NACH		varchar(3),
TAG		varchar(20),
MEILEN          integer,
PREIS           integer,
constraint BUCHUNG_NACH_NN
		check (NACH is not null),
constraint BUCHUNG_MEILEN_NN
		check (MEILEN is not null),
constraint BUCHUNG_PREIS_NN
		check (PREIS is not null),
constraint BUCHUNG_MEILEN_CHK
                check (MEILEN >= 0),
constraint BUCHUNG_PREIS_CHK
                check (PREIS > 0),
constraint BUCHUNG_PS
		primary key (BNR),
constraint BUCHUNG_FS_PNR
		foreign key (PNR) references PASSAGIER(PNR),
constraint BUCHUNG_FS_FLC
		foreign key (FLC) references FLUGLINIE(FLC),
constraint BUCHUNG_FS_VON
		foreign key (VON) references FLUGHAFEN(FHC),
constraint BUCHUNG_FS_NACH
		foreign key (NACH) references FLUGHAFEN(FHC)
)
HORIZONTAL (PNR(35,70));



