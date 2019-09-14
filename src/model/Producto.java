package model;

public class Producto {
	private int c�digoProducto;
	private String nombre;
	private Double precio;
	private String descripci�n;
	private int c�digoCategor�a;
	public Producto(int c�digoProducto, String nombre, Double precio, String descripci�n, int c�digoCategor�a) {
		this.c�digoProducto = c�digoProducto;
		this.nombre = nombre;
		this.precio = precio;
		this.descripci�n = descripci�n;
		this.c�digoCategor�a = c�digoCategor�a;
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
	public int getC�digoCategor�a() {
		return c�digoCategor�a;
	}
	public void setC�digoCategor�a(int c�digoCategor�a) {
		this.c�digoCategor�a = c�digoCategor�a;
	}
	@Override
	public String toString() {
		return "Producto [c�digoProducto=" + c�digoProducto + ", nombre=" + nombre + ", precio=" + precio
				+ ", descripci�n=" + descripci�n + ", c�digoCategor�a=" + c�digoCategor�a + "]";
	}
	
	
}
