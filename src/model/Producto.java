package model;

public class Producto {
	private int codigoProducto;
	private String nombre;
	private Double precio;
	private String descripci�n;
	private String tamano;
	private int cantidadTotal;   //anadiendo esto ya que no estabas
	
	public Producto (int codigoProducto, String nombre, Double precio, String descripci�n, String tamano, int cantidadT) {
		this.codigoProducto = codigoProducto;
		this.nombre = nombre;
		this.precio = precio;
		this.descripci�n = descripci�n;
		this.tamano= tamano;
		this.cantidadTotal= cantidadT;
	}
	public int getCodigoProducto() {
		return codigoProducto;
	}
	public int getCantidadTotal() {
		return cantidadTotal;
	}
	public void setCantidadTotal(int cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
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
		return "Producto [c�digoProducto=" + codigoProducto + ", nombre=" + nombre + ", precio=" + precio
				+ ", descripci�n=" + descripci�n +  ", tama�o =" + tamano + "]";
	}
	
	
}
