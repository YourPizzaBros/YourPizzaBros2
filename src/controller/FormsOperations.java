package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;


import javafx.scene.control.TabPane;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FormsOperations {

	public FXMLLoader OpenForm(String title,String url) {
		FXMLLoader fXMLLoader = null;
		try {
			fXMLLoader = new FXMLLoader(getClass().getResource(url));
			AnchorPane root = fXMLLoader.load();

	        root.setId("root");



			Stage stage = new Stage();
            Scene scene = new Scene(root,root.getPrefWidth(),root.getPrefHeight());
            stage.resizableProperty().setValue(Boolean.FALSE);
			stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);

            stage.getIcons().add (new Image ("/image/pizzaIcon.png"));
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
            stage.getIcons().add (new Image ("/image/pizzaIcon.png"));
           	stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return fXMLLoader;
	}
	
	
	
	
	public FXMLLoader OpenFormTab(String title, String url) {
		System.out.println("en tap pane");
		FXMLLoader fXMLLoader = null;
		try {
			fXMLLoader = new FXMLLoader(getClass().getResource(url));
			TabPane root = fXMLLoader.load();
			Stage stage = new Stage();
            Scene scene = new Scene(root,root.getPrefWidth(),root.getPrefHeight());
            stage.resizableProperty().setValue(Boolean.FALSE);
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
