
--Para trabajo IPS:
drop table Producto;
drop table Pedido;
drop table PedidoProducto;
drop table Almacenero;
drop table AlmaceneroPedido;
drop table PedidoWorkorder;
drop table Workorder;

create table Producto(
    id int primary key not null, 
    nombre varchar(32) unique not null, 
    categoria varchar(32) not null, 
    descripcion varchar(200),
    precio decimal(10,2) not null,
    localizacion int not null
);

create table Pedido(
    idPedido int primary key not null,
    numProductos int not null,
    fecha date not null,
    estado varchar(20) not null,
    idAlmacenero int not null,
    foreign key (idAlmacenero) references Almacenero(idAlmacenero),
    check (estado in('Pendiente','Listo'))
);

create table PedidoProducto(
    idPedido int not null,
    idProducto int not null,
    cantidad int not null,
    foreign key (idPedido) references Pedido(idPedido)
    foreign key (idProducto) references Producto(id),
    check (cantidad >= 0)
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

create table PedidoWorkorder (
    idWorkorder int not null,
    idPedido int not null,
    foreign key (idWorkorder) references Workorder(idWorkorder),
    foreign key (idPedido) references Pedido(idPedido)
);

create table Workorder(
    idWorkorder  int primary key,
    idAlmacenero int not null,
    idPedido int not null,
    foreign key (idAlmacenero) references Almacenero(idAlmacenero),
    foreign key (idPedido) references Pedido(idPedido)
);
