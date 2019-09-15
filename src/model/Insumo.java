package model;

public class Insumo {
	private int codigoInsumo;
	private String nombre;
	private Double precio;
	private String descripci�n;
	private String tamano;
	
	public Insumo (int codigoProducto, String nombre, Double precio, String descripci�n, String tamano) {
		this.codigoInsumo = codigoInsumo;
		this.nombre = nombre;
		this.precio = precio;
		this.descripci�n = descripci�n;
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
	public String getDescripci�n() {
		return descripci�n;
	}
	public void setDescripci�n(String descripci�n) {
		this.descripci�n = descripci�n;
	}
	
	public String getTamano() {
		return tamano;
	}
	public void setTamano(String tamano) {
		this.tamano = tamano;
	}
	@Override
	public String toString() {
		return "Producto [c�digoProducto=" + codigoInsumo + ", nombre=" + nombre + ", precio=" + precio
				+ ", descripci�n=" + descripci�n +  ", tama�o =" + tamano + "]";
	}
	
	
}
