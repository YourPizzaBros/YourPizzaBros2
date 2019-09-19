package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


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
	private TableView<Insumo> tableInsumos; // esto enves de table productos decia Porducto
	@FXML
	private TableColumn<Insumo, String> tbcInsumo;
	@FXML
	private TableColumn<Insumo, String> tbcCantidad;
	@FXML
	private Button btnCargarP;

	@FXML
	private void initialize() {
		if (tbcInsumo == null)
			System.out.println("mama");
		// System.out.println(new PropertyValueFactory<>("nombre"));
		tbcInsumo.setCellValueFactory(new PropertyValueFactory<Insumo, String>("nombre"));
		tbcCantidad.setCellValueFactory(new PropertyValueFactory<Insumo, String>("cantidad"));

	}

	@FXML
	private void btnCargarP_Action() {

		try {

			// preparedStatement = connection.queryGeneratedKeys("select * from producto");
			// preparedStatement.executeUpdate();
			// resultSet = preparedStatement.executeQuery();
			// resultSet = preparedStatement.getGeneratedKeys();
			final ObservableList<Insumo> data = FXCollections.observableArrayList();
			PreparedStatement preparedStatement = connection.query("Select * from insumo");
			ResultSet resultSet = preparedStatement.executeQuery();
			Insumo prod = null;

			while (resultSet.next()) {
				System.out.println(resultSet.getString("nombre") + " , " + resultSet.getString("cantidad"));

				String cantidad = resultSet.getString("cantidad");

				prod = new Insumo(resultSet.getString("nombre"), cantidad);
				// tableProductos.getItems().add(prod);
				// tableProductos.getItems().add(prod);
				data.add(prod);
				// tableProductos.setItems(data);

			}

			tableInsumos.getItems().addAll(data);
			// tableProductos.setStyle("-fx-text-fill: black");

			// tableProductos.getColumns().setAll();
			// tableProductos.setItems(data);
			// tableProductos.getColumns().addAll(tbcProducto, tbcCantidad);
			// tableProductos.getChildren().add(tableProductos);

		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}

	public class Insumo {
		public String nombre;
		public String cantidad;

		public Insumo(String nombre, String cantidad) {
			this.nombre = nombre;
			this.cantidad = cantidad;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getCantidad() {
			return cantidad;
		}

		public void setCantidad(String cantidad) {
			this.cantidad = cantidad;
		}

	}

	/*
	 * class Producto { private StringProperty nombre; private StringProperty
	 * cantidad;
	 * 
	 * private Producto(String nombre, String cantidad) { this.nombre = new
	 * SimpleStringProperty(nombre); this.cantidad = new
	 * SimpleStringProperty(cantidad);
	 * 
	 * }
	 * 
	 * public StringProperty firstNameProperty() { return nombre; }
	 * 
	 * public StringProperty lastNameProperty() { return cantidad; }
	 * 
	 * @Override public String toString() { return "Producto [nombre=" + nombre +
	 * ", cantidad=" + cantidad + "]"; }
	 * 
	 * }
	 */

}