package model;

public class Insumo {
	private int codigoInsumo;
	private String nombre;
	
	private String descripci�n;
	
	private int cantidad;
	
	public Insumo (int codigoInsumo, String nombre, String descripci�n, int Cantidad) {
		this.codigoInsumo = codigoInsumo;
		this.nombre = nombre;
	this.cantidad = Cantidad;
		this.descripci�n = descripci�n;
	
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
	public String getDescripci�n() {
		return descripci�n;
	}
	public void setDescripci�n(String descripci�n) {
		this.descripci�n = descripci�n;
	}
	
	
	@Override
	public String toString() {
		return "Insumo [c�digoInsumo=" + codigoInsumo + ", nombre=" + nombre + /*", precio=" + precio
				+ ,*/ "descripci�n=" + descripci�n  + "]";
	}
	
	
}
