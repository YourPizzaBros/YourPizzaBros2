
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


import javafx.scene.control.TextField;


import java.time.LocalDate;
import java.util.Date;

public class RegistroResetaFX {

	


		
		
		
		
		private Connection connection;
		/*@FXML
		private DatePicker dtpFecha;
		*/
		@FXML
		private TabPane tbpPanel;
		@FXML
		private Tab tabReceta;
		@FXML
		private Tab tabBC;
		
		@FXML
		private TextField txtCodProducto;  //pp
		@FXML
		private TextField txtCodInsumo; //pp
		
		@FXML
		private Button btnRegistrarRR;
		@FXML
		private Button btnCancelarRR;
		
		
		
		
		
		@FXML
		private TextField txtCodigoProducto;//sp
		@FXML
		private TextField txtCodigoInsumo;//sp
		
		@FXML
		private ComboBox <ProductoCBX> cbxNombreProducto;
		
		/*@FXML
		private ComboBox <ProductoTamanoCBX> cbxTamanoProducto;*/
		
		@FXML
		private ComboBox <InsumoCBX> cbxNombreInsumo;
		
		@FXML
		private Button btnBuscarProducto;
		@FXML
		private Button btnBuscarInsumo;
		
		@FXML
		private TextField txtCantidad;
		
		
		/*@FXML
		private TableView<DetalleTBV> tbvDetalle;
		@FXML
		private TableColumn<DetalleTBV, String> tbcProducto;
		@FXML
		private TableColumn<DetalleTBV, String> tbcCantidad;
		*/
		
		/*
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
		
		*/
		
		
		
		
		
		@FXML
		private void initialize() {
			
			
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
			}
		}*/
/*
		@FXML
		private void btnAñadirProducto_Action() {
			
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
		}*/
/*
		private boolean buscarDetalle(DetalleTBV detalleTBV) {
			boolean resp = false;
			for (DetalleTBV item : tbvDetalle.getItems()) {
				if (item.getProductoCBX().equals(detalleTBV.getProductoCBX())) {
					resp = true;
					break;
				}
			}
			return resp;
		}*/

		/*
		@FXML
		private void btnRemoverProducto_Action() {
			DetalleTBV selectedItem = tbvDetalle.getSelectionModel().getSelectedItem();
			tbvDetalle.getItems().remove(selectedItem);
		}*/

		
		

		@FXML
		private void btnCancelarRR_Action() {
			cleanScreen(); // Limpiamos la pantalla
		}
/*
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
		}*/

		/*
		 * Convierte un valor LocalDate (DatePicker) en java.util.Date
		 */
		/*private java.util.Date convertToDate(LocalDate localDate) {
			return Date.from(localDate.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
		}*/

		
		

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
							resultSet.getString("tamano"));
					cbxNombreProducto.getItems().add(productoCBX);
				}

			} catch (SQLException e) {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Error", e.getMessage());
			}
		}
		
		
		
		
		public void loadcbxInsumo() {
			try {
				PreparedStatement preparedStatement = connection.query("Select * from insumo");
				ResultSet resultSet = preparedStatement.executeQuery();
				InsumoCBX insumoCBX = null;

				
				while (resultSet.next()) {
					insumoCBX = new InsumoCBX(resultSet.getInt("codInsumo"), resultSet.getString("nombre"), resultSet.getString("descripcion"));
					cbxNombreInsumo.getItems().add(insumoCBX);
				}

			} catch (SQLException e) {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Error", e.getMessage());
			}
		}


		/*public void now() {

			dtpFecha.setValue(LocalDate.now());
		}*/

		private void cleanScreen() {
			//now();
			txtCodProducto.setText("");
			txtCodInsumo.setText("");
			
		}

		class ProductoCBX extends model.Producto {

			public ProductoCBX(int codigoProducto, String nombre, Double precio, String descripción, String tamano) {
				super(codigoProducto, nombre, precio, descripción, tamano);
			}

			
			@Override
			public String toString() {
				return "" + getNombre() + "- Tamaño : " + getTamano();
			}
			
		
			
		}
		
		
		
		class InsumoCBX extends model.Insumo {

			public InsumoCBX(int codigoProducto, String nombre, String descripción) {
				super(codigoProducto, nombre, descripción);
				// TODO Auto-generated constructor stub
			}

			@Override
			public String toString() {
				return getNombre() ;
			}
		}

		

		

		
