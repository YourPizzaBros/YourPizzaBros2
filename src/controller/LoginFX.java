package controller;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginFX {
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
			connection = new Connection(txtUser.getText(), txtPassword.getText(),"mitienda");
			System.out.println("se conecta");
			if(txtUser.getText().equals("root")) 
			{
				System.out.println("Entra al if");
				FormsOperations formsOperations = new FormsOperations();
				FXMLLoader fXMLLoader = formsOperations.OpenForm("Opciones Administrador","/view/AdmiFX.fxml");
				AdmiFX admiFX =  fXMLLoader.getController();
				admiFX.setConnection(connection);
			}
			else if(txtUser.getText().equals("cliente")) 
			{
				FormsOperations formsOperations = new FormsOperations();
				FXMLLoader fXMLLoader = formsOperations.OpenForm("Registro Cliente","/view/RegistroCFX.fxml");
				RegistroCFX registroCFX =  fXMLLoader.getController();
				registroCFX.setConnection(connection);
			}
						
			
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
	
}
