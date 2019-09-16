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
	private Button btnVerInsumos;
	@FXML
	private Button btnComprarInsumos;
	@FXML
	private Button btnRegistrarProveedor;
	
	@FXML
	private Button btnRegistraInsumos;
	@FXML
	private Button btnRegistrarReseta;
	
	@FXML
	private Button btnRegistrarMesa;
	@FXML
	private Button btnRegistrarProductos;
	
	
	@FXML
	private void btnVerInsumos_Action() 
	{
		FormsOperations formsOperations = new FormsOperations();
		FXMLLoader fXMLLoader = formsOperations.OpenForm ("Insumos en Almacén" , "/view/MostrarFX.fxml");
		MostrarFX mostrarFX = fXMLLoader.getController();
		mostrarFX.setConnection(connection);
		
	}
	@FXML
	private void btnComprarInsumos_Action() 
	{
/*		FormsOperations formsOperations = new FormsOperations();
		FXMLLoader fXMLLoader = formsOperations.OpenForm ("Comprar Insumo" , "/view/CompraFX.fxml");
		CompraFX compraFX = fXMLLoader.getController();
		compraFX.setConnection(connection);
		*/
	}
	@FXML
	private void btnRegistrarProveedor_Action() 
	{
		FormsOperations formsOperations = new FormsOperations();
		FXMLLoader fXMLLoader = formsOperations.OpenForm ("Registrar Proveedores" , "/view/RegistroPFX.fxml");
		RegistroPFX RegistroPFX = fXMLLoader.getController();
		RegistroPFX.setConnection(connection);
		
	}
	@FXML
	private void btnRegistarInsumos_Action() 
	{
		FormsOperations formsOperations = new FormsOperations();
		FXMLLoader fXMLLoader = formsOperations.OpenForm ("Registrar Insumo" , "/view/RegistrarInsumoFX.fxml");
		RegistroInsumoFX RegistroIFX = fXMLLoader.getController();
		RegistroIFX.setConnection(connection);
		
	}
	@FXML
	private void btnRegistrarReseta_Action() 
	{
		FormsOperations formsOperations = new FormsOperations();
		FXMLLoader fXMLLoader = formsOperations.OpenFormTab ("Registrar Receta" , "/view/RegistrarResetaFX.fxml");
		RegistroResetaFX RegistroRFX = fXMLLoader.getController();
		RegistroRFX.setConnection(connection);
		RegistroRFX.loadcbxInsumo();
		RegistroRFX.loadcbxProducto();
		
		
	}
	
	@FXML
	private void btnRegistrarProductos_Action() 
	{
		FormsOperations formsOperations = new FormsOperations();
		FXMLLoader fXMLLoader = formsOperations.OpenForm ("Registrar Producto" , "/view/RegistrarProductoFX.fxml");
		RegistroProductoFX RegistroProdFX = fXMLLoader.getController();
		RegistroProdFX.setConnection(connection);
		
	}
	
	@FXML
	private void btnRegistrarMesa_Action() 
	{
		FormsOperations formsOperations = new FormsOperations();
		FXMLLoader fXMLLoader = formsOperations.OpenForm ("Registrar Mesa" , "/view/RegistroMesaFX.fxml");
		RegistroMesaFX RegistroMFX = fXMLLoader.getController();
		RegistroMFX.setConnection(connection);
		
	}
	
	
	
}
