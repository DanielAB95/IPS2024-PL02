
--Para trabajo IPS:
drop table Producto;
drop table Pedido;
drop table PedidoProducto;
drop table Almacenero;
drop table AlmaceneroPedido;
drop table PaqueteWorkorder;
drop table Workorder;
drop table Almacen;

create table Producto(
    id int primary key not null, 
    nombre varchar(32) unique not null, 
    categoria varchar(32) not null, 
    descripcion varchar(200),
    precio decimal(10,2) not null
);

create table PedidoProducto(
    idPedido int not null,
    idProducto int not null,
    cantidad int not null,
    foreign key (idPedido) references Pedido(idPedido),
    foreign key (idProducto) references Producto(id),
    check (cantidad >= 0)
);

create table Pedido(
    idPedido int primary key not null,
    numProductos int not null,
    fecha date not null,
    estado varchar(20) not null,
    check (estado in('Pendiente','Listo'))
);

create table Almacenero(
    idAlmacenero int primary key,
    nombre varchar(20) not null,
    apellido varchar(20) not null
);

create table AlmaceneroPedido(
    idAlmacenero int not null,
    idPedido int not null,
    foreign key (idAlmacenero) references Almacenero(idAlmacenero),
    foreign key (idPedido) references Pedido(idPedido)
);

create table PaqueteWorkorder (
    idWorkorder int not null,
    idPaquete int not null,
    foreign key (idWorkorder) references Workorder(idWorkorder),
    foreign key (idPaquete) references Paquete(idPaquete)
);

create table Workorder(
    idWorkorder  int primary key,
    idAlmacenero int not null,
    idPedido int not null,
    workorderEstado varchar(20) not null,
    check (workorderEstado in('Pendiente','Listo','Incidencia')),
    foreign key (idAlmacenero) references Almacenero(idAlmacenero),
    foreign key (idPedido) references Pedido(idPedido)
);

create table Almacen(
    idProducto int not null,
    estanteria int not null,
    posicionEstanteria int not null,
    pasillo int not null,
    foreign key (idProducto) references Producto(id)
);



