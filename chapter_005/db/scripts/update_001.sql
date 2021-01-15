create table food(
    id serial primary key,
    name varchar(100),
    expiryDate timestamp,
    createDate timestamp,
    price decimal,
    discount decimal,
    unique (name, expiryDate, createDate, price)
);