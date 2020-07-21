create table "address"
(
    "id" int primary key auto_increment,
    "country" varchar(20),
    "city" varchar(20),
    "county" varchar(20),
    "street_address" varchar(50)
);

create table "location"
(
    "id" int primary key auto_increment,
    "name" varchar(50) unique,
    "address_id" int references "address"("id")
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

create table "order"
(
    "id" int primary key auto_increment,
    "shipped_from" int references "location"("id"),
    "customer_id" int references "customer"("id"),
    "created_at" timestamp,
    "address_id" int references "address"("id")
);

create table "order_detail"
(
    "id" int primary key auto_increment,
    "order_id" int references "order"("id"),
    "product_id" int references "product"("id"),
    "quantity" int
)
