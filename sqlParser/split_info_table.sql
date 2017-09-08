drop table split_info;

CREATE TABLE SPLIT_INFO (
affected_table VARCHAR(20),
which_column VARCHAR(20),
lower_bound integer,
upper_bound integer);
