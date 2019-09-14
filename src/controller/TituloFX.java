package controller;

import java.sql.Connection;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class TituloFX {
	private Connection connection;
	@FXML
	private Label lblYourPizzaBros;
	@FXML
	private Label lblGaby;
	@FXML
	private Label lblTefy;
	@FXML
	private Label lblAndy;
	@FXML
	private Button btnSTART;

	@FXML
	private void initialize() {
				
	}
	@FXML
	  private void btnSTART_Action() {
	  
	  FormsOperations formsOperations = new FormsOperations();
	  formsOperations.OpenForm("Usuario","/view/LoginFX.fxml");
	  
	  
	  }
	 


}
