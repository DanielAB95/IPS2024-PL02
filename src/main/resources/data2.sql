--Datos para carga inicial de la base de datos

--Para proyecto IPS:

	
delete from producto;
insert into producto(id, nombre, categoria, descripcion, precio, pasillo, estanteria, balda) values  
	(1, 'Taladro Inalámbrico', 'Ferretería', 'Taladro inalámbrico de 18V con batería de litio', 99.99, 1, 1, 1),
	(2, 'Martillo de Uña', 'Ferretería', 'Martillo de uña de acero forjado con mango antideslizante', 14.50, 2, 2, 1),
	(3, 'Destornillador Eléctrico', 'Ferretería', 'Destornillador eléctrico con 20 puntas intercambiables', 29.99, 2, 3, 1),
	(4, 'Caja de Herramientas', 'Ferretería', 'Caja de herramientas de plástico con múltiples compartimentos', 24.99, 3, 4, 1),
	(5, 'Llave Inglesa', 'Ferretería', 'Llave inglesa ajustable de acero inoxidable', 12.75, 3, 5, 1),
	(6, 'Sierra Circular', 'Ferretería', 'Sierra circular de 7 1/4 pulgadas con motor de alta potencia', 139.90, 4, 6, 2),
	(7, 'Caja de Almacenamiento', 'Almacén', 'Caja de almacenamiento de plástico con tapa hermética de 50L', 19.95, 4, 7, 2),
	(8, 'Estantería Metálica', 'Almacén', 'Estantería metálica de 5 niveles con capacidad de 150kg por nivel', 89.99, 5, 8, 1),
	(9, 'Carro de Carga', 'Almacén', 'Carro de carga plegable con capacidad de 200kg', 55.50, 5, 9, 2),
	(10, 'Escalera de Aluminio', 'Almacén', 'Escalera de aluminio de 7 peldaños, ligera y resistente', 49.99, 6, 10, 3);

delete from Pedido;
insert into Pedido(idPedido,idCliente, fecha, estado) values
	(1, 2,'2024-10-25', 'Pendiente'),
	(2, 3, '2024-05-08', 'Pendiente'),
	(3, 5,'2024-10-24', 'Listo'),
	(4, 8,'2024-10-26', 'Pendiente'),
	(5, 22,'2024-07-12', 'Pendiente'),
	(6, 11,'2024-12-01', 'Listo'),
	(7, 15,'2024-10-24', 'Pendiente'),
	(8, 43,'2024-10-31', 'Pendiente'),
	(9, 1,'2024-10-19', 'Listo'),
	(10, 17, '2024-10-04', 'Pendiente'),
	(11, 44,'2024-11-16', 'Pendiente'),
	(12, 33, '2024-03-14', 'Listo');

delete from PedidoProducto;
insert into PedidoProducto(idPedido, idProducto, cantidad) values
	(1, 1, 1),
	(1, 4, 6),
	(1, 6, 16),
	(1, 7, 5),
	(1, 8, 3),
	(1, 10, 12),
	(1, 3, 2),
	(2, 3, 2),
	(4, 4, 4),
	(4, 6, 7),
	(5, 5, 2),
	(7, 6, 1),
	(8, 8, 1),
	(9, 2, 5),
	(9, 1, 2),
	(9, 4, 1),
	(12, 1, 1),
	(12, 2, 1);
	
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
insert into Workorder(idWorkorder, idAlmacenero, workorderEstado) values
	(1, 1, 'Pendiente'),
	(2, 2, 'Pendiente'),
	(3, 4, 'Pendiente'),
	(4, 5, 'Incidencia'),
	(5, 1, 'Listo'),
	(6, 3, 'Listo');

-- delete from Almacen;
-- insert into Almacen(idProducto, estanteria,posicionEstanteria, pasillo) values 
-- 	(1,1,1,1),
-- 	(2,1,2,1),
-- 	(3,1,3,1),
-- 	(4,2,1,2),
-- 	(5,2,2,2),
-- 	(6,2,3,2),
-- 	(7,3,1,2),
-- 	(8,3,2,2),
-- 	(9,3,3,2),
-- 	(10,4,1,3);

delete from Paquete;
insert into Paquete(idPaquete,idPedido, paqueteEstado) values
	(2, 3, 'Listo');


