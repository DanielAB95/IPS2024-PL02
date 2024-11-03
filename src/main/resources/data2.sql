--Datos para carga inicial de la base de datos

--Para proyecto IPS:

delete from producto;
insert into producto(id, nombre, categoria, descripcion, precio, pasillo, estanteria, balda) values  
	-- Productos en la categoría Ferretería
    (1, 'Llave Inglesa', 'Llaves y Pinzas', 'Llave inglesa ajustable', 15.99, 1, 1, 1),
    (2, 'Pinzas de Corte', 'Llaves y Pinzas', 'Pinzas de corte de precisión', 8.50, 1, 1, 2),
    (3, 'Destornillador Philips', 'Destornilladores', 'Destornillador Philips mediano', 4.99, 1, 2, 1),
    (4, 'Juego de Destornilladores', 'Destornilladores', 'Set de destornilladores de varios tamaños', 12.99, 1, 2, 2),
    (5, 'Taladro Percutor', 'Taladros', 'Taladro percutor con múltiples brocas', 49.99, 1, 3, 1),
    (6, 'Taladro Inalámbrico', 'Taladros', 'Taladro inalámbrico de 18V', 89.99, 1, 3, 2),
    (7, 'Sierra Circular', 'Sierras', 'Sierra circular para madera', 129.99, 1, 4, 1),
    (8, 'Sierra Caladora', 'Sierras', 'Sierra caladora para cortes curvos', 69.99, 1, 4, 2),
     
    -- Productos en la categoría Almacén
    (9, 'Caja de Plástico 50L', 'Cajas y Contenedores', 'Caja de almacenamiento de 50 litros', 19.99, 2, 1, 1),
    (10, 'Caja de Plástico 100L', 'Cajas y Contenedores', 'Caja de almacenamiento de 100 litros', 29.99, 2, 1, 2),
    (11, 'Estantería de Metal', 'Estanterías', 'Estantería de metal con 5 niveles', 89.99, 2, 2, 1),
    (12, 'Estantería de Plástico', 'Estanterías', 'Estantería plástica para garaje', 49.99, 2, 2, 2),
    (13, 'Carretilla de Mano', 'Carretillas', 'Carretilla de mano con capacidad de 200kg', 55.99, 2, 3, 1),
    (14, 'Carro de Plataforma', 'Plataformas', 'Carro de plataforma plegable', 75.99, 2, 3, 2),
    
    -- Productos en la categoría Muebles
    (15, 'Sofá 3 Plazas', 'Sofás', 'Sofá de 3 plazas de tela gris', 299.99, 3, 1, 1),
    (16, 'Sofá Esquinero', 'Sofás', 'Sofá esquinero modular', 499.99, 3, 1, 2),
    (17, 'Mesa de Centro', 'Mesas de Centro', 'Mesa de centro de madera', 99.99, 3, 2, 1),
    (18, 'Mesa de Centro con Cristal', 'Mesas de Centro', 'Mesa de centro de cristal y metal', 149.99, 3, 2, 2),
    (19, 'Cama Matrimonial', 'Camas', 'Cama matrimonial con base de madera', 249.99, 3, 3, 1),
    (20, 'Cama King Size', 'Camas', 'Cama king size con cabecera', 399.99, 3, 3, 2),
    (21, 'Mesa de Noche', 'Mesas de Noche', 'Mesa de noche con cajón', 59.99, 3, 4, 1),
    (22, 'Mesa de Noche Moderna', 'Mesas de Noche', 'Mesa de noche con diseño moderno', 79.99, 3, 4, 2),
    
    -- Productos en la categoría Electrodomésticos
    (23, 'Horno Eléctrico', 'Hornos', 'Horno eléctrico de gran capacidad', 199.99, 4, 1, 1),
    (24, 'Horno Microondas', 'Microondas', 'Microondas digital con grill', 99.99, 4, 1, 2),
    (25, 'Lavadora Automática', 'Lavadoras', 'Lavadora automática de alta eficiencia', 399.99, 4, 2, 1),
    (26, 'Secadora de Ropa', 'Secadoras', 'Secadora de ropa con sensor de humedad', 349.99, 4, 2, 2),
    
    -- Productos en la categoría Tecnología
    (27, 'Laptop de 14 pulgadas', 'Laptops', 'Laptop ultradelgada con SSD de 512GB', 899.99, 5, 1, 1),
    (28, 'PC de Escritorio', 'Escritorio', 'PC de escritorio con 16GB RAM y 1TB HDD', 699.99, 5, 1, 2),
    (29, 'Smartphone 5G', 'Smartphones', 'Smartphone con pantalla OLED de 6.5 pulgadas', 699.99, 5, 2, 1),
    (30, 'Teléfono Inalámbrico', 'Teléfonos Fijos', 'Teléfono inalámbrico con contestador', 49.99, 5, 2, 2);

