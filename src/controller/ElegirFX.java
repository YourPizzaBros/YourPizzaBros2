package controller;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;


public class ElegirFX {

	@FXML
	public Button btnPedido;
	

	private RegistroCFX registrocfx;
	
	private String clienteNIT;

	@FXML
	public Button btnCuenta;
	
	private Connection connection;
	@FXML
	private void initialize() {
		
	}
	
	
	public String getClienteNIT() {
		return clienteNIT;
	}
	public void setClienteNIT(String string) {
		this.clienteNIT = string;
	}
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	
	
	
	@FXML
	private void btnPedido_Action() {
		
		
		FormsOperations formsOperations = new FormsOperations();
		FXMLLoader fXMLLoader = formsOperations.OpenFormTab ("Formulario Venta" , "/view/VentaFX.fxml");
		
		VentaFX ventaFX = fXMLLoader.getController();
		
		
		ventaFX.setConnection(connection);
		ventaFX.loadcbxProducto();   
		ventaFX.loadNIT(clienteNIT);
	
		
		
		
	}
	
	@FXML
	private void btnCuenta_Action() {
		
		
		FormsOperations formsOperations = new FormsOperations();
		FXMLLoader fXMLLoader = formsOperations.OpenForm ("Formulario Pagar" , "/view/PagarFX.fxml");
		PagarFX pagarFX = fXMLLoader.getController();
		pagarFX.setConnection(connection);
		pagarFX.setClienteNIT(clienteNIT);
		 //aqui tiene que venir con pagar
	}

	
}
