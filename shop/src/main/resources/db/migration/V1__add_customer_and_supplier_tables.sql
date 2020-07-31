create table "customer"
(
    "id" int primary key auto_increment,
    "first_name" varchar(20),
    "last_name" varchar(20),
    "username" varchar(50) unique,
    "password" varchar(100),
    "email_address" varchar(50) unique
);

create table "supplier"
(
    "id" int primary key auto_increment,
    "name" varchar(50) unique
);