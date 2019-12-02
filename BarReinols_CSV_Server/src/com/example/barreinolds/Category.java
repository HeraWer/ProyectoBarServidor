package com.example.barreinolds;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
/*
 * Esta es la clase de las Categorias, con los siguientes parametros:
 * id: String que es el ID de la categoria
 * nombre: String que es el nombre de la categoria (o titulo)
 * aLProducts: ArrayList de productos que contiene la categoria
 */
public class Category implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Atributos de categoria
	private int id;
	private String nCategory;
	private ArrayList<Product> listProducts;
	private byte[] imgBlob;
	
	public Category() {
		listProducts = new ArrayList<Product>();
	}
	
	public Category(int id, String nombre, ArrayList<Product> aLProducts) {
		super();
		this.id = id;
		this.nCategory = nombre;
		this.listProducts = aLProducts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nCategory;
	}

	public void setNombre(String nombre) {
		this.nCategory = nombre;
	}

	public ArrayList<Product> getaLProducts() {
		return listProducts;
	}

	public void setaLProducts(ArrayList<Product> aLProducts) {
		this.listProducts = aLProducts;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public byte[] getImgBlob() {
		return imgBlob;
	}

	public void setImgBlob(byte[] imgBlob) {
		this.imgBlob = imgBlob;
	}
	
	
	
	
	
	
	
}