create table "location"
(
    "id" int primary key auto_increment,
    "name" varchar(50) unique,
    "country" varchar(20),
    "city" varchar(20),
    "county" varchar(20),
    "street_address" varchar(50)
);

create table "stock"
(
    "id" int primary key auto_increment,
    "product_id" int references "product"("id"),
    "location_id" int references "location"("id"),
    "quantity" int
);

create table "revenue"
(
    "id" int primary key auto_increment,
    "location_id" int references "location"("id"),
    "date" date,
    "sum" decimal
);

create table "customer_order"
(
    "id" int primary key auto_increment,
    "shipped_from" int references "location"("id"),
    "customer_id" int references "customer"("id"),
    "created_at" timestamp,
    "country" varchar(20),
    "city" varchar(20),
    "county" varchar(20),
    "street_address" varchar(50)
);

create table "order_detail"
(
    "id" int primary key auto_increment,
    "order_id" int references "customer_order"("id"),
    "product_id" int references "product"("id"),
    "quantity" int
)
