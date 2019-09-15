package model;

public class Producto {
	private int codigoProducto;
	private String nombre;
	private Double precio;
	private String descripción;
	private String tamano;
	
	public Producto (int codigoProducto, String nombre, Double precio, String descripción, String tamano) {
		this.codigoProducto = codigoProducto;
		this.nombre = nombre;
		this.precio = precio;
		this.descripción = descripción;
		this.tamano= tamano;
	}
	public int getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
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
		return "Producto [códigoProducto=" + codigoProducto + ", nombre=" + nombre + ", precio=" + precio
				+ ", descripción=" + descripción +  ", tamaño =" + tamano + "]";
	}
	
	
}
