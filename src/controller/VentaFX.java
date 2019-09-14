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
	private Tab tabCliente;
	@FXML
	private Tab tabVenta;
	@FXML
	private Button btnRegistrarCliente;
	@FXML
	private Button btnCancelarCliente;
	@FXML
	private Button btnBuscarCliente;
	@FXML
	private Button btnActualizarCliente;
	@FXML
	private Button btnEliminarCliente;
	@FXML
	private TextField txtNITCliente;
	@FXML
	private TextField txtNombreCliente;

	@FXML
	private void initialize() {
		/*
		 * Se define que tipo de datos guardará cada columna
		 */
		tbcProducto.setCellValueFactory(new PropertyValueFactory<>("productoCBX"));
		tbcCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
	}

	@FXML
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
		}
	}

	@FXML
	private void btnAñadirProducto_Action() {
		/*
		 * Se añaden los items que se encuentran en cbxProducto y en el txtCantidad
		 */
		if (cbxProducto.getValue() != null && !txtCantidad.getText().isEmpty()) { // Se verifica si ambos no estan
																					// vacios
			DetalleTBV detalleTBV = new DetalleTBV(cbxProducto.getValue(), Integer.parseInt(txtCantidad.getText()));
			if (!buscarDetalle(detalleTBV)) {
				// Añade el objeto detalleTBV en la tabla
				tbvDetalle.getItems().add(detalleTBV);
				// Despues de añadir, ambos cambian a estado vacio
				cbxProducto.setValue(null);
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
		int númeroVenta = 0;

		if (dtpFecha.getValue() != null && !txtNIT.getText().isEmpty() && !txtNombre.getText().isEmpty()) {
			if (tbvDetalle.getItems().size() > 0) {

				númeroVenta = guardarVenta(convertToDate(dtpFecha.getValue()), txtNIT.getText());

				// Recorremos todos los elementos del objeto TableView
				for (DetalleTBV detalleTBV : tbvDetalle.getItems()) {

					guardarDetalleVenta(númeroVenta, detalleTBV.getProductoCBX().getCódigoProducto(),
							detalleTBV.getCantidad());
				}
				cleanScreen(); // Limpiamos la pantalla
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

			// Utilizamos el método queryGeneratedKeys para que nos devuelva la clava
			// generada
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

	private void guardarDetalleVenta(int número, int códigoProducto, int cantidad) {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection
					.query("insert into detalleventa(número, códigoProducto, cantidad) " + "values(?,?,?)");
			preparedStatement.setInt(1, número);
			preparedStatement.setInt(2, códigoProducto);
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
				productoCBX = new ProductoCBX(resultSet.getInt("códigoProducto"), resultSet.getString("nombre"),
						resultSet.getDouble("precio"), resultSet.getString("descripción"),
						resultSet.getInt("códigoCategoría"));
				cbxProducto.getItems().add(productoCBX);
			}

		} catch (SQLException e) {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error", e.getMessage());
		}
	}

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

		public ProductoCBX(int códigoProducto, String nombre, Double precio, String descripción, int códigoCategoría) {
			super(códigoProducto, nombre, precio, descripción, códigoCategoría);
		}

		@Override
		public String toString() {
			return getNombre();
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

	/***********************************************************
	 * 
	 * Métodos de pestaña de Cliente
	 * 
	 * *********************************************************
	 */

	@FXML
	private void btnRegistrarCliente_Action() {
		PreparedStatement preparedStatement = null;
		if (!txtNITCliente.getText().isEmpty() && !txtNombreCliente.getText().isEmpty()) {
			try {
				preparedStatement = connection.query("insert into cliente(nit, nombre) values(?,?)");
				preparedStatement.setString(1, txtNITCliente.getText());
				preparedStatement.setString(2, txtNombreCliente.getText());
				preparedStatement.executeUpdate();
				txtNIT.setText(txtNITCliente.getText());
				txtNombre.setText(txtNombreCliente.getText());
				tbpPanel.getSelectionModel().select(tabVenta);
			} catch (SQLException e) {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Error en Consulta", e.getMessage());
			}
		} else {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error en Cliente", "Debe llenar ambos campos");
		}
	}

	@FXML
	private void btnCancelarCliente_Action() {
		cleanScreenCliente();
	}

	private void cleanScreenCliente() {
		txtNITCliente.setText("");
		txtNombreCliente.setText("");
	}

	@FXML
	private void btnBuscarCliente_Action() {
		String nombre = null;
		if (!txtNITCliente.getText().isEmpty()) {
			nombre = buscarNombre(txtNITCliente.getText());
			if (nombre != null) {
				txtNombreCliente.setText(nombre);
			} else {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Información", "El número de NIT no se encuentra registrado");
			}

		} else {
			MessageBox messageBox = new MessageBox();
			messageBox.message("NIT", "El Campo de NIT no puede estar vacio");
		}
	}

	@FXML
	private void btnActualizarCliente_Action() {
		PreparedStatement preparedStatement = null;
		if (!txtNITCliente.getText().isEmpty() && !txtNombreCliente.getText().isEmpty()) {
			try {
				preparedStatement = connection.query("update cliente set nombre = ? where nit = ?");
				preparedStatement.setString(2, txtNITCliente.getText());
				preparedStatement.setString(1, txtNombreCliente.getText());
				preparedStatement.executeUpdate();
				MessageBox messageBox = new MessageBox();
				messageBox.message("Información", "Cliente actualizado con éxito");
			} catch (SQLException e) {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Error en Consulta", e.getMessage());
			}
		} else {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error en Cliente", "Debe llenar ambos campos");
		}
	}

	@FXML
	private void btnEliminarCliente_Action() {
		PreparedStatement preparedStatement = null;
		int rows = 0;

		if (!txtNITCliente.getText().isEmpty()) {
			try {
				preparedStatement = connection.query("delete from cliente where nit = ?");
				preparedStatement.setString(1, txtNITCliente.getText());
				rows = preparedStatement.executeUpdate();
				MessageBox messageBox = new MessageBox();
				if (rows > 0) {
					messageBox.message("Información", "Cliente eliminado con éxito");
				} else {
					messageBox.message("Información", "El cliente no esta registrado");
				}
			} catch (SQLException e) {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Error en Consulta", e.getMessage());
			}
		} else {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error en Cliente", "Debe llenar el campo NIT");
		}
	}

}
