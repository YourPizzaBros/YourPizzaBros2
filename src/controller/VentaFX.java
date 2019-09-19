package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.Date;



public class VentaFX {
	private Connection connection;
	@FXML
	private DatePicker dtpFecha;
	@FXML
	private TextField txtNIT;
	
	
	@FXML
	private TextField txtMesa;
	@FXML
	private TextField txtNombre;
	@FXML
	private ComboBox<ProductoCBX> cbxProducto;
	@FXML
	private TableView<DetalleTBV> tbvDetalle;
	@FXML
	private TableColumn<DetalleTBV, String> tbcProducto;
	@FXML	
	private TableColumn<DetalleTBV, String> tbcCantidad;
	@FXML
	private TextField txtCantidad;
	@FXML
	private Button btnAñadirProducto;
	@FXML
	private Button btnEditarProducto;
	@FXML
	private Button btnRemoverProducto;
	@FXML
	private Button btnGuardarVenta;
	@FXML
	private Button btnCancelarVenta;
	@FXML
	private TabPane tbpPanel;
	
	
	
	
	@FXML
	private Tab tabMenu; 
	@FXML
	private Tab tabVenta;
	
	
	
	@FXML
	private void initialize() {
		
		 
		tbcProducto.setCellValueFactory(new PropertyValueFactory<>("productoCBX"));
		tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
	}
	

	

	@FXML
	private void btnAñadirProducto_Action() {
		
		if(registrado()) {
		
		if (cbxProducto.getValue() != null && !txtCantidad.getText().isEmpty()) { // Se verifica si ambos no estan
																					// vacios
			DetalleTBV detalleTBV = new DetalleTBV(cbxProducto.getValue(), Integer.parseInt(txtCantidad.getText()));
			if (!buscarDetalle(detalleTBV)) {
				
				tbvDetalle.getItems().add(detalleTBV);
				
				cbxProducto.setValue(null);
				txtCantidad.setText("");
			}else {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Información", "El producto se encuentra en el detalle");
			}

		}
		}
		
		else{
			MessageBox messageBox = new MessageBox();
			messageBox.message("Información", "Su NIT es incorrecto");
			
			
		}
	}

	
	
	private boolean buscarDetalle(DetalleTBV detalleTBV) {
		boolean resp = false;
		for (DetalleTBV item : tbvDetalle.getItems()) {
			if (item.getProductoCBX().equals(detalleTBV.getProductoCBX())) {
				resp = true;
				break;
			}
		}
		return resp;
	}

	@FXML
	private void btnEditarProducto_Action() {
		DetalleTBV selectedItem = tbvDetalle.getSelectionModel().getSelectedItem();
		cbxProducto.setValue(selectedItem.getProductoCBX());
		txtCantidad.setText(selectedItem.getCantidad().toString());
		tbvDetalle.getItems().remove(selectedItem);
	}
	
	@FXML
	private void btnRemoverProducto_Action() {
		DetalleTBV selectedItem = tbvDetalle.getSelectionModel().getSelectedItem();
		tbvDetalle.getItems().remove(selectedItem);
	}

	@FXML
	private void btnGuardarVenta_Action() {
		int numeroVenta = 0;

		if (dtpFecha.getValue() != null && !txtNIT.getText().isEmpty()) {
			if (tbvDetalle.getItems().size() > 0) {

				numeroVenta = guardarVenta(convertToDate(dtpFecha.getValue()), txtNIT.getText());

				
				for (DetalleTBV detalleTBV : tbvDetalle.getItems()) {

					guardarDetalleVenta(numeroVenta, detalleTBV.getProductoCBX().getCodigoProducto(),
							detalleTBV.getCantidad(), detalleTBV.getProductoCBX().getPrecio());
				}
				
							try {
					PreparedStatement preparedStatement = connection.query( "update mesa " + 
							"set estado= 'ocupado' " + 
							"where mesa.id_Mesa=?" );
				
					preparedStatement.setString(1,  txtMesa.getText()  );
				 preparedStatement.executeUpdate();
					
				
				} catch (SQLException e) {
					MessageBox messageBox = new MessageBox();
					messageBox.message("Error en Consulta Mesa", e.getMessage());
					
				}
							
							
							
							
							
				
				
				
				
				
				cleanScreen(); 
			}
		} else {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error en la venta", "Los campos no deben estar vacios");
		}

	}
	private boolean registrado() {
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		boolean registrado= false;
		
			try {
<<<<<<< HEAD
				preparedStatement = connection.query("select cliente.apellidoC from cliente "
=======
				preparedStatement = connection.queryGeneratedKeys("select cliente.apellidoC from cliente "
>>>>>>> Andy
						+ "  where cliente.nit = ?" );
				preparedStatement.setString(1, txtNIT.getText());
		
			 resultset =preparedStatement.executeQuery();
				
				if ( resultset.next()) {
					registrado=true;
					
				}
				
		   
			} catch (SQLException e) {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Error en Consulta Registrado", e.getMessage());
				
			}
		
		return registrado;
	}
	
	@FXML
	private void btnCancelarVenta_Action() {
		cleanScreen(); 
	}

	

	
	private java.util.Date convertToDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
	}

