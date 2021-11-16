create schema cajeros;
-- creacion de tablas
create table usuarios (
    usuario varchar(255) not null,
    pass varchar(100) not null,
    saldo DECIMAL(15,2) not null default 0
);

