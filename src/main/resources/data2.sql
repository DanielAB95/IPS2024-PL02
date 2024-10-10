--Datos para carga inicial de la base de datos

--Para proyecto IPS:

	
delete from producto;
insert into producto(id, nombre, categoria, descripcion, precio) values  
	(1, 'Taladro Inalámbrico', 'Ferretería', 'Taladro inalámbrico de 18V con batería de litio', 99.99),
	(2, 'Martillo de Uña', 'Ferretería', 'Martillo de uña de acero forjado con mango antideslizante', 14.50),
	(3, 'Destornillador Eléctrico', 'Ferretería', 'Destornillador eléctrico con 20 puntas intercambiables', 29.99),
	(4, 'Caja de Herramientas', 'Ferretería', 'Caja de herramientas de plástico con múltiples compartimentos', 24.99),
	(5, 'Llave Inglesa', 'Ferretería', 'Llave inglesa ajustable de acero inoxidable', 12.75),
	(6, 'Sierra Circular', 'Ferretería', 'Sierra circular de 7 1/4 pulgadas con motor de alta potencia', 139.90),
	(7, 'Caja de Almacenamiento', 'Almacén', 'Caja de almacenamiento de plástico con tapa hermética de 50L', 19.95),
	(8, 'Estantería Metálica', 'Almacén', 'Estantería metálica de 5 niveles con capacidad de 150kg por nivel', 89.99),
	(9, 'Carro de Carga', 'Almacén', 'Carro de carga plegable con capacidad de 200kg', 55.50),
	(10, 'Escalera de Aluminio', 'Almacén', 'Escalera de aluminio de 7 peldaños, ligera y resistente', 49.99);

delete from Pedido;
insert into Pedido(idPedido, numProductos, fecha, estado, idAlmacenero) values
	(1, 6, '2024-10-25', 'Pendiente', 2),
	(2, 1, '2024-05-08', 'Pendiente', 3),
	(3, 15, '2024-10-24', 'Listo', 10),
	(4, 5, '2024-10-26', 'Pendiente', 5),
	(5, 12, '2024-07-12', 'Pendiente', 7),
	(6, 9, '2024-12-01', 'Listo', 4),
	(7, 19, '2024-10-24', 'Pendiente', 1),
	(8, 22, '2024-10-31', 'Pendiente', 9),
	(9, 4, '2024-10-19', 'Listo', 8),
	(10, 13, '2024-10-04', 'Pendiente', 1),
	(11, 33, '2024-11-16', 'Pendiente', 10),
	(12, 2, '2024-03-14', 'Listo', 6);

delete from PedidoProducto
insert into PedidoProducto(idPedido, idProducto, cantidad) values
	(1, 1, 1),
	(1, 3, 2),
	(1, 4, 1),
	(1, 5, 2),
	(1, 6, 1),
	(1, 8, 1);
	
delete from Almacenero;
insert into Almacenero(idAlmacenero, nombre, apellido) values
	(1, 'Carlos', 'Garcia'),
	(2, 'Ana', 'Rodriguez'),
	(3, 'Luis', 'Diaz'),
	(4, 'Facundo', 'Alvarez'),
	(5, 'Laura', 'Martinez'),
	(6, 'Mario', 'Gomez'),
	(7, 'Lucia', 'Fernandez'),
	(8, 'Sara', 'Alvarez'),
	(9, 'Victor', 'Suarez'),
	(10, 'Maria', 'Garcia');

delete from Workorder;
create into Workorder(idWorkorder, idAlmacenero, idPedido) values
	(1, 1, 1);