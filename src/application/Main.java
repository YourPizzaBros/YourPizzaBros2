package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/view/TituloFX.fxml"));
			FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/view/TituloFX.fxml"));
			AnchorPane root = fXMLLoader.load();
			//ScreenFX screenFX = fXMLLoader.getController();
			Scene scene = new Scene(root,root.getPrefWidth(),root.getPrefHeight());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.getIcons().add (new Image ("/image/pizzaIcon.png"));
			primaryStage.setScene(scene);
			System.out.println("main");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
