package model;

public class Insumo {
	private int codigoInsumo;
	private String nombre;
	
	private String descripción;
	
	private int cantidad;
	
	public Insumo (int codigoInsumo, String nombre, String descripción, int Cantidad) {
		this.codigoInsumo = codigoInsumo;
		this.nombre = nombre;
	this.cantidad = Cantidad;
		this.descripción = descripción;
	
	}
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCodigoInsumo() {
		return codigoInsumo;
	}

	public void setCodigoInsumo(int codigoInsumo) {
		this.codigoInsumo = codigoInsumo;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/*public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}*/
	public String getDescripción() {
		return descripción;
	}
	public void setDescripción(String descripción) {
		this.descripción = descripción;
	}
	
	
	@Override
	public String toString() {
		return "Insumo [códigoInsumo=" + codigoInsumo + ", nombre=" + nombre + /*", precio=" + precio
				+ ,*/ "descripción=" + descripción  + "]";
	}
	
	
}
