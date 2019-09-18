package controller;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;


public class ElegirFX {

	@FXML
	public Button btnPedido;
	
<<<<<<< HEAD
=======
	private RegistroCFX registrocfx;
	
	private String clienteNIT;
>>>>>>> Andy
	@FXML
	public Button btnCuenta;
	
	private Connection connection;
	@FXML
	private void initialize() {
		
	}
	public void setclienteNIT(String nit) {
		
		this.clienteNIT=nit;
		
	}
	
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	
	/*@FXML
	private void btnPedido_Action() {
		try {
			connection = new Connection(txtUser.getText(), txtPassword.getText(),"mitienda");
			FormsOperations formsOperations = new FormsOperations();
			FXMLLoader fXMLLoader = formsOperations.OpenFormTab("Venta", "/view/VentaFX.fxml");
			VentaFX ventaFX = fXMLLoader.getController();
			ventaFX.setConnection(connection);
			ventaFX.loadcbxProducto();
			ventaFX.now();
			
		} catch (ClassNotFoundException e) {
			MessageBox msgBox = new MessageBox();
			msgBox.message("Exception", e.getMessage());
		} catch (SQLException e) {
			MessageBox msgBox = new MessageBox();
			msgBox.message("Exception", e.getMessage());
		}
	}*/
	
	
	
	@FXML
	private void btnPedido_Action() {
		
		System.out.println("apreto boton pedido");
		FormsOperations formsOperations = new FormsOperations();
		FXMLLoader fXMLLoader = formsOperations.OpenForm ("Formulario Venta" , "/view/VentaFX.fxml");
		System.out.println("entro a ventea");
		VentaFX ventaFX = fXMLLoader.getController();
		System.out.println("todo ok");
		
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
		
		 //aqui tiene que venir con pagar
	}
	
}
