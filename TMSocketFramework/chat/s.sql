create tablespace ch1
datafile 'ch2.dbf'
size 20M autoextend on;
create temporary tablespace ch1_tmp
tempfile 'ch_tmp2.dbf'
size 5M autoextend on;
create user ch1 identified by ch1
default tablespace ch1
temporary tablespace ch1_tmp;
grant connect to ch1;
grant all privileges to ch1;
grant unlimited tablespace to ch1;