/*
		@FXML
		private void btnCancelarCliente_Action() {
			cleanScreenCliente();
		}

		private void cleanScreenCliente() {
			txtNITCliente.setText("");
			txtNombreCliente.setText("");
		}*/

		
		@FXML
		private void btnBuscarProducto_Action() {
			String codigo = null;
			
			
			if (cbxNombreProducto.getValue() != null) {
				
				
				codigo = buscarProducto(cbxNombreProducto.getValue().getNombre(),cbxNombreProducto.getValue().getTamano());
				if (codigo != null) {
					txtCodigoProducto.setText(codigo);
					txtCodProducto.setText(codigo);
				} else {
					MessageBox messageBox = new MessageBox();
					messageBox.message("Información", "El producto no se encuentra registrado");
				}

			} else {
				MessageBox messageBox = new MessageBox();
				messageBox.message("NIT", "El Campo de nombre Producto no puede estar vacio");
			}
		}
		
		
		@FXML
		private void btnBuscarInsumo_Action() {
			String codigo = null;
			
			
			if (cbxNombreInsumo.getValue() != null) {
				
				System.out.println("entro en aqi1");
				codigo = buscarInsumo(cbxNombreInsumo.getValue().getNombre());
				System.out.println(codigo);
				if (codigo != null) {
					txtCodigoInsumo.setText(codigo);
					txtCodInsumo.setText(codigo);
				} else {
					MessageBox messageBox = new MessageBox();
					messageBox.message("Información", "El Insumo no se encuentra registrado");
				}

			} else {
				MessageBox messageBox = new MessageBox();
				messageBox.message("NIT", "El Campo de nombre Insumo no puede estar vacio");
			}
		}
		
		
		
		private String buscarProducto (String nombre, String tamano) {
			  //= null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String codigo=null;

			try {
				preparedStatement = connection.query("select producto.codProducto from producto " + " where producto.Nombre = ? " + 
						" and producto.Tamano = ? ");
				preparedStatement.setString(1, nombre);
				preparedStatement.setString(2, tamano);
				//preparedStatement.setString(3, precio);
				resultSet = preparedStatement.executeQuery();
				
				if (resultSet.next()) {
					
					codigo = resultSet.getString(1);
				}
				
			} catch (SQLException e) {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Error Producto", e.getMessage());
			}
			return codigo;
		}
		
		private String buscarInsumo (String nombre) {
			  //= null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String codigo = null;

			try {
				preparedStatement = connection.query("select insumo.codInsumo from insumo " + " where insumo.Nombre = ?  ");
				preparedStatement.setString(1, nombre);
				
				resultSet = preparedStatement.executeQuery();
				
				if (resultSet.next()) {
					
					codigo = resultSet.getString(1);
				}
				
			} catch (SQLException e) {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Error Insumo", e.getMessage());
			}
			
			return codigo;
		}
		@FXML
		private void btnRegistrarRR_Action() {
			PreparedStatement preparedStatement = null;
			if (!txtCodProducto.getText().isEmpty() && !txtCodInsumo.getText().isEmpty() && !txtCantidad.getText().isEmpty()) {
				boolean registrado =  false;
				registrado=registrado( txtCodInsumo.getText(), txtCodProducto.getText());
				
				if(!registrado) {
				try {
					preparedStatement = connection.query("insert into receta(codInsumo, codProducto , cantidad) values(?,?,?)");
					System.out.println(txtCodInsumo.getText());
					preparedStatement.setString(1, txtCodInsumo.getText());
					preparedStatement.setString(2, txtCodProducto.getText());
					preparedStatement.setString(3, txtCantidad.getText());
					preparedStatement.executeUpdate();
					txtCodProducto.setText(txtCodigoProducto.getText());
					txtCodInsumo.setText(txtCodigoInsumo.getText());
					tbpPanel.getSelectionModel().select(tabReceta);   ///decia tabVneta
				} catch (SQLException e) {
					MessageBox messageBox = new MessageBox();
					messageBox.message("Error en Consulta", e.getMessage());
				}
				}
				
				else {
					MessageBox messageBox = new MessageBox();
					messageBox.message("Error en Consulta", "receta ya registrada anteriormente");	
				}
				
				
			}
			
			
			
			
			else {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Error en Cliente", "Debe llenar todos los campos");
			}
			
			
			
			
			
		}
		
		private boolean registrado(String codigoInsumo, String codigoProducto) {
			  //= null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			boolean registrado=false;

			try {
				preparedStatement = connection.query("select * from receta " + "where receta.codInsumo like ?  && receta.codProducto like ? ");
				preparedStatement.setString(1, codigoInsumo);
				preparedStatement.setString(2, codigoProducto);
			
				resultSet = preparedStatement.executeQuery();
				
				if (!resultSet.next()) {
					
					registrado= false;
				}
				else {
					registrado=true;
				}
			} catch (SQLException e) {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Error Insumo", e.getMessage());
			}
			return registrado;
		}
		
		@FXML
		private void btnActualizarReceta_Action() {
			PreparedStatement preparedStatement = null;
			if (!txtCodigoProducto.getText().isEmpty() && !txtCodigoInsumo.getText().isEmpty()) {
				try {
					preparedStatement = connection.query("update receta set cantidad = ? where codInsumo = ? && codProducto = ?");
					preparedStatement.setString(1, txtCantidad.getText());
					preparedStatement.setString(2, txtCodInsumo.getText());
					preparedStatement.setString(3, txtCodProducto.getText());
					preparedStatement.executeUpdate();
					MessageBox messageBox = new MessageBox();
					messageBox.message("Información", "Receta actualizada con éxito");
				} catch (SQLException e) {
					MessageBox messageBox = new MessageBox();
					messageBox.message("Error en Consulta", e.getMessage());
				}
			} else {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Error en Receta", "Debe llenar ambos campos");
			}
		}


	}

	


	
	
	

