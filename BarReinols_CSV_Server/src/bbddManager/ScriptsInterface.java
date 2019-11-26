package bbddManager;

/*
 * NO HACER CONTROL + F!
 */

public interface ScriptsInterface {

	String createCategorias = "CREATE TABLE categorias (" + 
			"	id_categoria INT NOT NULL AUTO_INCREMENT," + 
			"   nombre VARCHAR(50)," + 
			"   PRIMARY KEY (id_categoria)" + 
			")";
	
	String createCamareros = "CREATE TABLE camareros (" + 
			"	id_camarero INT NOT NULL AUTO_INCREMENT," + 
			"    nombre VARCHAR(50)," + 
			"    username VARCHAR(50)," +
			"    password VARCHAR(50)," + 
			"    PRIMARY KEY (id_camarero)" + 
			")";
	
	String createProductos = "CREATE TABLE productos (" + 
			"	id_producto INT NOT NULL AUTO_INCREMENT," + 
			"    nombre VARCHAR(50)," + 
			"    precio_producto FLOAT(4,2)," + 
			"    descripcion VARCHAR(200)," + 
			"    imagen VARCHAR(100)," + 
			"    image_movil VARCHAR(100)," + 
			"    id_categoria INT NOT NULL," + 
			"    PRIMARY KEY (id_producto)," + 
			"    CONSTRAINT id_categoria FOREIGN KEY (id_categoria) REFERENCES categorias (id_categoria)" + 
			")";
	
	String createComandas = "CREATE TABLE comandas (" + 
			"	 mesa INT NOT NULL," +
			"    id_camarero INT NOT NULL," + 
			"    datetime TIMESTAMP NOT NULL," + 
			"    PRIMARY KEY (mesa)," + 
			"    CONSTRAINT id_camarero FOREIGN KEY (id_camarero) REFERENCES camareros (id_camarero)" + 
			")";
	
	String createDetailsComanda = "CREATE TABLE details_comandas (" + 
			"	 mesa INT NOT NULL," + 
			"    id_producto INT NOT NULL," + 
			"    cantidad_producto INT NOT NULL," + 
			"    precio_producto FLOAT(4,2) NOT NULL," + 
			"	 servido BOOLEAN," +
			"    CONSTRAINT mesa_fk FOREIGN KEY (mesa) REFERENCES comandas (mesa)," + 
			"    CONSTRAINT id_producto_fk FOREIGN KEY (id_producto) REFERENCES productos (id_producto)" +  
			")";
	
	
	String createFacturas = "CREATE TABLE facturas (" + 
			"	id_factura INT NOT NULL AUTO_INCREMENT," +
			"	mesa INT NOT NULL," +
			"	id_camarero INT NOT NULL, " + 
			"	datetime TIMESTAMP NOT NULL," + 
			"	precio_final_noiva FLOAT(5,2)," + 
			"	precio_final_iva FLOAT(5,2)," + 
			"    PRIMARY KEY (id_factura)" + 
			")";
	
	String createMesaConfig = "CREATE TABLE mesa_maestra_configuracion (" + 
			"	ip VARCHAR(20)," + 
			"    nombre_pda VARCHAR(50)," + 
			"    cantidad_mesa INT" + 
			")";
	
	String createDetailsFacturas = "CREATE TABLE details_facturas (" + 
			"    id_factura INT NOT NULL," + 
			"    nombre_producto VARCHAR(50) NOT NULL," + 
			"    precio_producto FLOAT(4,2) NOT NULL," + 
			"    cantidad INT NOT NULL," + 
			"    CONSTRAINT id_factura_fk FOREIGN KEY (id_factura) REFERENCES facturas (id_factura)" + 
			")";
	
	String[] insertIntoCamareros = {
			"INSERT INTO camareros (nombre, username, password) VALUES ('Jonatan Valle', 'jvalle', 'P@ssw0rd')",
			"INSERT INTO camareros (nombre, username, password) VALUES ('David Salas', 'dsalas', 'P@ssw0rd')",
			"INSERT INTO camareros (nombre, username, password) VALUES ('Erik Cabezuelo', 'ecabezue', 'P@ssw0rd')"
			};
	
	String[] insertIntoCategorias = {
			"INSERT INTO categorias (nombre) VALUES ('Bebidas')",
			"INSERT INTO categorias (nombre) VALUES ('Cafeteria')",
			"INSERT INTO categorias (nombre) VALUES ('Ensaladas')",
			"INSERT INTO categorias (nombre) VALUES ('Pastas')",
			"INSERT INTO categorias (nombre) VALUES ('Carnes')",
			"INSERT INTO categorias (nombre) VALUES ('Pescados')",
			"INSERT INTO categorias (nombre) VALUES ('Postres')"
	};
	
