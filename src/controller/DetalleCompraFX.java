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

public class DetalleCompraFX {
	private Connection connection;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;

	}

	@FXML
	private Button btnCargar;
	@FXML
	private TableView<DetCompra> tableDetCompra;
	@FXML
	private TableColumn<DetCompra, String> colFecha;
	@FXML
	private TableColumn<DetCompra, Integer> colIDProv;
	@FXML
	private TableColumn<DetCompra, Integer> colCantidad;
	@FXML
	private TableColumn<DetCompra, String> colInsumo;
	@FXML
	private TableColumn<DetCompra, Integer>colPrecio;
	
	@FXML
	private void initialize() {
		
		 colFecha.setCellValueFactory(new PropertyValueFactory<DetCompra, String>("fecha"));
		 colIDProv.setCellValueFactory(new PropertyValueFactory<DetCompra, Integer>("IDProveedor"));
		 colCantidad.setCellValueFactory(new PropertyValueFactory<DetCompra, Integer>("cantidad"));
		 colInsumo.setCellValueFactory(new PropertyValueFactory<DetCompra, String>("insumo"));
		 colPrecio.setCellValueFactory(new PropertyValueFactory<DetCompra, Integer>("precio"));
		 
		
		 
		

	}
	public class DetCompra 
	{
		public String fecha;
		public int IDProveedor;
		public int cantidad;
		public String insumo;
		public int precio;
		public DetCompra(String fecha, int iDProveedor, int cantidad, String insumo, int precio) {
			super();
			this.fecha = fecha;
			IDProveedor = iDProveedor;
			this.cantidad = cantidad;
			this.insumo = insumo;
			this.precio = precio;
		}
		public String getFecha() {
			return fecha;
		}
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
		public int getIDProveedor() {
			return IDProveedor;
		}
		public void setIDProveedor(int iDProveedor) {
			IDProveedor = iDProveedor;
		}
		public int getCantidad() {
			return cantidad;
		}
		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}
		public String getInsumo() {
			return insumo;
		}
		public void setInsumo(String insumo) {
			this.insumo = insumo;
		}
		public int getPrecio() {
			return precio;
		}
		public void setPrecio(int precio) {
			this.precio = precio;
		}
		
		
		
		
	}
	
	@FXML
	private void btnCargar_Action() {
		
			try {


				final ObservableList<DetCompra> data = FXCollections.observableArrayList();
				PreparedStatement preparedStatement = connection.query("select  detallecompra.Fecha as Fecha , relacion.NITProveedor as NITProveedor, \r\n" + 
						"detallecompra.Cantidad as Cantidad, insumo.Nombre as Insumo, relacion.precio as Precio from\r\n" + 
						"detallecompra inner join relacion on \r\n" + 
						"detallecompra.idRelacion= relacion.idRelacion\r\n" + 
						"inner join insumo\r\n" + 
						"on relacion.codInsumo = insumo.codInsumo\r\n" + 
						"order by detallecompra.Fecha desc limit 20\r\n" + 
						"\r\n" + 
						"");
				//preparedStatement.setInt(1, cbxInsumo.getValue().getCodigoInsumo());
				ResultSet resultSet = preparedStatement.executeQuery();
				DetCompra detVenta = null;

				while (resultSet.next()) {
					//System.out.println(resultSet.getString("producto") + " , " + resultSet.getString("cantidad"));



					detVenta = new DetCompra(resultSet.getString("Fecha"),resultSet.getInt("NITProveedor"),
							resultSet.getInt("Cantidad") ,resultSet.getString("Insumo"),resultSet.getInt("Precio"));

					data.add(detVenta);


				}

				tableDetCompra.getItems().addAll(data);
				

			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}

		

	
	

	
	
}
