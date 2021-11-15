create schema cajeros;
-- creacion de tablas
create table usuarios (
    usuario varchar(255) not null,
    pass varchar(100) not null
);
create table cuenta(
    id_cuenta int not null,
    usuario varchar(255) not null,
    saldo decimal(15, 2) not null,
);
-- creacion de llaves primarias
alter table usuarios
add constraint pk_usuarios primary key (usuario);
alter table cuenta
add constraint pk_cuenta primary key (id_cuenta);
-- creacion de llaves foraneas
alter table cuenta
add constraint fk_cuenta_usuario foreign key (usuario) references usuarios(usuario) on delete cascade;