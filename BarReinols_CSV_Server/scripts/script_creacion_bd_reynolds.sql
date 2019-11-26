DROP TABLE mesa_maestra_configuracion;
DROP TABLE details_facturas;
DROP TABLE facturas;
DROP TABLE details_comandas;
DROP TABLE comandas;
DROP TABLE productos;
DROP TABLE camareros;
DROP TABLE categorias;


CREATE TABLE categorias (
	id_categoria INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50),
    PRIMARY KEY (id_categoria)
);

CREATE TABLE camareros (
	id_camarero INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50),
    username VARCHAR(50),
    PRIMARY KEY (id_camarero)
);

CREATE TABLE productos (
	id_producto INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50),
    precio_producto FLOAT(4,2),
    descripcion VARCHAR(200),
    imagen VARCHAR(100),
    id_categoria INT NOT NULL,
    PRIMARY KEY (id_producto),
    CONSTRAINT id_categoria FOREIGN KEY (id_categoria) REFERENCES categorias (id_categoria)
);

CREATE TABLE comandas (
	id_comanda INT NOT NULL AUTO_INCREMENT,
    id_camarero INT NOT NULL,
    datetime TIMESTAMP NOT NULL,
    PRIMARY KEY (id_comanda),
    CONSTRAINT id_camarero FOREIGN KEY (id_camarero) REFERENCES camareros (id_camarero)
);

CREATE TABLE details_comandas (
	id_details_comanda INT NOT NULL AUTO_INCREMENT,
	id_comanda INT NOT NULL,
    id_producto INT NOT NULL,
    precio_producto FLOAT(4,2) NOT NULL
    PRIMARY KEY (id_details_comanda),
    CONSTRAINT id_comanda_fk FOREIGN KEY (id_comanda) REFERENCES comandas (id_comanda),
    CONSTRAINT id_producto_fk FOREIGN KEY (id_producto) REFERENCES productos (id_producto)
);

CREATE TABLE facturas (
	id_factura INT NOT NULL AUTO_INCREMENT,
	datetime TIMESTAMP NOT NULL,
	precio_final FLOAT(5,2),
    PRIMARY KEY (id_factura)
);

CREATE TABLE mesa_maestra_configuracion (
	ip VARCHAR(20),
    nombre_pda VARCHAR(50),
    cantidad_mesa INT
);

CREATE TABLE details_facturas (
	id_details_factura INT NOT NULL AUTO_INCREMENT,
    nombre_pruducto VARCHAR(50),
    precio_producto INT,
    cantidad INT,
    id_factura INT NOT NULL,
    PRIMARY KEY (id_details_factura),
    CONSTRAINT id_factura_fk FOREIGN KEY (id_factura) REFERENCES facturas (id_factura)
);

DESC prod_com_asoc;
