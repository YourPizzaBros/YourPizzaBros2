package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import controller.VentaFX.DetalleTBV;
import controller.VentaFX.ProductoCBX;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MostrarFX {

	private Connection connection;

	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@FXML
	private TableView<Producto> tableProductos;  //esto enves de table productos decia Porducto
	@FXML
	private TableColumn<Producto, String> tbcProducto;
	@FXML
	private TableColumn<Producto, String> tbcCantidad;
	@FXML
	private Button btnCargarP;

	
	@FXML
	private void initialize() {
		if (tbcProducto == null)
			System.out.println("mama");
		 //System.out.println(new PropertyValueFactory<>("nombre"));
		tbcProducto.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
		tbcCantidad.setCellValueFactory(new PropertyValueFactory<Producto, String>("cantidad"));
		
		
	}
	
	
	@FXML
	public void btnCargarP_Action() {
		
	
		try {

			
			//preparedStatement = connection.queryGeneratedKeys("select * from producto");
			//preparedStatement.executeUpdate();
			//resultSet = preparedStatement.executeQuery();
			//resultSet = preparedStatement.getGeneratedKeys();
			final ObservableList<Producto> data = FXCollections.observableArrayList();
			PreparedStatement preparedStatement = connection.query("Select * from producto");
			ResultSet resultSet = preparedStatement.executeQuery(); 
			Producto prod = null;
			
			
			while (resultSet.next()) {
				System.out.println(resultSet.getString("nombre") + " , " + resultSet.getString("cantidad"));

				
				String cantidad = Integer.toString(resultSet.getInt("cantidad")) ;
				
				prod = new Producto(resultSet.getString("nombre"), cantidad);
				 //tableProductos.getItems().add(prod);
				 
				 data.add(prod);
				 //tableProductos.setItems(data);
				 
				

			}
			
			tableProductos.getItems().addAll(data);
			tableProductos.setStyle("-fx-text-fill: black");
			
			//tableProductos.getColumns().setAll();
			//tableProductos.setItems(data);
			//tableProductos.getColumns().addAll(tbcProducto, tbcCantidad);
			//tableProductos.getChildren().add(tableProductos);

		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}
	
	
	 
	


	class Producto {
		private StringProperty nombre;
		private StringProperty cantidad;

		private Producto(String nombre, String  cantidad) {
			this.nombre = new SimpleStringProperty(nombre);
			this.cantidad = new SimpleStringProperty(cantidad);

		}

		public StringProperty firstNameProperty() {
			return nombre;
		}

		public StringProperty lastNameProperty() {
			return cantidad;
		}

		@Override
		public String toString() {
			return "Producto [nombre=" + nombre + ", cantidad=" + cantidad + "]";
		}

	}
	
	
	
	
	
	
	
	
}