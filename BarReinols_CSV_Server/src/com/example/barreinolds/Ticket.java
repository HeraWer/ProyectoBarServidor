package com.example.barreinolds;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

/*
 * Esta es la clase de Tickets, o comandas.
 * Tiene un arraylist de productos y un integer del
 * numero de mesa al que pertenece.
 * 
 * Implementa la clase Serializable
 * para poder pasarsela al cliente
 */
public class Ticket implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Product> productosComanda;
	private int mesa;
	private Camarero camarero;
	private Timestamp datetime;
	
	public Ticket() {
		productosComanda = new ArrayList<Product>();
	}
	
	/*
	 * Constructor en el que se le pasa 
	 * el numero de mesa del ticket
	 */
	public Ticket(int mesa) {
		productosComanda = new ArrayList<Product>();
		this.mesa = mesa;
	}
	
	public Ticket(ArrayList<Product> productosComanda, int mesa) {
		this.productosComanda = productosComanda;
		this.mesa = mesa;
	}
	
	public Ticket(ArrayList<Product> productosComanda, int mesa, Camarero camarero, Timestamp datetime) {
		super();
		this.productosComanda = productosComanda;
		this.mesa = mesa;
		this.camarero = camarero;
		this.datetime = datetime;
	}

	public Ticket(int mesa, Camarero camarero, Timestamp datetime) {
		super();
		this.mesa = mesa;
		this.camarero = camarero;
		this.datetime = datetime;
		this.productosComanda = new ArrayList<Product>();
	}

	/*
	 * metodo que devuelve el numero de mesa de la comanda
	 */
	public int getMesa() {
		return this.mesa;
	}
	
	public void setMesa(ArrayList<Product> productosComanda) {
		this.productosComanda = productosComanda;
	}
	
	/*
	 * Metodo que devuelve el ArrayList de productos
	 */
	public ArrayList<Product> getProductosComanda() {
		return this.productosComanda;
	}
	
	public void setProductosComanda(ArrayList<Product> productosComanda) {
		this.productosComanda = productosComanda;
	}

	public Camarero getCamarero() {
		return camarero;
	}

	public void setCamarero(Camarero camarero) {
		this.camarero = camarero;
	}

	public Timestamp getDatetime() {
		return datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public void setMesa(int mesa) {
		this.mesa = mesa;
	}
	
	
	
	
}
