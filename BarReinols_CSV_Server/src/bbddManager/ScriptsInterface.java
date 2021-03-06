package bbddManager;

import java.io.File;

/*
 * NO HACER CONTROL + F!
 */

public interface ScriptsInterface {
	
	String rutaImgFolder = "res/img/";
	
	String createCategorias = "CREATE TABLE categorias (" + 
			"	id_categoria INT NOT NULL AUTO_INCREMENT," + 
			"   nombre VARCHAR(50)," + 
			"	image_blob LONGBLOB," +
			"   PRIMARY KEY (id_categoria)" + 
			")";
	
	String createCamareros = "CREATE TABLE camareros (" + 
			"	id_camarero INT NOT NULL AUTO_INCREMENT," + 
			"   nombre VARCHAR(50)," + 
			"   username VARCHAR(50)," +
			"   password VARCHAR(50)," + 
			"	image_blob LONGBLOB," +
			"   PRIMARY KEY (id_camarero)" + 
			")";
	
	String createProductos = "CREATE TABLE productos (" + 
			"	id_producto INT NOT NULL AUTO_INCREMENT," + 
			"   nombre VARCHAR(50)," + 
			"   precio_producto FLOAT(6,2)," + 
			"   descripcion VARCHAR(200)," + 
			"   imagen VARCHAR(100)," + 
			"   image_movil VARCHAR(100)," + 
			"	image_blob LONGBLOB, " +
			"   id_categoria INT NOT NULL," + 
			"   PRIMARY KEY (id_producto)," + 
			"   CONSTRAINT id_categoria FOREIGN KEY (id_categoria) REFERENCES categorias (id_categoria)" + 
			")";
	
	String createComandas = "CREATE TABLE comandas (" + 
			"	mesa INT NOT NULL," +
			"   id_camarero INT NOT NULL," + 
			"   datetime TIMESTAMP NOT NULL," + 
			"   PRIMARY KEY (mesa)," + 
			"   CONSTRAINT id_camarero FOREIGN KEY (id_camarero) REFERENCES camareros (id_camarero)" + 
			")";
	
	String createDetailsComanda = "CREATE TABLE details_comandas (" + 
			"	mesa INT NOT NULL," + 
			"   id_producto INT NOT NULL," + 
			"   cantidad_producto INT NOT NULL," + 
			"   precio_producto FLOAT(6,2) NOT NULL," + 
			"	servido BOOLEAN," +
			"   CONSTRAINT mesa_fk FOREIGN KEY (mesa) REFERENCES comandas (mesa)," + 
			"   CONSTRAINT id_producto_fk FOREIGN KEY (id_producto) REFERENCES productos (id_producto)" +  
			")";
	
	
	String createFacturas = "CREATE TABLE facturas (" + 
			"	id_factura INT NOT NULL AUTO_INCREMENT," +
			"	mesa INT NOT NULL," +
			"	id_camarero INT NOT NULL, " + 
			"	datetime TIMESTAMP NOT NULL," + 
			"	precio_final_noiva FLOAT(6,2)," + 
			"	precio_final_iva FLOAT(6,2)," + 
			"   PRIMARY KEY (id_factura)" + 
			")";
	
	String createMesaConfig = "CREATE TABLE mesa_maestra_configuracion (" + 
			"	ip VARCHAR(20)," + 
			"   nombre_pda VARCHAR(50)," + 
			"   cantidad_mesa INT" + 
			")";
	
	String createDetailsFacturas = "CREATE TABLE details_facturas (" + 
			"   id_factura INT NOT NULL," + 
			"   nombre_producto VARCHAR(50) NOT NULL," + 
			"   precio_producto FLOAT(6,2) NOT NULL," + 
			"   cantidad INT NOT NULL," + 
			"   CONSTRAINT id_factura_fk FOREIGN KEY (id_factura) REFERENCES facturas (id_factura)" + 
			")";
	
