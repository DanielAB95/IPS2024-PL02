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

	
