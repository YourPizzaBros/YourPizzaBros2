package model;

public class Proveedor {


	
	private int NITProveedor;
	private String NombreP;
	
	
	private String Telefono;
	private String Direccion;	
	
	
	
	
	public Proveedor (int codigoC, String nombreC,  String TelC,String direccionC) {
		this.NITProveedor = codigoC;
		this.NombreP = nombreC;
	
		this.Telefono= TelC;
		this.Direccion= direccionC;
		
	}




	public int getNITProveedor() {
		return NITProveedor;
	}




	public void setNITProveedor(int nITProveedor) {
		NITProveedor = nITProveedor;
	}




	public String getNombreP() {
		return NombreP;
	}




	public void setNombreP(String nombreP) {
		NombreP = nombreP;
	}




	public String getTelefono() {
		return Telefono;
	}




	public void setTelefono(String telefono) {
		Telefono = telefono;
	}




	public String getDireccion() {
		return Direccion;
	}




	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	
}
	