delete from Categoria;
insert into Categoria(nombreCategoria,categoriaPadre) values
	-- Categorías principales
    ('Ferretería', NULL),
    ('Almacén', NULL),
    ('Muebles', NULL),
    ('Electrodomésticos', NULL),
    ('Tecnología', NULL),
    
    -- Subcategorías de Ferretería
    ('Herramientas Manuales', 'Ferretería'),
    ('Herramientas Eléctricas', 'Ferretería'),
    
    -- Sub-subcategorías de Herramientas Manuales
    ('Llaves y Pinzas', 'Herramientas Manuales'),
    ('Destornilladores', 'Herramientas Manuales'),
    
    -- Sub-subcategorías de Herramientas Eléctricas
    ('Taladros', 'Herramientas Eléctricas'),
    ('Sierras', 'Herramientas Eléctricas'),

    -- Subcategorías de Almacén
    ('Organización', 'Almacén'),
    ('Equipos de Carga', 'Almacén'),
    
    -- Sub-subcategorías de Organización
    ('Cajas y Contenedores', 'Organización'),
    ('Estanterías', 'Organización'),
    
    -- Sub-subcategorías de Equipos de Carga
    ('Carretillas', 'Equipos de Carga'),
    ('Plataformas', 'Equipos de Carga'),
    
    -- Subcategorías de Muebles
    ('Salas', 'Muebles'),
    ('Dormitorios', 'Muebles'),
    
    -- Sub-subcategorías de Salas
    ('Sofás', 'Salas'),
    ('Mesas de Centro', 'Salas'),
    
    -- Sub-subcategorías de Dormitorios
    ('Camas', 'Dormitorios'),
    ('Mesas de Noche', 'Dormitorios'),
    
    -- Subcategorías de Electrodomésticos
    ('Cocina', 'Electrodomésticos'),
    ('Lavado', 'Electrodomésticos'),
    
    -- Sub-subcategorías de Cocina
    ('Hornos', 'Cocina'),
    ('Microondas', 'Cocina'),
    
    -- Sub-subcategorías de Lavado
    ('Lavadoras', 'Lavado'),
    ('Secadoras', 'Lavado'),
    
    -- Subcategorías de Tecnología
    ('Computadoras', 'Tecnología'),
    ('Teléfonos', 'Tecnología'),
    
    -- Sub-subcategorías de Computadoras
    ('Laptops', 'Computadoras'),
    ('Escritorio', 'Computadoras'),
    
    -- Sub-subcategorías de Teléfonos
    ('Smartphones', 'Teléfonos'),
    ('Teléfonos Fijos', 'Teléfonos');

delete from Pedido;
insert into Pedido(idPedido, idCliente, fecha, estado) values
	(1, 2,'2024-10-25', 'Pendiente'),
	(2, 3, '2024-05-08', 'Pendiente'),
	(3, 5,'2024-10-24', 'Listo'),
	(4, 8,'2024-10-26', 'Pendiente'),
	(5, 22,'2024-07-12', 'Pendiente'),
	(6, 11,'2024-12-01', 'Pendiente'),
	(7, 15,'2024-10-24', 'Pendiente'),
	(8, 43,'2024-10-31', 'Pendiente'),
	(9, 1,'2024-10-19', 'Pendiente'),
	(10, 17, '2024-10-04', 'Pendiente'),
	(11, 44,'2024-11-16', 'Pendiente'),
	(12, 33, '2024-03-14', 'Listo'),
    (13, 200, '2024-05-14', 'Listo'),
    (14, 201, '2024-05-15', 'Listo'),
    (15, 202, '2024-05-15', 'Listo');

delete from PedidoProducto;
insert into PedidoProducto(idPedido, idProducto, cantidad) values
    (3, 1, 2),
    (3, 2, 1),
    (13, 21, 1),
    (13, 30, 1),
    (14, 29, 1),
    (15, 1, 4),
    (15, 2, 1);
	
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

delete from WorkorderPedido;
insert into WorkorderPedido(idWorkorder, idPedido) values
    (5, 3),
    (300, 13),
    (300, 14),
    (301, 15),
    (302, 15);

delete from WorkorderProducto;
insert into WorkorderProducto(idWorkorder, idPedido, idProducto, cantidad, recogidos) values
    (5, 3, 1, 2, 2),
    (5, 3, 2, 1, 1),
    (300, 13, 21, 1, 1),
    (300, 13, 30, 1, 1),
    (300, 14, 29, 1, 1),
    (301, 15, 1, 3, 3),
    (302, 15, 1, 1, 1),
    (302, 15, 2, 1, 1);

delete from Workorder;
insert into Workorder(idWorkorder, idAlmacenero, workorderEstado) values
    (5, 1, 'Empaquetada'),
    (300, 1, 'Listo'),
    (301, 1, 'Listo'),
    (302, 1, 'Listo');

delete from Paquete;
insert into Paquete(idPaquete, idPedido, paqueteEstado) values
	(2, 3, 'Listo');

delete from PaqueteProducto;
insert into PaqueteProducto(idPaquete, idProducto, cantidad) values
    (2, 1, 2),
    (2, 2, 1);