	private int guardarVenta(Date fecha, String NIT) {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		int aiKey = 0;

		try {

			
			preparedStatement = connection.queryGeneratedKeys("insert into Venta(nit,fecha, estado ,id_mesa) " + "values(?,?, 'SinPagar' ,?)");
		
			preparedStatement.setDate(2, new java.sql.Date(fecha.getTime())  );
			preparedStatement.setString(1, txtNIT.getText());
			preparedStatement.setString(3, txtMesa.getText());
			
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				aiKey = resultSet.getInt(1); // Obtenemos la clave autoIncrementada
			}
		} catch (SQLException e) {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error en Consulta", e.getMessage());
		}
		return aiKey;
	}

	private void guardarDetalleVenta(int numero, int codigoProducto, int cantidad, double precio) {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection
					.query("insert into DetalleVenta(codVenta, codProducto, Precio, Cantidad) " + "values(?,?,?,?)");
			
			preparedStatement.setInt(1, buscarCodVenta());
			preparedStatement.setInt(2, codigoProducto);
			preparedStatement.setInt(4, cantidad);
			preparedStatement.setDouble(3, precio);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error en Consulta", e.getMessage());
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void loadcbxProducto() {
		try {
			PreparedStatement preparedStatement = connection.query("Select * from producto");
			ResultSet resultSet = preparedStatement.executeQuery();
			ProductoCBX productoCBX = null;

			while (resultSet.next()) {
				productoCBX = new ProductoCBX(resultSet.getInt("codProducto"), resultSet.getString("nombre"),
						resultSet.getDouble("precio"), resultSet.getString("descripcion"),
						resultSet.getString("tamano"), resultSet.getInt("cantidad"));
				cbxProducto.getItems().add(productoCBX);
			}

		} catch (SQLException e) {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error", e.getMessage());
		}
	}
	

	private int buscarCodVenta () {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int codigo=0;

		try {
			preparedStatement = connection.query("select venta.codVenta from venta " + " where venta.Nit = ? " + 
					" and venta.id_mesa = ? ");
			preparedStatement.setString(1, txtNIT.getText());
			preparedStatement.setString(2, txtMesa.getText());
			//preparedStatement.setString(3, precio);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				
				codigo = resultSet.getInt(1);
			}
			
		} catch (SQLException e) {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error Producto", e.getMessage());
		}
		return codigo;
	}
	
	
	private String buscarNombre(String NIT) {
		String nombre = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.query("select nombre from cliente " + "where nit = ?");
			preparedStatement.setString(1, NIT);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				nombre = resultSet.getString("nombre");
			}
		} catch (SQLException e) {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error Cliente", e.getMessage());
		}
		return nombre;
	}

	public void now() {

		dtpFecha.setValue(LocalDate.now());
	}

	private void cleanScreen() {
		now();
		txtNIT.setText("");
		txtMesa.setText("");
		cbxProducto.setValue(null);
		txtCantidad.setText("");
		tbvDetalle.getItems().clear();
	}

	class ProductoCBX extends model.Producto {

		public ProductoCBX(int codigoProducto, String nombre, Double precio, String descripción, String tamano,int cantidad) {
			super(codigoProducto, nombre, precio, descripción, tamano,cantidad);
		}

		
		@Override
		public String toString() {
			return "" + getNombre() + "- Tamaño : " + getTamano();
		}
		
	
		
	}
	
	public void loadNIT (String nit) {
	txtNIT.setText(nit);	
		
	}

	public class DetalleTBV {
		private ProductoCBX productoCBX;
		private Integer cantidad;

		public DetalleTBV(ProductoCBX productoCBX, Integer cantidad) {
			this.productoCBX = productoCBX;
			this.cantidad = cantidad;
		}

		public ProductoCBX getProductoCBX() {
			return productoCBX;
		}

		public void setProductoCBX(ProductoCBX productoCBX) {
			this.productoCBX = productoCBX;
		}

		public Integer getCantidad() {
			return cantidad;
		}

		public void setCantidad(Integer cantidad) {
			this.cantidad = cantidad;
		}
	}

	



}
