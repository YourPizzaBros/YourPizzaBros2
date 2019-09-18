package controller;


import java.time.LocalDate;

import controller.VentaFX.DetalleTBV;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ComprarInsumosFX {
	private Connection connection;
	
	@FXML
	private DatePicker dtpFecha;
	
	@FXML
	private ComboBox cbxInsumo;
	
	@FXML
	private ComboBox cbxProveedor;
	
	@FXML
	private TextField txtCantidad;
	
	
	@FXML
	private TableView<DetalleTBV> tbvDetalle;
	@FXML
	private TableColumn<DetalleTBV, String> tbcInsumo;
	@FXML	
	private TableColumn<DetalleTBV, String> tbcCantidad;
	
	@FXML
	private Button btnAñadirInsumo;
	@FXML
	private Button btnEditarInsumo;
	@FXML
	private Button btnRemoverInsumo;
	@FXML
	private Button btnGuardarCompra;
	@FXML
	private Button btnCancelarCompra;
	
	@FXML
	private void initialize() {
		
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public void now() {

		dtpFecha.setValue(LocalDate.now());
	}

	
	
}
