package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;


public class AdmiFX {
	private Connection connection;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;

	}
	@FXML
	private void initialize() {
				
	}
	
	@FXML
	private Button btnVerificar;
	@FXML
	private Button btnComprar;
	@FXML
	private Button btnRegistrar;
	@FXML
	private void btnVerificar_Action() 
	{
		FormsOperations formsOperations = new FormsOperations();
		FXMLLoader fXMLLoader = formsOperations.OpenForm ("Productos en Almacén" , "/view/MostrarFX.fxml");
		MostrarFX mostrarFX = fXMLLoader.getController();
		mostrarFX.setConnection(connection);
		
	}
	@FXML
	private void btnComprar_Action() 
	{
		FormsOperations formsOperations = new FormsOperations();
		FXMLLoader fXMLLoader = formsOperations.OpenForm ("Comprar Producto" , "/view/ComprarFX.fxml");
		ComprarFX comprarFX = fXMLLoader.getController();
		comprarFX.setConnection(connection);
		
	}
	@FXML
	private void btnRegistrar_Action() 
	{
		FormsOperations formsOperations = new FormsOperations();
		FXMLLoader fXMLLoader = formsOperations.OpenForm ("Registrar Proveedores" , "/view/RegistroPFX.fxml");
		RegistroPFX RegistroPFX = fXMLLoader.getController();
		RegistroPFX.setConnection(connection);
		
	}
	
	
}
