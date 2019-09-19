package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//import com.mysql.jdbc.Connection;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PagarFX {
	private Connection connection;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;

	}

	private String clienteNIT;
	

	public String getClienteNIT() {
		return clienteNIT;
	}

	public void setClienteNIT(String clienteNIT) {
		this.clienteNIT = clienteNIT;
	}

	@FXML
	private Button btnPagar;
	@FXML
	private Button btnCargarPedidos;
	@FXML
	private Label lblTotal;
	@FXML
	private TableView<Pago> tablePagar;
	@FXML
	private TableColumn<Pago, String> colNIT;
	@FXML
	private TableColumn<Pago, String> colProducto;
	@FXML
	private TableColumn<Pago, Integer> colPrecio;
	@FXML
	private TableColumn<Pago, Integer> colCantidad;
	@FXML
	private TableColumn<Pago, Integer> colTotProd;
	@FXML
	private void initialize() {
		
		// System.out.println(new PropertyValueFactory<>("nombre"));
	colNIT.setCellValueFactory(new PropertyValueFactory<Pago, String>("INT"));
		colProducto.setCellValueFactory(new PropertyValueFactory<Pago, String>("producto"));
		colPrecio.setCellValueFactory(new PropertyValueFactory<Pago, Integer>("precioUni"));
		colCantidad.setCellValueFactory(new PropertyValueFactory<Pago, Integer>("cantidad"));
		colTotProd.setCellValueFactory(new PropertyValueFactory<Pago, Integer>("totProd"));
		
		
	
	
	
		

	}

	public class Pago {
		public String INT;
		public String producto;
		public Integer precioUni;
		public Integer cantidad;
		public Integer totProd;

		public Pago(String iNT, String producto, Integer precioUni, Integer cantidad, Integer totProd) {
			super();
			this.INT = iNT;
			this.producto = producto;
		  this.precioUni = precioUni;
			this.cantidad = cantidad;
			this.totProd = totProd;
		}
		
			
		

		public String getINT() {
			return INT;
		}

		public void setINT(String iNT) {
			this.INT = iNT;
		}

		public String getProducto() {
			return producto;
		}

		public void setProducto(String producto) {
			this.producto = producto;
		}

		public Integer getPrecioUni() {
			return precioUni;
		}

		public void setPrecioUni(Integer precioUni) {
			this.precioUni = precioUni;
		}

		public Integer getCantidad() {
			return cantidad;
		}

		public void setCantidad(Integer cantidad) {
			this.cantidad = cantidad;
		}

		public Integer getTotProd() {
			return totProd;
		}

		public void setTotProd(Integer totProd) {
			this.totProd = totProd;
		}

	}

	@FXML
	private void btnCargarPedidos_Action() {

		try {

			final ObservableList<Pago> data = FXCollections.observableArrayList();
		PreparedStatement preparedStatement = connection
					.query("select cliente.nit as NIT, producto.Nombre as Producto, producto.Precio as PrecioUni,\r\n" + 
							"detalleventa.Cantidad as Cantidad, venta.Estado as Estado,\r\n" + 
							"(producto.Precio*detalleventa.cantidad) as TotProd\r\n" + 
							"from cliente inner join venta\r\n" + 
							"on cliente.NIT = venta.NIT inner join detalleventa\r\n" + 
							"on detalleventa.codVenta = venta.codVenta inner join producto\r\n" + 
							"on detalleventa.codProducto= producto.codProducto\r\n" + 
							"where cliente.nit= ? and venta.Estado = \"SinPagar\" ");
		/*	PreparedStatement preparedStatement = connection
					.query("select " + 
							"venta.Estado,\r\n" + 
							"cliente.nit\r\n" + 
							"from cliente inner join venta\r\n" + 
							"on cliente.nit = venta.NIT\r\n" + 
							"where cliente.nit= ? and venta.Estado = 'SinPagar' ");*/
			preparedStatement.setString(1, this.getClienteNIT());
			ResultSet resultSet = preparedStatement.executeQuery();
			Pago pago = null;
			Integer TOTAL = 0;

			while (resultSet.next()) {
				
				
				System.out.println("Entra a pagar");
				System.out.println(resultSet.getString("NIT") + " , " + resultSet.getString("Producto") + " , "
						+ resultSet.getString("PrecioUni") + " , " + resultSet.getString("Cantidad") + " , "
						+ resultSet.getString("TotProd"));

				pago = new Pago(resultSet.getString("NIT"), resultSet.getString("Producto"),
				resultSet.getInt("PrecioUni"), resultSet.getInt("Cantidad"), resultSet.getInt("TotProd"));
			
				tableProductos.getItems().add(prod);
			TOTAL = TOTAL + resultSet.getInt("TotProd");

				data.add(pago);
				tableProductos.setItems(data);

			}

			tablePagar.getItems().addAll(data);
		System.out.println("EL TOTAL ES: "+TOTAL);
		lblTotal.setText("EL TOTAL ES: "+TOTAL.toString());
		//total();
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		

	}

	
	public void setConnection1(controller.Connection connection) {
		this.connection = (Connection) connection;

	}

	@FXML
	private void btnPagar_Action() {
		try {
			ResultSet resultSetb=null;
			PreparedStatement preparedStatement22 = connection.query("select \r\n" + 
					"	venta.id_mesa " + 
					"			from \r\n" + 
					"			venta\r\n" + 
					"			where venta.NIT = ? && venta.Estado = 'SinPagar' ");
			preparedStatement22.setString(1, this.getClienteNIT());
			resultSetb=preparedStatement22.executeQuery();
		
			int num= resultSetb.getInt("id_mesa");
			 System.out.println(num + "antes" );
			 
			PreparedStatement preparedStatement = connection.query("update venta\r\n" + "inner join cliente on \r\n"
					+ "venta.NIT = cliente.nit " + " set venta.Estado= \"Pagado\" "
					+ " where venta.Estado = \"SinPagar\" and cliente.nit= ?");
			preparedStatement.setString(1, this.getClienteNIT());
			 preparedStatement.executeUpdate();
			//System.out.println(resultSet.getString("venta.estado"));
			
			
		 System.out.println(num + "despues" );
				PreparedStatement preparedStatement2 = connection.query( "update mesa  " + 
						" set estado= 'Desocupado'  " + 
						" where mesa.id_Mesa=? " );
			//preparedStatement2.setInt(1,  resultSetb.getInt("id_mesa") );
				preparedStatement2.setInt(1,  num);
	    	 preparedStatement2.executeUpdate();
	    	 
	    	
				
			
			
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}
