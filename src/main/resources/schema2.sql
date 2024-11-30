--Para trabajo IPS:
drop table Producto;
drop table Pedido;
drop table PedidoProducto;
drop table Almacenero;
--drop table AlmaceneroPedido;
drop table Workorder;
drop table Paquete;
--drop table Almacen;
drop table Cliente;
drop table WorkorderPedido;
drop table Categoria;
drop table PaqueteProducto;
drop table WorkorderProducto;
drop table carrito;
drop table producto_carrito;
drop table workorderPaquete;
drop table vehiculo;

--estado: particular o empresa
create table Cliente(
	 idCliente varchar(9) primary key not null,
	 nombreUsuario varchar(32) unique not null,
	 nombre varchar(32) not null,
	 telefono varchar(32) not null,
	 pais varchar(32) not null,
	 region varchar(32) not null,
	 ciudad varchar(32) not null,
	 calle varchar(32) not null,
	 tipoCliente varchar(32) not null
);


--igual meter zonaReparto en paquete
create table Vehiculo (
	
	matricula varchar(10) primary key not null,
	zonaReparto varchar(15) not null,
    check (zonaReparto in('Regional','Nacional'))
	
);

create table carrito (
	id_cliente varchar(9) not null,
	id_carrito varchar(9) not null,
	foreign key (id_cliente) references cliente(idCliente)
);

create table producto_carrito (
	id_producto int not null,
	cantidad int not null,
	id_carrito varchar(9) not null,
	foreign key (id_producto) references producto(id),
	foreign key (id_carrito) references carrito(id_carrito)
);

create table Categoria (
	nombreCategoria varchar(20) primary key,
	categoriaPadre varchar(20),
	foreign key (categoriaPadre) references Categoria(nombreCategoria)
);

create table Producto(
    id int primary key not null, 
    nombre varchar(32) unique not null, 
    categoria varchar(20) not null, 
    descripcion varchar(200),
    precio decimal(10,2) not null,
    pasillo int not null,
    estanteria int not null,
    balda int not null,
    precioEmpresa decimal(10,2) not null,
    iva int not null,
    stock int not null,
    minStock int not null,
    stockReposicion int not null,
    
    foreign key (categoria) references Categoria(nombreCategoria)
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
    idCliente varchar(9) not null,
    fecha date not null,
    estado varchar(20) not null,
    check (estado in('Sin Facturar','Pendiente','Listo','Empaquetado')),
    foreign key (idCliente) references Cliente(idCliente)
);

create table Almacenero(
    idAlmacenero int primary key,
    nombre varchar(20) not null,
    apellido varchar(20) not null
);

create table Workorder(
    idWorkorder  int primary key,
    idAlmacenero int not null,
    workorderEstado varchar(20) not null,
    fecha date,
    check (workorderEstado in('Pendiente','En Curso','Incidencia','Listo','Empaquetada')),
    foreign key (idAlmacenero) references Almacenero(idAlmacenero)
);

create table WorkorderPedido (
	idWorkorder int not null, 
	idPedido int not null,
	foreign key (idPedido) references Pedido(idPedido),
	foreign key (idWorkorder) references Workorder(idWorkorder)
);

create table Paquete(
    idPaquete int primary key,
    idPedido int not null,
    paqueteEstado varchar(20) not null,
    fecha date,
    check (paqueteEstado in('En Curso','Listo','Incidencia')),
    foreign key (idPedido) references Pedido(idPedido)
);

create table WorkorderProducto(
    idWorkorder int not null,
    idPedido int not null,
    idProducto int not null,
    cantidad int not null,
    recogidos int not null,
    foreign key (idWorkorder) references Workorder(idWorkorder),
    foreign key (idPedido) references Pedido(idPedido),
    foreign key (idProducto) references Producto(id),
    check (cantidad >= 0),
    check (recogidos >= 0),
    primary key (idWorkorder, idPedido, idProducto)
);

create table PaqueteProducto(
    idPaquete int not null,
    idProducto int not null,
    cantidad int not null,
    foreign key (idPaquete) references Paquete(idPaquete),
    foreign key (idProducto) references Producto(id),
    check (cantidad >= 0),
    primary key (idPaquete, idProducto)
);

create table workorderPaquete(
    idWorkorder int not null,
    idPaquete int not null,
    foreign key (idWorkorder) references Workorder(idWorkorder),
    foreign key (idPaquete) references Paquete(idPaquete)
);