package bar;

/*
 * Esta es la clase que define al producto en si mismo.
 */
public class Product {

	// Atributos de un producto.
	String id;
	String name;
	String price;
	String rutaImagen;
	int quantity;
	
	/*
	 * Constructor vacio en el que se inicializa
	 * la cantidad en 0.
	 */
	public Product() {
		quantity = 0;
	}
	
	/*
	 * Constructor que rellena todos los datos de producto
	 */
	public Product(String id, String name, String price, String rutaImagen, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.rutaImagen = rutaImagen;
		this.quantity = quantity;
	}



	/*
	 * Getters y setters
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
