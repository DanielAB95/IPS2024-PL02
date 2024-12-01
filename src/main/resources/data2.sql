--Datos para carga inicial de la base de datos

--Para proyecto IPS:

delete from cliente;
--PARTICULARES
INSERT INTO Cliente (idCliente, nombreUsuario, nombre, telefono, pais, region, ciudad, calle, tipoCliente) VALUES
('CLI000001', 'jlopez92', 'Juan Lopez', '+34123456789', 'España', 'Madrid', 'Madrid', 'Calle Mayor 15 5º izquierda', 'PARTICULAR'),
('CLI000002', 'mmartinez87', 'Maria Martinez', '+34567891234', 'España', 'Cataluña', 'Barcelona', 'Avenida Diagonal 101 5º izquierda', 'PARTICULAR'),
('CLI000003', 'rvargas90', 'Raul Vargas', '+34987654321', 'España', 'Valencia', 'Valencia', 'Calle Colon 22 5º izquierda', 'PARTICULAR'),
('CLI000004', 'asolano88', 'Andrea Solano', '+34901234567', 'España', 'Andalucía', 'Sevilla', 'Calle Feria 78 5º izquierda', 'PARTICULAR'),
('CLI000005', 'cfuentes95', 'Carlos Fuentes', '+34987654321', 'España', 'Galicia', 'A Coruña', 'Rua Nova 11 3º B', 'PARTICULAR'),
('CLI000006', 'lflores80', 'Laura Flores', '+35123456789', 'Portugal', 'Lisboa', 'Lisboa', 'Rua Augusta 200 3º B', 'PARTICULAR'),
('CLI000007', 'jsantos83', 'Javier Santos', '+33456789123', 'Francia', 'Île-de-France', 'Paris', 'Rue de Rivoli 150 3º B', 'PARTICULAR'),
('CLI000008', 'mbrown91', 'Michael Brown', '+44123456789', 'Reino Unido', 'Inglaterra', 'Londres', 'Baker Street 221B 3º B' , 'PARTICULAR'),
('CLI000009', 'cchen99', 'Chen Chen', '+81312345678', 'Japón', 'Kanto', 'Tokio', 'Shibuya 109 3º B', 'PARTICULAR'),
('CLI000010', 'asang95', 'Anita Sang', '+12123456789', 'EE.UU.', 'California', 'Los Ángeles', 'Sunset Blvd 500 3º B', 'PARTICULAR');
--EMPRESAS

INSERT INTO Cliente (idCliente, nombreUsuario, nombre, telefono, pais, region, ciudad, calle, tipoCliente) VALUES
('CLI000011', 'techsol_inc', 'Tech Solutions Inc.', '+34987651234', 'España', 'Madrid', 'Madrid', 'Calle Gran Vía 1', 'EMPRESA'),
('CLI000012', 'bioenviro_sa', 'BioEnviro S.A.', '+34906547812', 'España', 'Cataluña', 'Barcelona', 'Passeig de Gracia 55', 'EMPRESA'),
('CLI000013', 'ecomerce_llc', 'E-Commerce LLC', '+447765432109', 'Reino Unido', 'Inglaterra', 'Londres', 'King Street 75', 'EMPRESA'),
('CLI000014', 'softdev_solutions', 'SoftDev Solutions', '+12024567890', 'EE.UU.', 'Nueva York', 'Nueva York', '5th Avenue 300', 'EMPRESA'),
('CLI000015', 'global_trades', 'Global Trades Ltd.', '+81345678901', 'Japón', 'Kanto', 'Tokio', 'Ginza Street 10', 'EMPRESA');

