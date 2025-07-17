CREATE table users
(
    id         serial primary key,
    first_name varchar(100) not null,
    last_name  varchar(100) not null,
    age        int          not null,
    created_at timestamp default current_timestamp
);
create table "blacklist"
(
    "id"         serial primary key,
    "last_name"  varchar(100) not null,
    "created_at" timestamp with time zone default now()
);
