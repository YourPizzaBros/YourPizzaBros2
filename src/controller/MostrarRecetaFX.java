package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MostrarRecetaFX {
	private Connection connection;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;

	}
	
	@FXML
	private Button btnCargarPedidos;
	
	@FXML
	private TableView<Receta> tableRecetas;
	@FXML
	private TableColumn<Receta, String> colProducto;
	@FXML
	private TableColumn<Receta, String> colInsumo;
	@FXML
	private TableColumn<Receta, Integer> colCantidad;
	
	@FXML
	private void initialize() {
		
		
		colProducto.setCellValueFactory(new PropertyValueFactory<Receta, String>("producto"));
		colInsumo.setCellValueFactory(new PropertyValueFactory<Receta, String>("insumo"));
		colCantidad.setCellValueFactory(new PropertyValueFactory<Receta, Integer>("cantidad"));
		
	}

	
	@FXML
	private void btnCargar_Action() {

		try {

			final ObservableList<Receta> data = FXCollections.observableArrayList();
			PreparedStatement preparedStatement = connection
					.query("select producto.Nombre as producto, insumo.Nombre as insumo, receta.Cantidad from producto inner join receta\r\n" + 
							"on producto.codProducto = receta.codProducto\r\n" + 
							"inner join insumo on\r\n" + 
							"receta.codInsumo = insumo.codInsumo");
			ResultSet resultSet = preparedStatement.executeQuery();
			Receta receta = null;
			

			while (resultSet.next()) {
				

				receta= new Receta(resultSet.getString("producto"), resultSet.getString("insumo"),
						resultSet.getInt("cantidad"));
				

				data.add(receta);
				// tableProductos.setItems(data);

			}

			tableRecetas.getItems().addAll(data);
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		
		
	}
	public class Receta
	{
		public String producto;
		public String insumo;
		public int cantidad;
		public Receta(String producto, String insumo, int cantidad) {
			super();
			this.producto = producto;
			this.insumo = insumo;
			this.cantidad = cantidad;
			
		}
		public String getProducto() {
			return producto;
		}
		public void setProducto(String producto) {
			this.producto = producto;
		}
		public String getInsumo() {
			return insumo;
		}
		public void setInsumo(String insumo) {
			this.insumo = insumo;
		}
		public int getCantidad() {
			return cantidad;
		}
		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}
		
		
	}
	

}