delete from carrito;
INSERT INTO carrito (id_cliente, id_carrito) VALUES
('CLI000001', 'CAR000001'),
('CLI000002', 'CAR000002'),
('CLI000003', 'CAR000003'),
('CLI000004', 'CAR000004'),
('CLI000005', 'CAR000005'),
('CLI000006', 'CAR000006'),
('CLI000007', 'CAR000007'),
('CLI000008', 'CAR000008'),
('CLI000009', 'CAR000009'),
('CLI000010', 'CAR000010'),
('CLI000011', 'CAR000011'),
('CLI000012', 'CAR000012'),
('CLI000013', 'CAR000013'),
('CLI000014', 'CAR000014'),
('CLI000015', 'CAR000015');

  
delete from Producto;
insert into Producto(id, nombre, categoria, descripcion, precio, pasillo, 
	estanteria, balda, precioEmpresa, iva, stock, minStock, stockReposicion) values  
    -- Productos en la categoría Ferretería
    (1, 'Llave Inglesa', 'Llaves y Pinzas', 'Llave inglesa ajustable', 15.99, 1, 1, 1, 10, 12, 50, 10, 20),
    (2, 'Pinzas de Corte', 'Llaves y Pinzas', 'Pinzas de corte de precisión', 8.50, 1, 1, 2, 6, 12, 100, 15, 30),
    (3, 'Destornillador Philips', 'Destornilladores', 'Destornillador Philips mediano', 4.99, 1, 1, 3, 3, 12, 200, 20, 50),
    (4, 'Juego de Destornilladores', 'Destornilladores', 'Set de destornilladores de varios tamaños', 12.99, 2, 2, 1, 10, 12, 75, 15, 30),
    (5, 'Taladro Percutor', 'Taladros', 'Taladro percutor con múltiples brocas', 49.99, 2, 2, 2, 40, 12, 30, 5, 15),
    (6, 'Taladro Inalámbrico', 'Taladros', 'Taladro inalámbrico de 18V', 89.99, 2, 2, 3, 80, 12, 25, 5, 10),
    (7, 'Sierra Circular', 'Sierras', 'Sierra circular para madera', 129.99, 2, 3, 1, 100, 12, 15, 3, 5),
    (8, 'Sierra Caladora', 'Sierras', 'Sierra caladora para cortes curvos', 69.99, 2, 3, 2, 60, 12, 20, 4, 8),
     
    -- Productos en la categoría Almacén
    (9, 'Caja de Plástico 50L', 'Cajas y Contenedores', 'Caja de almacenamiento de 50 litros', 19.99, 2, 3, 3, 10, 21, 30, 10, 50),
    (10, 'Caja de Plástico 100L', 'Cajas y Contenedores', 'Caja de almacenamiento de 100 litros', 29.99, 3, 4, 1, 10, 21, 20, 5, 40),
    (11, 'Estantería de Metal', 'Estanterías', 'Estantería de metal con 5 niveles', 89.99, 3, 4, 2, 10, 21, 10, 2, 20),
    (12, 'Estantería de Plástico', 'Estanterías', 'Estantería plástica para garaje', 49.99, 3, 4, 3, 10, 21, 15, 5, 30),
    (13, 'Carretilla de Mano', 'Carretillas', 'Carretilla de mano con capacidad de 200kg', 55.99, 3, 5, 1, 10, 21, 4, 5, 25),
    (14, 'Carro de Plataforma', 'Plataformas', 'Carro de plataforma plegable', 75.99, 3, 5, 2, 10, 21, 10, 3, 20),
    
    -- Productos en la categoría Muebles
    (15, 'Sofá 3 Plazas', 'Sofás', 'Sofá de 3 plazas de tela gris', 299.99, 3, 5, 3, 10, 10, 5, 2, 10),
    (16, 'Sofá Esquinero', 'Sofás', 'Sofá esquinero modular', 499.99, 4, 6, 1, 10, 10, 3, 1, 5),
    (17, 'Mesa de Centro', 'Mesas de Centro', 'Mesa de centro de madera', 99.99, 4, 6, 2, 10, 10, 10, 3, 20),
    (18, 'Mesa de Centro con Cristal', 'Mesas de Centro', 'Mesa de centro de cristal y metal', 149.99, 4, 6, 3, 10, 10, 8, 2, 15),
    (19, 'Cama Matrimonial', 'Camas', 'Cama matrimonial con base de madera', 249.99, 4, 7, 1, 10, 10, 5, 2, 10),
    (20, 'Cama King Size', 'Camas', 'Cama king size con cabecera', 399.99, 4, 7, 2, 10, 10, 3, 1, 5),
    (21, 'Mesa de Noche', 'Mesas de Noche', 'Mesa de noche con cajón', 59.99, 4, 7, 3, 10, 10, 15, 5, 30),
    (22, 'Mesa de Noche Moderna', 'Mesas de Noche', 'Mesa de noche con diseño moderno', 79.99, 5, 8, 1, 10, 10, 2, 4, 25),
    
    -- Productos en la categoría Electrodomésticos
    (23, 'Horno Eléctrico', 'Hornos', 'Horno eléctrico de gran capacidad', 199.99, 5, 8, 2, 10, 21, 8, 2, 15),
    (24, 'Horno Microondas', 'Microondas', 'Microondas digital con grill', 99.99, 5, 8, 3, 10, 21, 15, 5, 30),
    (25, 'Lavadora Automática', 'Lavadoras', 'Lavadora automática de alta eficiencia', 399.99, 5, 9, 1, 10, 21, 5, 1, 10),
    (26, 'Secadora de Ropa', 'Secadoras', 'Secadora de ropa con sensor de humedad', 349.99, 5, 9, 2, 10, 21, 5, 1, 10),
    
    -- Productos en la categoría Tecnología
    (27, 'Laptop de 14 pulgadas', 'Laptops', 'Laptop ultradelgada con SSD de 512GB', 899.99, 5, 9, 3, 10, 21, 5, 1, 10),
    (28, 'PC de Escritorio', 'Escritorio', 'PC de escritorio con 16GB RAM y 1TB HDD', 699.99, 6, 10, 1, 10, 21, 5, 1, 10),
    (29, 'Smartphone 5G', 'Smartphones', 'Smartphone con pantalla OLED de 6.5 pulgadas', 699.99, 6, 10, 2, 10, 21, 13, 2, 15),
    (30, 'Teléfono Inalámbrico', 'Teléfonos Fijos', 'Teléfono inalámbrico con contestador', 49.99, 6, 10, 3, 10, 21, 4, 5, 40);

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
	(1, 'CLI000001', '2024-10-25', 'Sin Facturar'),
	(2, 'CLI000002', '2024-05-08', 'Sin Facturar'),
	(3, 'CLI000002', '2024-10-24', 'Empaquetado'),
	(4, 'CLI000003', '2024-10-26', 'Pendiente'),
	(5, 'CLI000003', '2024-07-12', 'Pendiente'),
	(6, 'CLI000004', '2024-11-01', 'Pendiente'),
	(7, 'CLI000005', '2024-10-24', 'Pendiente'),
	(8, 'CLI000006', '2024-10-31', 'Pendiente'),
	(9, 'CLI000007', '2024-10-19', 'Pendiente'),
	(10, 'CLI000008', '2024-10-04', 'Sin Facturar'),
	(11, 'CLI000009', '2024-10-16', 'Sin Facturar'),
	(12, 'CLI000001', '2024-03-14', 'Listo'),
    (13, 'CLI000009', '2024-05-14', 'Listo'),
    (14, 'CLI000010', '2024-05-15', 'Listo'),
    (15, 'CLI000001', '2024-05-15', 'Listo'),
    (16, 'CLI000010', '2024-05-10', 'Listo'),
    (17, 'CLI000010', '2024-05-12', 'Listo'),
    (18, 'CLI000010', '2024-04-11', 'Pendiente');

