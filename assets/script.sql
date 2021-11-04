create schema cajeros;

create table usuarios
(
    usuario varchar(255) not null
        primary key,
    pass    varchar(8)   not null
);