	File[] camareroImages = {
			new File(rutaImgFolder + "ecabezue.jpg"),
			new File(rutaImgFolder + "dsalas.jpg"),
			new File(rutaImgFolder + "jvalle.jpg"),
			new File(rutaImgFolder + "dvaquer.jpg")
	};
	
	String[] insertIntoCamareros = {
			"INSERT INTO camareros(nombre, username, password, image_blob) VALUES ('Erik Cabezuelo', 'ecabezue', MD5('123456'), ?)",
			"INSERT INTO camareros(nombre, username, password, image_blob) VALUES ('David Salas', 'dsalas', MD5('123456'), ?)",
			"INSERT INTO camareros(nombre, username, password, image_blob) VALUES ('Jonatan Valle', 'jvalle', MD5('123456'), ?)",
			"INSERT INTO camareros(nombre, username, password, image_blob) VALUES ('Daniel Vaquer', 'dvaquer', MD5('123456'), ?)"
	};
	
	File[] categoryImages = {
			new File(rutaImgFolder + "catbebidas.jpg"),
			new File(rutaImgFolder + "catcafes.png"), 
			new File(rutaImgFolder + "catensaladas.jpg"), 
			new File(rutaImgFolder + "catpastas.jpg"), 
			new File(rutaImgFolder + "catcarnes.jpg"), 
			new File(rutaImgFolder + "catpescados.jpg"),
			new File(rutaImgFolder + "catpostres.jpg")
	};
	
	String[] insertIntoCategorias = {
			"INSERT INTO categorias (nombre, image_blob) VALUES ('Bebidas', ?)",
			"INSERT INTO categorias (nombre, image_blob) VALUES ('Cafeteria', ?)",
			"INSERT INTO categorias (nombre, image_blob) VALUES ('Ensaladas', ?)",
			"INSERT INTO categorias (nombre, image_blob) VALUES ('Pastas', ?)",
			"INSERT INTO categorias (nombre, image_blob) VALUES ('Carnes', ?)",
			"INSERT INTO categorias (nombre, image_blob) VALUES ('Pescados', ?)",
			"INSERT INTO categorias (nombre, image_blob) VALUES ('Postres', ?)"
	};
	
