
--Para trabajo IPS:
drop table Producto;
create table Producto (id int primary key not null, nombre varchar(32) unique not null, categoria varchar(32) not null, descripcion varchar(200), precio decimal(10,2) not null);

