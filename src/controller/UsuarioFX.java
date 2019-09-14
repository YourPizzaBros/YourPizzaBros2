package controller;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UsuarioFX {
	private Connection connection;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	@FXML
	private Label lblUsuario;
	@FXML
	private Label lblClave;
	@FXML
	private TextField txtFUsuario;
	@FXML
	private PasswordField txtPClave;
	@FXML
	private Button btnOK;
	@FXML
	private void btnOK_Action() 
	{
		System.out.println("boton ok");
		/*
		 * try { connection = new Connection(txtFUsuario.getText(),
		 * txtFUsuario.getText(),"mitienda"); //para no pedir la coneccion a cada rato
		 * FormsOperations formsOperations = new FormsOperations();
		 * if(txtFUsuario.getText()=="cliente") { FXMLLoader fXMLLoader =
		 * formsOperations.OpenForm("Registro Cliente","/view/RegistroCFX.fxml");
		 * RegistroCFX registroCFX =fXMLLoader.getController();
		 * registroCFX.setConnection(connection); System.out.println("boton ok");
		 * 
		 * }
		 * 
		 * else if(txtFUsuario.getText()=="root") { FXMLLoader fXMLLoader =
		 * formsOperations.OpenForm("Opciones Administrador","/view/AdmiFX.fxml");
		 * RegistroCFX registroCFX =fXMLLoader.getController();
		 * registroCFX.setConnection(connection); } else { MessageBox msgBox = new
		 * MessageBox(); msgBox.message("Exception", "Usuario Incorrecto"); }
		 * 
		 * 
		 * } catch (ClassNotFoundException e) { MessageBox msgBox = new MessageBox();
		 * msgBox.message("Exception", e.getMessage()); } catch (SQLException e) {
		 * MessageBox msgBox = new MessageBox(); msgBox.message("Exception",
		 * e.getMessage()); }
		 */
		try {
			System.out.println("Try");
			connection = new Connection(txtFUsuario.getText(), txtPClave.getText(),"mitienda");  //para no pedir la coneccion a cada rato
			FormsOperations formsOperations = new FormsOperations();
			FXMLLoader fXMLLoader = formsOperations.OpenForm("Registro Cliente","/view/RegistroCFX.fxml");
			RegistroCFX registroCFX =  fXMLLoader.getController();
			registroCFX.setConnection(connection);
			
			
		} catch (ClassNotFoundException e) {
			MessageBox msgBox = new MessageBox();
			msgBox.message("Exception", e.getMessage());
		} catch (SQLException e) {
			MessageBox msgBox = new MessageBox();
			msgBox.message("Exception", e.getMessage());
		}
	}
}