	File[] productImages = {
			
		// Fotos de bebidas
		new File(rutaImgFolder + "imgCocaCola.jpg"),
		new File(rutaImgFolder + "imgFantaNaranja.jpg"),
		new File(rutaImgFolder + "imgAguaMineral.jpg"),
		new File(rutaImgFolder + "imgAlhambra.jpg"),
		new File(rutaImgFolder + "imgNestea.jpg"),
		
		// Fotos de cafes
		new File(rutaImgFolder + "imgCafeSolo.jpg"),
		new File(rutaImgFolder + "imgCafeCortado.jpg"),
		new File(rutaImgFolder + "imgCafeConLeche.jpg"),
		new File(rutaImgFolder + "imgCafeAmericano.jpg"),
		new File(rutaImgFolder + "imgCarajillo.jpg"),
		
		// Fotos de ensaladas 
		new File(rutaImgFolder + "imgEnsaladaCesar.jpg"),
		new File(rutaImgFolder + "imgEnsaladaHuerta.jpg"),
		new File(rutaImgFolder + "imgEnsaladaTropical.jpg"),
		new File(rutaImgFolder + "imgEnsaladaMar.jpg"),
		
		// Fotos de pastas
		new File(rutaImgFolder + "imgEspaguetiCarbonara.jpg"),
		new File(rutaImgFolder + "imgMacarronesBolonesa.jpg"),
		new File(rutaImgFolder + "imgPastaConMarisco.jpg"),
		
		// Fotos de carnes
		new File(rutaImgFolder + "imgSolomilloCerdo.jpg"),
		new File(rutaImgFolder + "imgAbanicoCerdo.jpg"),
		new File(rutaImgFolder + "imgEntrecotteBrasa.jpg"),
		new File(rutaImgFolder + "imgSolomilloTernera.jpg"),
		
		// Fotos de pescados
		new File(rutaImgFolder + "imgLubinaAjetes.jpg"),
		new File(rutaImgFolder + "imgEmperador.jpg"),
		new File(rutaImgFolder + "imgMerluzaGallega.jpg"),
		
		// Fotos de postres
		new File(rutaImgFolder + "imgCoulant.jpg"),
		new File(rutaImgFolder + "imgTartaNY.jpg"),
		new File(rutaImgFolder + "imgCremaCatalana.jpg")
	};
	
	
	String[] insertIntoProductos = {
			// Categoria Bebidas
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Coca Cola', 1.60, 'Bebida refrescante con gas con sabor a cola','..\\\\res\\\\img\\\\imgCocaCola.jpg', 'imgcocacola', ?, 1)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Fanta Naranja', 1.60, 'Bebida refrescante con gas con sabor a naranja','..\\\\res\\\\img\\\\imgFantaNaranja.jpg', 'imgfantanaranja', ?, 1)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Agua mineral', 1.50, 'Agua mineral de los lagos de Galicia','..\\\\res\\\\img\\\\imgAguaMineral.jpg', 'imgaguamineral', ?, 1)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Cerveza', 1.70, 'Cerveza proviniente de Granada con baja fermentaci�n.','..\\\\res\\\\img\\\\imgAlhambra.jpg', 'imgalhambra', ?, 1)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Nestea al lim�n', 1.75, 'Bebida refrescante con sabor a t� al lim�n.','..\\\\res\\\\img\\\\imgNestea.jpg', 'imgnestea', ?, 1)",
			
			// Categoria Cafeteria
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Caf� solo', 1.25, 'Taza peque�a con una carga de caf� sin leche.','..\\\\res\\\\img\\\\imgCafeSolo.jpg', 'imgcafesolo', ?, 2)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Caf� cortado', 1.35, 'Taza mediana con una carga de caf� con un chorro de leche.','..\\\\res\\\\img\\\\imgCafeCortado.jpg', 'imgcafecortado', ?, 2)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Caf� con leche', 1.50, 'Taza grande con doble carga de caf� y un chorro de leche.','..\\\\res\\\\img\\\\imgCafeConLeche.jpg', 'imgcafeconleche', ?, 2)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Caf� americano', 1.40, 'Taza mediana con doble carga de caf� sin leche.','..\\\\res\\\\img\\\\imgCafeAmericano.jpg', 'imgcafeamericano', ?, 2)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Carajillo de Baileys', 1.60, 'Taza mediana con una carga de caf� con un chorro de licor Baileys.','..\\\\res\\\\img\\\\imgCarajillo.jpg', 'imgcarajillo', ?, 2)",
			
			// Categoria Ensaladas
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Ensalada Cesar', 5.95, 'Ensalada con pollo, picatostes, salsa C�sar y frutos secos','..\\\\res\\\\img\\\\imgEnsaladaCesar.jpg', 'imgensaladacesar', ?, 3)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Ensalada de la huerta', 4.95, 'Ensalada con mezclum, brotes, tomate, pepino y ali�ada con aceite de oliva y vinagre','..\\\\res\\\\img\\\\imgEnsaladaHuerta.jpg', 'imgensaladahuerta', ?, 3)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Ensalada tropical', 5.49, 'Ensalada con mezclum, pi�a y queso.','..\\\\res\\\\img\\\\imgEnsaladaTropical.jpg', 'imgensaladatropical', ?, 3)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Ensalada del mar', 6.95, 'Ensalada con mezclum, gambas y tomates.','..\\\\res\\\\img\\\\imgEnsaladaMar.jpg', 'imgensaladamar', ?, 3)",
			
			// Categoria Pastas
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Espaguetis a la carbonara', 5.95, 'Espaguettis con nata, bacon, huevo y nuez moscada','..\\\\res\\\\img\\\\imgEspaguetiCarbonara.jpg', 'imgespagueticarbonara', ?, 4)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Macarrones a la bolo�esa', 5.95, 'Macarrones con tomate y carne picada mixta de ternera y cerdo y queso parmesano','..\\\\res\\\\img\\\\imgMacarronesBolonesa.jpg', 'imgmacarronesbolonesa', ?, 4)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Pasta con frutos de mar', 7.49, 'Pasta a elecci�n con gambas, mejillones y escamarlán.','..\\\\res\\\\img\\\\imgPastaConMarisco.jpg', 'imgpastaconmarisco', ?, 4)",
			
			// Categoria Carnes
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Solomillo de cerdo ib�rico a la pimienta', 12.95, 'Solomillo de cerdo ib�rico con salsa de pimienta verde','..\\\\res\\\\img\\\\imgSolomilloCerdo.jpg', 'imgsolomillocerdo', ?, 5)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Abanico ib�rico', 11.95, 'Abanico de cerdo ib�rico con patatas de guarnaici�n.','..\\\\res\\\\img\\\\imgAbanicoCerdo.jpg', 'imgabanicocerdo', ?, 5)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Entrecotte de ternera wagyu a la brasa', 23.49, 'Entrecotte de ternera WAGYU a la brasa con guarnici�n de patatas al caliu.','..\\\\res\\\\img\\\\imgEntrecotteBrasa.jpg', 'imgentrecottebrasa', ?, 5)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Solomillo de ternera a la brasa ahumado', 23.95, 'Solomillo de ternera a la brasa cocinado durante horas con sabor ahumado.','..\\\\res\\\\img\\\\imgSolomilloTernera.jpg', 'imgsolomilloternera', ?, 5)",
			
			// Categoria Pescados
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Lubina a la plancha con lim�n y ajetes', 16.49, 'Lubina hecha a la plancha con jugo de lim�n y ajetes braseados','..\\\\res\\\\img\\\\imgLubinaAjetes.jpg', 'imglubinaajetes', ?, 6)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Emperador con salsa verde', 21.99, 'Emperador hecho a la plancha con deliciosa salsa verde hecha a partir de vino blanco, ajo, lim�n, perejil y cebolla.','..\\\\res\\\\img\\\\imgEmperador.jpg', 'imgemperador', ?, 6)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Merluza a la gallega', 15.95, 'Merluza con salsa gallega de tomate y base de patatas al horno.','..\\\\res\\\\img\\\\imgMerluzaGallega.jpg', 'imgmerluzagallega', ?, 6)",
			
			// Categoria Postres
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Coulant de chocolate', 7.49, 'Peque�o pastel de chocolate relleno de foundant de chocolate suizo negro.','..\\\\res\\\\img\\\\imgCoulant.jpg', 'imgcoulant', ?, 7)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Tarta de queso New York', 5.49, 'Tarta de queso pintada con mermelada de frambuesa y base de galleta caramelizada.','..\\\\res\\\\img\\\\imgTartaNY.jpg', 'imgtartany', ?, 7)",
			"INSERT INTO productos (nombre, precio_producto, descripcion, imagen, image_movil, image_blob, id_categoria) VALUES ('Crema catalana', 4.95, 'Crema catalana casera con az�car cremat.','..\\\\res\\\\img\\\\imgCremaCatalana.jpg', 'imgcremacatalana', ?, 7)"
	};
	
	String createProcedureInsertEmployee = "CREATE PROCEDURE `insert_employee` ("+
			" IN nameC VARCHAR(50)," + 
			" IN usernameC VARCHAR(50)," +
			" IN passwordC VARCHAR(50)," + 
			" IN image_blobC longblob)"  +
			" BEGIN" +
			" INSERT INTO camareros (" +
			" nombre," +
			" username," +
			" password," +
			" image_blob)" +
			" VALUES (" +
			" nameC," +
			" usernameC," +
			" MD5(passwordC)," +
			" image_blobC); " +
			"END";
}
