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

public class CompraFX {
	private Connection connection;
	@FXML
	private DatePicker dtpFecha;
	/*@FXML
	private TextField txtNIT;*/
	
	@FXML
	private TextField txtNombre;
	@FXML
	private ComboBox<InsumoCBX> cbxInsumo;
	@FXML
	private TableView<DetalleTBV> tbvDetalle;
	@FXML
	private TableColumn<DetalleTBV, String> tbcInsumo;
	@FXML
	private TableColumn<DetalleTBV, String> tbcCantidad;
	@FXML
	private TextField txtCantidad;
	@FXML
	private Button btnAñadirInsumo;
	@FXML
	private Button btnEditarInsumo;
	@FXML
	private Button btnRemoverInsumo;
	@FXML
	private Button btnComprar;
	@FXML
	private Button btnCancelarCompra;
	@FXML
	private TabPane tbpPanel;
	
	
	@FXML
	private Tab tabMenu;  //cambiando de lo que era tab cliente
	@FXML
	private Tab tabVenta;
	
	
	
	@FXML
	private void initialize() {
		/*
		 * Se define que tipo de datos guardará cada columna
		 */
		
		
		//txtNombre.setText(nombre);
		tbcInsumo.setCellValueFactory(new PropertyValueFactory<>("insumoCBX"));
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
	private void btnAñadirProducto_Action() {
		
		if (cbxInsumo.getValue() != null && !txtCantidad.getText().isEmpty()) { // Se verifica si ambos no estan
																					// vacios
			DetalleTBV detalleTBV = new DetalleTBV(cbxInsumo.getValue(), Integer.parseInt(txtCantidad.getText()));
			if (!buscarDetalle(detalleTBV)) {
				// Añade el objeto detalleTBV en la tabla
				tbvDetalle.getItems().add(detalleTBV);
				// Despues de añadir, ambos cambian a estado vacio
				cbxInsumo.setValue(null);
				txtCantidad.setText("");
			}else {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Información", "El producto se encuentra en el detalle");
			}

		}
	}

	private boolean buscarDetalle(DetalleTBV detalleTBV) {
		boolean resp = false;
		for (DetalleTBV item : tbvDetalle.getItems()) {
			if (item.getInsumoCBX().equals(detalleTBV.getInsumoCBX())) {
				resp = true;
				break;
			}
		}
		return resp;
	}

	@FXML
	private void btnEditarProducto_Action() {
		DetalleTBV selectedItem = tbvDetalle.getSelectionModel().getSelectedItem();
		cbxInsumo.setValue(selectedItem.getInsumoCBX());
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
		int númeroVenta = 0;

		if (dtpFecha.getValue() != null /*&& !txtNIT.getText().isEmpty() */&& !txtNombre.getText().isEmpty()) {
			if (tbvDetalle.getItems().size() > 0) {

				númeroVenta = guardarVenta(convertToDate(dtpFecha.getValue()), txtNombre.getText());

				
				for (DetalleTBV detalleTBV : tbvDetalle.getItems()) {

					guardarDetalleVenta(númeroVenta, detalleTBV.getInsumoCBX().getCodigoInsumo(),
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
	private void btnCancelarCompra_Action() {
		cleanScreen(); // Limpiamos la pantalla
	}

	

	/*
	 * Convierte un valor LocalDate (DatePicker) en java.util.Date
	 */
	private java.util.Date convertToDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
	}

	private int guardarVenta(Date fecha, String nombre) {
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		int aiKey = 0;

		try {

			
			preparedStatement = connection.queryGeneratedKeys("insert into Compra(fecha, nombre) " + "values(?,?)");
			// Convertimos la fecha de java.util en java.sql para guardarla
			preparedStatement.setDate(1, new java.sql.Date(fecha.getTime()));
			preparedStatement.setString(2, txtNombre.getText());
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

	private void guardarDetalleVenta(int numero, int codigoInsumo, int cantidad) {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection
					.query("insert into detalleventa(numero, codigoInsumo, cantidad) " + "values(?,?,?)");
			preparedStatement.setInt(1, numero);
			preparedStatement.setInt(2, codigoInsumo);
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

	public void loadcbxInsumo() {
		try {
			PreparedStatement preparedStatement = connection.query("Select * from producto");
			ResultSet resultSet = preparedStatement.executeQuery();
			InsumoCBX insumoCBX = null;

			/*
			 * Se carga el comboBox con Producto
			 */
			while (resultSet.next()) {
				insumoCBX = new InsumoCBX(resultSet.getInt("codigoInsumo"), resultSet.getString("nombre"),
						resultSet.getDouble("precio"), resultSet.getString("descripcion"),
						resultSet.getString("tamano"));
				cbxInsumo.getItems().add(insumoCBX);
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
	
		txtNombre.setText("");
		cbxInsumo.setValue(null);
		txtCantidad.setText("");
		tbvDetalle.getItems().clear();
	}

	class InsumoCBX extends model.Insumo {

		public InsumoCBX(int códigoProducto, String nombre, Double precio, String descripcion, String tamano) {
			super(códigoProducto, nombre, precio, descripcion, tamano);
		}

		@Override
		public String toString() {
			return " "+ getNombre() + " .Tamaño : " + getTamano() ;
		}
		
		
	}

	public class DetalleTBV {
		private InsumoCBX insumoCBX;
		private Integer cantidad;

		public DetalleTBV(InsumoCBX insumoCBX, Integer cantidad) {
			this.insumoCBX = insumoCBX;
			this.cantidad = cantidad;
		}

		public InsumoCBX getInsumoCBX() {
			return insumoCBX;
		}

		public void setInsumoCBX(InsumoCBX insumoCBX) {
			this.insumoCBX = insumoCBX;
		}

		public Integer getCantidad() {
			return cantidad;
		}

		public void setCantidad(Integer cantidad) {
			this.cantidad = cantidad;
		}
	}

	



}
