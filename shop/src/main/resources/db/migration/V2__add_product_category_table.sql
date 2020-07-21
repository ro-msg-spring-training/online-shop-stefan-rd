create table "product_category"
(
    "id" int primary key auto_increment,
    "name" varchar(50) unique,
    "description" varchar(500)
);