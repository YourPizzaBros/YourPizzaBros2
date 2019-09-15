package model;

public class Insumo {
	private int codigoInsumo;
	private String nombre;
	private Double precio;
	private String descripción;
	private String tamano;
	
	public Insumo (int codigoProducto, String nombre, Double precio, String descripción, String tamano) {
		this.codigoInsumo = codigoInsumo;
		this.nombre = nombre;
		this.precio = precio;
		this.descripción = descripción;
		this.tamano= tamano;
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
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public String getDescripción() {
		return descripción;
	}
	public void setDescripción(String descripción) {
		this.descripción = descripción;
	}
	
	public String getTamano() {
		return tamano;
	}
	public void setTamano(String tamano) {
		this.tamano = tamano;
	}
	@Override
	public String toString() {
		return "Producto [códigoProducto=" + codigoInsumo + ", nombre=" + nombre + ", precio=" + precio
				+ ", descripción=" + descripción +  ", tamaño =" + tamano + "]";
	}
	
	
}
