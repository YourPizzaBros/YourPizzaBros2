package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DetalleVentaFX {
	private Connection connection;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;

	}
	@FXML
	private TableView<DetVenta> tableDetVenta;
	@FXML
	private TableColumn<DetVenta, String> colFecha;
	@FXML
	private TableColumn<DetVenta, Integer> colIDMesa;
	@FXML
	private TableColumn<DetVenta, String> colNIT;
	@FXML
	private TableColumn<DetVenta, String> colProducto;
	@FXML
	private TableColumn<DetVenta, Integer>colPrecioUni;
	@FXML
	private TableColumn<DetVenta, Integer>colCantidad;
	@FXML
	private TableColumn<DetVenta, String> colEstado;
	@FXML
	private TableColumn<DetVenta, Integer> colTotProd;

	
	@FXML
	private void initialize() {
		
		 colFecha.setCellValueFactory(new PropertyValueFactory<DetVenta, String>("Fecha"));
		 colIDMesa.setCellValueFactory(new PropertyValueFactory<DetVenta, Integer>("IDMesa"));
		 colNIT.setCellValueFactory(new PropertyValueFactory<DetVenta, String>("NIT"));
		 colProducto.setCellValueFactory(new PropertyValueFactory<DetVenta, String>("Producto"));
		 colPrecioUni.setCellValueFactory(new PropertyValueFactory<DetVenta, Integer>("PrecioUni"));
		 colCantidad.setCellValueFactory(new PropertyValueFactory<DetVenta, Integer>("Cantidad"));
		 colEstado.setCellValueFactory(new PropertyValueFactory<DetVenta, String>("Estado"));
		 colTotProd.setCellValueFactory(new PropertyValueFactory<DetVenta, Integer>("TotProd"));
		

	}

	
	public class DetVenta
	{
		public String fecha;
		public int idMesa;
		public String NIT;
		public String producto;
		public int PrecioUni;
		public int cantidad;
		public String estado;
		public int totProd;

		public DetVenta(String fecha, int idMesa, String nIT, String producto, int precioUni, int cantidad,
				String estado, int totProd) {
			super();
			this.fecha = fecha;
			this.idMesa = idMesa;
			NIT = nIT;
			this.producto = producto;
			PrecioUni = precioUni;
			this.cantidad = cantidad;
			this.estado = estado;
			this.totProd = totProd;
		}

		public String getFecha() {
			return fecha;
		}

		public void setFecha(String fecha) {
			this.fecha = fecha;
		}

		public int getIdMesa() {
			return idMesa;
		}

		public void setIdMesa(int idMesa) {
			this.idMesa = idMesa;
		}

		public String getNIT() {
			return NIT;
		}

		public void setNIT(String nIT) {
			NIT = nIT;
		}

		public String getProducto() {
			return producto;
		}

		public void setProducto(String producto) {
			this.producto = producto;
		}

		public int getPrecioUni() {
			return PrecioUni;
		}

		public void setPrecioUni(int precioUni) {
			PrecioUni = precioUni;
		}

		public int getCantidad() {
			return cantidad;
		}

		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}

		public int getTotProd() {
			return totProd;
		}

		public void setTotProd(int totProd) {
			this.totProd = totProd;
		}


	}
	@FXML
	private void btnCargar_Action() {

		try {


			final ObservableList<DetVenta> data = FXCollections.observableArrayList();
			PreparedStatement preparedStatement = connection.query("select venta.Fecha as Fecha,venta.id_mesa as IDMesa, "
					+ "venta.nit as NIT, "
					+ "producto.Nombre as Producto, producto.Precio as PrecioUni,\r\n" + 
					"detalleventa.Cantidad as Cantidad, venta.Estado as Estado,\r\n" + 
					"(producto.Precio*detalleventa.cantidad) as TotProd\r\n" + 
					"from cliente inner join venta\r\n" + 
					"on cliente.NIT = venta.NIT inner join detalleventa\r\n" + 
					"on detalleventa.codVenta = venta.codVenta inner join producto\r\n" + 
					"on detalleventa.codProducto= producto.codProducto\r\n" + 
					"order by venta.Fecha desc limit 15;\r\n" + 
					"");
			ResultSet resultSet = preparedStatement.executeQuery();
			DetVenta detVenta = null;

			while (resultSet.next()) {
				System.out.println(resultSet.getString("producto") + " , " + resultSet.getString("cantidad"));



				detVenta = new DetVenta(resultSet.getString("Fecha"),resultSet.getInt("IDMesa"),
						resultSet.getString("NIT") ,resultSet.getString("Producto"),resultSet.getInt("PrecioUni"),
						resultSet.getInt("Cantidad"),resultSet.getString("Estado"),resultSet.getInt("TotProd"));

				data.add(detVenta);


			}

			tableDetVenta.getItems().addAll(data);
			

		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}
	

}