delete from PedidoProducto;
insert into PedidoProducto(idPedido, idProducto, cantidad) values
    (1, 25, 1),
    (2, 30, 1),
    (3, 1, 2),
    (3, 2, 1),
    (4, 15, 1),
    (5, 19, 1),
    (6, 21, 1),
    (7, 1, 3),
    (8, 11, 1),
    (8, 10, 1),
    (9, 9, 8),
    (10, 25, 1),
    (11, 30, 1),
    (12, 4, 2),
    (12, 18, 1),
    (12, 29, 3),
    (13, 21, 1),
    (13, 30, 1),
    (14, 29, 1),
    (15, 1, 4),
    (15, 2, 1),
    (16, 1, 1),
    (17, 19, 1),
    (18, 21, 1),
    (18, 30, 3),
    (18, 29, 6),
    (18, 1, 4),
    (18, 7, 9),
    (18, 3, 6),
    (18, 11, 4),
    (18, 2, 3),
    (18, 6, 4),
    (18, 14, 9),
    (18, 19, 3);
	
delete from Almacenero;
insert into Almacenero(idAlmacenero, nombre, apellido) values
    (0, 'Sin', 'Asignar'),
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
insert into Workorder(idWorkorder, idAlmacenero, workorderEstado, fecha) values
    (1, 1, 'Pendiente', null),
    (2, 1, 'Pendiente', null),
    (3, 1, 'Pendiente', null),
    (5, 2, 'Empaquetada', '2024-09-21'),
    (300, 1, 'Listo','2024-10-25'),
    (301, 1, 'Listo','2024-10-25'),
    (302, 1, 'Listo','2024-10-25');

delete from WorkorderPedido;
insert into WorkorderPedido(idWorkorder, idPedido) values
    (1, 12),
    (2, 12),
    (3, 16),
    (3, 17),
    (5, 3),
    (300, 13),
    (300, 14),
    (301, 15),
    (302, 15);

delete from WorkorderProducto;
insert into WorkorderProducto(idWorkorder, idPedido, idProducto, cantidad, recogidos) values
    (1, 12, 4, 2, 0),
    (1, 12, 18, 1, 0),
    (2, 12, 29, 3, 0),
    (3, 16, 1, 1, 0),
    (3, 17, 19, 1, 0),
    (5, 3, 1, 2, 2),
    (5, 3, 2, 1, 1),
    (300, 13, 21, 1, 1),
    (300, 13, 30, 1, 1),
    (300, 14, 29, 1, 1),
    (301, 15, 1, 3, 3),
    (302, 15, 1, 1, 1),
    (302, 15, 2, 1, 1);

delete from Paquete;
insert into Paquete(idPaquete, idPedido, idAlmacenero, paqueteEstado, fecha) values
	(2, 3, 2, 'Listo', '2024-10-25'),
    (1, 2, 1, 'Listo', '2024-11-30'),
    (4, 1, 6, 'Listo', '2024-02-01');

delete from PaqueteProducto;
insert into PaqueteProducto(idPaquete, idProducto, cantidad) values
    (2, 1, 2),
    (2, 2, 1);

delete from WorkorderPaquete;
insert into WorkorderPaquete(idWorkorder, idPaquete) values
    (5, 2);

