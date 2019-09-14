package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class MessageBoxFX {
	@FXML
	private Label label;
	
	@FXML
	private Button btnOK;
	
	@FXML
	private TextArea textArea;
	
	@FXML
	private void btnOK_Action() {
		Stage stage = (Stage) btnOK.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	private void initialize() {
		textArea.setWrapText(true);
	}
	
	public void setMessage(String title, String msg) {
		label.setText(title);
		textArea.setText(msg);
	}
}
