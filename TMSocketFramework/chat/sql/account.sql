create table account(
user_id decimal(10,0),
user_name char(20) unique not null,
email_id char(100) unique not null,
password char(100) not null,
possword_key char(100) not null,
first_name char(20) not null,
last_name char(20) not null,
gender char(1) not null,
status char(1) not null,
primary key(user_id)
);
