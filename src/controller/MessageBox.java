package controller;

import javafx.fxml.FXMLLoader;

public class MessageBox {

	public void message(String title, String msg) {
		FormsOperations formsOperations = new FormsOperations();
		FXMLLoader fXMLLoader = formsOperations.OpenForm("warning","/view/MessageBoxFX.fxml");
		MessageBoxFX messageBoxFX = fXMLLoader.getController();
		messageBoxFX.setMessage(title, msg);
	}
}
