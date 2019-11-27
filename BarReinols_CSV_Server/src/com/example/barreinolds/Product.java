package com.example.barreinolds;

import java.io.Serializable;

/*
 * Esta es la clase que define al producto en si mismo.
 */
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	// Atributos de un producto.
	private int id;
	private String name;
	private String description;
	private String price;
	private int cantidad;
	private String image_desktop;
	private String image_movil;
	private boolean served;
	
	/*
	 * Constructor vacio en el que se inicializa
	 * la cantidad en 0.
	 */
	public Product() {
		cantidad = 0;
		served = false;
	}

	public Product(int id, String name, String description, String price, int cantidad, String image_desktop, String image_movil) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.cantidad = cantidad;
		this.image_desktop = image_desktop;
		this.image_movil = image_movil;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getImage_Desktop() {
		return image_desktop;
	}

	public void setImage_Desktop(String image_desktop) {
		this.image_desktop = image_desktop;
	}

	public void setImage_movil(String image_movil) {
		this.image_movil = image_movil;
	}
	
	public String getImage_movil() {
		return image_movil;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
    public boolean isServed() { return served; }
    
    public void setServed(boolean served) { this.served = served; }
	
	

	/*
	 * Getters y setters
	 */

	
}
