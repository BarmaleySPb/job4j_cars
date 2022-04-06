create table if not exists drivers (
    id SERIAL PRIMARY KEY,
    name varchar(2000),
    email varchar(2000),
    password varchar(2000),
    phoneNumber varchar(2000)
);

create table if not exists cars (
    id serial primary key,
    varchar name(2000),
    engine_id int not null references engines(id),
    carbody_id int not null references bodies(id)
);

create table if not exists engines (
    id serial primary key,
    name varchar(2000)
);

create table if not exists bodies (
    id serial primary key,
    name varchar(2000)
);

create table if not exists history_owner (
    id serial primary key,
    driver_id int not null references drivers(id),
    car_id int not null references cars(id)
);

insert into engines (name)
values ('benzine'), ('propane'), ('diesel');

insert into bodies (name)
values ('sedan'), ('pickup'), ('van'), ('minivan'), ('crossover');
