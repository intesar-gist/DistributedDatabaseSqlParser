-- -- Achtung! Add semicolon at the end of comments too;
--
-- SELECT COUNT(*) FROM BUCHUNG;
--
-- drop table simple;
-- create table simple (a integer, b VARCHAR(10));
--
-- insert into simple values (1, 'uno');
-- insert into simple values (2, 'dos');
-- insert into simple values (3, 'tres');
--
-- SELECT COUNT(*) FROM simple;
--
-- DELETE from simple where a=1;
-- DELETE from simple where a=100;
--
-- SELECT COUNT(*) FROM simple;
-- SELECT * FROM simple where (simple.a=3);
--
-- DELETE from simple;
--
-- insert into simple values (4, 'cuatro');
-- insert into simple values (5, 'cinco');


-- DISTRIBUTED TESTS;

drop table simple_d;
create table simple_d (col_a integer, col_b VARCHAR(10)) HORIZONTAL (col_a (3));

insert into simple_d values (1, 'uno');
insert into simple_d values (2, 'dos');
insert into simple_d values (3, 'tres');
insert into simple_d values (4, 'cuatro');
insert into simple_d values (5, 'cinco');


-- drop table simple_d2;
-- create table simple_d2 (col_a integer, col_b VARCHAR(10)) HORIZONTAL (col_a(3,7));
--
-- insert into simple_d2 values (1, 'uno');
-- insert into simple_d2 values (2, 'dos');
-- insert into simple_d2 values (3, 'tres');
-- insert into simple_d2 values (4, 'cuatro');
-- insert into simple_d2 values (5, 'cinco');
-- insert into simple_d2 values (6, 'seis');
-- insert into simple_d2 values (7, 'siete');
-- insert into simple_d2 values (8, 'ocho');
-- insert into simple_d2 values (9, 'nueve');


