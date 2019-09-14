package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
<<<<<<< HEAD
import javafx.scene.image.Image;
=======
>>>>>>> Andy
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FormsOperations {

	public FXMLLoader OpenForm(String title,String url) {
		FXMLLoader fXMLLoader = null;
		try {
			fXMLLoader = new FXMLLoader(getClass().getResource(url));
			AnchorPane root = fXMLLoader.load();
<<<<<<< HEAD
			root.setId("root");
=======
>>>>>>> Andy
			Stage stage = new Stage();
            Scene scene = new Scene(root,root.getPrefWidth(),root.getPrefHeight());
            stage.resizableProperty().setValue(Boolean.FALSE);
			stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
<<<<<<< HEAD
            stage.getIcons().add (new Image ("/image/pizzaIcon.png"));
            //scene.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
=======
>>>>>>> Andy
           	stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return fXMLLoader;
	}
	
	public FXMLLoader OpenForm(String title,String url, Boolean resizable ) { //si le ponemos falso a rezible modificar la pantalla se anula
		FXMLLoader fXMLLoader = null;
		try {
			fXMLLoader = new FXMLLoader(getClass().getResource(url));
			AnchorPane root = fXMLLoader.load();
			Stage stage = new Stage();
            Scene scene = new Scene(root,root.getPrefWidth(),root.getPrefHeight());
            stage.resizableProperty().setValue(resizable);
			stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
           	stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return fXMLLoader;
	}
}
