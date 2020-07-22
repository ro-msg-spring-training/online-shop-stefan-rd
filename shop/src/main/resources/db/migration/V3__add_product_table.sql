create table "product"
(
    "id" int primary key auto_increment,
    "name" varchar(50),
    "description" varchar(500),
    "price" decimal,
    "weight" double,
    "category_id" int references "product_category"("id"),
    "supplier_id" int references "supplier"("id"),
    "image_url" varchar(500)
);