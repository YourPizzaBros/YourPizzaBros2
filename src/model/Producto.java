package model;

public class Producto {
	private int c�digoProducto;
	private String nombre;
	private Double precio;
	private String descripci�n;
	private String tamano;
	
	public Producto (int c�digoProducto, String nombre, Double precio, String descripci�n, String tamano) {
		this.c�digoProducto = c�digoProducto;
		this.nombre = nombre;
		this.precio = precio;
		this.descripci�n = descripci�n;
		this.tamano= tamano;
	}
	public int getC�digoProducto() {
		return c�digoProducto;
	}
	public void setC�digoProducto(int c�digoProducto) {
		this.c�digoProducto = c�digoProducto;
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
		return "Producto [c�digoProducto=" + c�digoProducto + ", nombre=" + nombre + ", precio=" + precio
				+ ", descripci�n=" + descripci�n +  ", tama�o =" + tamano + "]";
	}
	
	
}
