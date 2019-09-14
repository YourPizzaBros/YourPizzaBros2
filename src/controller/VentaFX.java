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
	private Button btnA�adirProducto;
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
	private Tab tabMenu;  //cambiando de lo que era tab cliente
	@FXML
	private Tab tabVenta;
	
	
	
	@FXML
	private void initialize() {
		/*
		 * Se define que tipo de datos guardar� cada columna
		 */
		
		
		//txtNombre.setText(nombre);
		tbcProducto.setCellValueFactory(new PropertyValueFactory<>("productoCBX"));
		tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
	}

	/*@FXML
	private void txtNIT_Action() {
		String nombre = null;
		if (!txtNIT.getText().isEmpty()) {
			nombre = buscarNombre(txtNIT.getText());
			if (nombre != null) {
				txtNombre.setText(nombre);
			} else {
				txtNITCliente.setText(txtNIT.getText());
				tbpPanel.getSelectionModel().select(tabCliente);
			}

		} else {
			MessageBox messageBox = new MessageBox();
			messageBox.message("NIT", "El Campo de NIT no puede estar vacio");
		}  /// este es para que aparezca el nit de la otra pagi
	}*/

	@FXML
	private void btnA�adirProducto_Action() {
		
		if (cbxProducto.getValue() != null && !txtCantidad.getText().isEmpty()) { // Se verifica si ambos no estan
																					// vacios
			DetalleTBV detalleTBV = new DetalleTBV(cbxProducto.getValue(), Integer.parseInt(txtCantidad.getText()));
			if (!buscarDetalle(detalleTBV)) {
				// A�ade el objeto detalleTBV en la tabla
				tbvDetalle.getItems().add(detalleTBV);
				// Despues de a�adir, ambos cambian a estado vacio
				cbxProducto.setValue(null);
				txtCantidad.setText("");
			}else {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Informaci�n", "El producto se encuentra en el detalle");
			}

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
		int n�meroVenta = 0;

		if (dtpFecha.getValue() != null && !txtNIT.getText().isEmpty() && !txtNombre.getText().isEmpty()) {
			if (tbvDetalle.getItems().size() > 0) {

				n�meroVenta = guardarVenta(convertToDate(dtpFecha.getValue()), txtNIT.getText());

				
				for (DetalleTBV detalleTBV : tbvDetalle.getItems()) {

					guardarDetalleVenta(n�meroVenta, detalleTBV.getProductoCBX().getC�digoProducto(),
							detalleTBV.getCantidad());
				}
				cleanScreen(); 
			}
		} else {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error en la venta", "Los campos no deben estar vacios");
		}

	}

	@FXML
	private void btnCancelarVenta_Action() {
		cleanScreen(); // Limpiamos la pantalla
	}

	

	/*
	 * Convierte un valor LocalDate (DatePicker) en java.util.Date
	 */
	private java.util.Date convertToDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
	}

	private int guardarVenta(Date fecha, String NIT) {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		int aiKey = 0;

		try {

			
			preparedStatement = connection.queryGeneratedKeys("insert into Venta(fecha, nit) " + "values(?,?)");
			// Convertimos la fecha de java.util en java.sql para guardarla
			preparedStatement.setDate(1, new java.sql.Date(fecha.getTime()));
			preparedStatement.setString(2, txtNIT.getText());
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

	private void guardarDetalleVenta(int n�mero, int c�digoProducto, int cantidad) {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection
					.query("insert into detalleventa(n�mero, c�digoProducto, cantidad) " + "values(?,?,?)");
			preparedStatement.setInt(1, n�mero);
			preparedStatement.setInt(2, c�digoProducto);
			preparedStatement.setInt(3, cantidad);
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

			/*
			 * Se carga el comboBox con Producto
			 */
			while (resultSet.next()) {
				productoCBX = new ProductoCBX(resultSet.getInt("codigoProducto"), resultSet.getString("nombre"),
						resultSet.getDouble("precio"), resultSet.getString("descripci�n"),
						resultSet.getString("tamano"));
				cbxProducto.getItems().add(productoCBX);
			}

		} catch (SQLException e) {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error", e.getMessage());
		}
	}
	
	
	
	
	/*private boolean buscarNombre(String NIT) {
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
	}*/

	public void now() {

		dtpFecha.setValue(LocalDate.now());
	}

	private void cleanScreen() {
		now();
		txtNIT.setText("");
		txtNombre.setText("");
		cbxProducto.setValue(null);
		txtCantidad.setText("");
		tbvDetalle.getItems().clear();
	}

	class ProductoCBX extends model.Producto {

		public ProductoCBX(int c�digoProducto, String nombre, Double precio, String descripcion, String tamano) {
			super(c�digoProducto, nombre, precio, descripcion, tamano);
		}

		@Override
		public String toString() {
			return " "+ getNombre() + " .Tama�o : " + getTamano() ;
		}
		
		
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
