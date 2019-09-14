package controller;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class UsuarioFX {

	
	
		private Connection connection;
		
		@FXML
		private TextField txtUser;
		
		@FXML
		private PasswordField txtPassword;   //este es el que esconde las letras cuando ingresamos la contrasena
		
		@FXML
		private Button btnOk;
		
		@FXML
		private Button btnCancel;
		
		@FXML
		private void initialize() {
					
		}
		
		@FXML
		private void btnOK_Action() {
			try {
				connection = new Connection(txtUser.getText(), txtPassword.getText(),"mitienda");   //para no pedir la coneccion a cada rato
				FormsOperations formsOperations = new FormsOperations();
				FXMLLoader fXMLLoader = formsOperations.OpenForm("yourname","/view/InitialFX.fxml");   
				InitialFX initialFX =  fXMLLoader.getController();
				initialFX.setConnection(connection);
				
				
			} catch (ClassNotFoundException e) {
				MessageBox msgBox = new MessageBox();
				msgBox.message("Exception", e.getMessage());
			} catch (SQLException e) {
				MessageBox msgBox = new MessageBox();
				msgBox.message("Exception", e.getMessage());
			}
			
		}
		
		@FXML
		private void btnCancel_Action() {
			txtUser.setText(null);
			txtPassword.setText(null);
		}

		public Connection getConnection() {
			return connection;
		}
		
		
		public void alCliente () {    ///para que si ingrese un cliente entre aqui
			
			
			
			
		}
		
		public void alAdmi () {  /// si entra un 
			
			
			
			
			
			
			
		}
	}

	
	
	
	
	
	
}