	String[] insertIntoProductos = {
			// Categoria Bebidas
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Coca Cola', 1.60, 'Bebida refrescante con gas con sabor a cola','..\\\\res\\\\img\\\\imgCocaCola.jpg', 'imgcocacola', 1)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Fanta Naranja', 1.60, 'Bebida refrescante con gas con sabor a naranja','..\\\\res\\\\img\\\\imgFantaNaranja.jpg', 'imgfantanaranja', 1)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Agua mineral', 1.50, 'Agua mineral de los lagos de Galicia','..\\\\res\\\\img\\\\imgAguaMineral.jpg', 'imgaguamineral', 1)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Cerveza', 1.70, 'Cerveza proviniente de Granada con baja fermentación.','..\\\\res\\\\img\\\\imgAlhambra.jpg', 'imgalhambra', 1)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Nestea al limón', 1.75, 'Bebida refrescante con sabor a té al limón.','..\\\\res\\\\img\\\\imgNestea.jpg', 'imgnestea', 1)",
			
			// Categoria Cafeteria
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Café solo', 1.25, 'Taza pequeña con una carga de café sin leche.','..\\\\res\\\\img\\\\imgCafeSolo.jpg', 'imgcafesolo', 2)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Café cortado', 1.35, 'Taza mediana con una carga de café con un chorro de leche.','..\\\\res\\\\img\\\\imgCafeCortado.jpg', 'imgcafecortado', 2)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Café con leche', 1.50, 'Taza grande con doble carga de café y un chorro de leche.','..\\\\res\\\\img\\\\imgCafeConLeche.jpg', 'imgcafeconleche', 2)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Café americano', 1.40, 'Taza mediana con doble carga de café sin leche.','..\\\\res\\\\img\\\\imgCafeAmericano.jpg', 'imgcafeamericano', 2)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Carajillo de Baileys', 1.60, 'Taza mediana con una carga de café con un chorro de licor Baileys.','..\\\\res\\\\img\\\\imgCarajillo.jpg', 'imgcarajillo', 2)",
			
			// Categoria Ensaladas
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Ensalada Cesar', 5.95, 'Ensalada con pollo, picatostes, salsa César y frutos secos','..\\\\res\\\\img\\\\imgEnsaladaCesar.jpg', 'imgensaladacesar', 3)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Ensalada de la huerta', 4.95, 'Ensalada con mezclum, brotes, tomate, pepino y aliñada con aceite de oliva y vinagre','..\\\\res\\\\img\\\\imgEnsaladaHuerta.jpg', 'imgensaladahuerta', 3)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Ensalada tropical', 5.49, 'Ensalada con mezclum, piña y queso.','..\\\\res\\\\img\\\\imgEnsaladaTropical.jpg', 'imgensaladatropical', 3)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Ensalada del mar', 6.95, 'Ensalada con mezclum, gambas y tomates.','..\\\\res\\\\img\\\\imgEnsaladaMar.jpg', 'imgensaladamar', 3)",
			
			// Categoria Pastas
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Espaguetis a la carbonara', 5.95, 'Espaguettis con nata, bacon, huevo y nuez moscada','..\\\\res\\\\img\\\\imgEspaguetiCarbonara.jpg', 'imgespagueticarbonara', 4)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Macarrones a la boloñesa', 5.95, 'Macarrones con tomate y carne picada mixta de ternera y cerdo y queso parmesano','..\\\\res\\\\img\\\\imgMacarronesBolonesa.jpg', 'imgmacarronesbolonesa', 4)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Pasta con frutos de mar', 7.49, 'Pasta a elección con gambas, mejillones y escamarlÃ¡n.','..\\\\res\\\\img\\\\imgPastaConMarisco.jpg', 'imgpastaconmarisco', 4)",
			
			// Categoria Carnes
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Solomillo de cerdo ibérico a la pimienta', 12.95, 'Solomillo de cerdo ibérico con salsa de pimienta verde','..\\\\res\\\\img\\\\imgSolomilloCerdo.jpg', 'imgsolomillocerdo', 5)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Abanico ibérico', 11.95, 'Abanico de cerdo ibérico con patatas de guarnaición.','..\\\\res\\\\img\\\\imgAbanicoCerdo.jpg', 'imgabanicocerdo', 5)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Entrecotte de ternera wagyu a la brasa', 23.49, 'Entrecotte de ternera WAGYU a la brasa con guarnición de patatas al caliu.','..\\\\res\\\\img\\\\imgEntrecotteBrasa.jpg', 'imgentrecottebrasa', 5)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Solomillo de ternera a la brasa ahumado', 23.95, 'Solomillo de ternera a la brasa cocinado durante horas con sabor ahumado.','..\\\\res\\\\img\\\\imgSolomilloTernera.jpg', 'imgsolomilloternera', 5)",
			
			// Categoria Pescados
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Lubina a la plancha con limón y ajetes', 16.49, 'Lubina hecha a la plancha con jugo de limón y ajetes braseados','..\\\\res\\\\img\\\\imgLubinaAjetes.jpg', 'imglubinaajetes', 6)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Emperador con salsa verde', 21.99, 'Emperador hecho a la plancha con deliciosa salsa verde hecha a partir de vino blanco, ajo, limón, perejil y cebolla.','..\\\\res\\\\img\\\\imgEmperador.jpg', 'imgemperador', 6)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Merluza a la gallega', 15.95, 'Merluza con salsa gallega de tomate y base de patatas al horno.','..\\\\res\\\\img\\\\imgMerluzaGallega.jpg', 'imgmerluzagallega', 6)",
			
			// Categoria Postres
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Coulant de chocolate', 7.49, 'Pequeño pastel de chocolate relleno de foundant de chocolate suizo negro.','..\\\\res\\\\img\\\\imgCoulant.jpg', 'imgcoulant', 7)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Tarta de queso New York', 5.49, 'Tarta de queso pintada con mermelada de frambuesa y base de galleta caramelizada.','..\\\\res\\\\img\\\\imgTartaNY.jpg', 'imgtartany', 7)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, id_categoria) VALUES ('Crema catalana', 4.95, 'Crema catalana casera con azúcar cremat.','..\\\\res\\\\img\\\\imgCremaCatalana.jpg', 'imgcremacatalana', 7)"
	};
			
}
