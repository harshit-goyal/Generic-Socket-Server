create tablespace chatts
datafile 'chattes.dbf'
size 20M autoextend on;
create temporary tablespace chatts_temp
tempfile 'chattes_temp.dbf'
size 5M autoextend on;
create user chat identified by chat
default tablespace chatts
temporary tablespace chatts_temp;
grant connect to chat;
grant all privileges to chat;
grant unlimited tablespace to chat;
