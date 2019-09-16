package controller;


	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;

	import javafx.fxml.FXML;
	import javafx.fxml.FXMLLoader;
	import javafx.scene.control.Button;
	import javafx.scene.control.TextField;

public class RegistroInsumoFX {

	
	




	
	
	

		private Connection connection;
		
		@FXML
		private Button btnRegistrarProducto;
		
		@FXML
		private Button btnCancelarProducto;
		
		@FXML
		private TextField txtNombreProducto;
		
		@FXML
		private TextField txtPrecioUnidadP;
		@FXML
		private TextField txtDescripcion;

		@FXML
		private TextField txtTamano;

		
		
		
		
		//private String nombre;
		
		/*
		
		@FXML
		private Button btnEliminarProveedor;
		
*/

		public Connection getConnection() {
			return connection;
		}

		public void setConnection(Connection connection) {
			this.connection = connection;

		}
		
		
		@FXML
		private void btnRegistrarProducto_Action() {
			System.out.println("boton registrado");
			PreparedStatement preparedStatement = null;
			
			if (!txtNombreProducto.getText().isEmpty() && !txtPrecioUnidadP.getText().isEmpty() && !txtDescripcion.getText().isEmpty()  && !txtTamano.getText().isEmpty()) {
				try {
					preparedStatement = connection.query("insert into producto( nombre ,precio ,descripcion, tamano) values(?,?,?,?)");
					preparedStatement.setString(1, txtNombreProducto.getText());
					preparedStatement.setString(2, txtPrecioUnidadP.getText());
					preparedStatement.setString(3, txtDescripcion.getText());
					preparedStatement.setString(4, txtTamano.getText());
				//	preparedStatement.setDouble(4, Double.parseDouble(txtPrecioP.getText()));  //aqui diria set String
					preparedStatement.executeUpdate();
			    
				} catch (SQLException e) {
					MessageBox messageBox = new MessageBox();
					messageBox.message("Error en Consulta", e.getMessage());
				}
			} else {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Error en Cliente", "Debe llenar todos los campos");
			}
		}

		@FXML
		private void btnCancelarProducto_Action() {
			cleanScreenCliente();
		}

		private void cleanScreenCliente() {
			txtNombreProducto.setText("");
			txtPrecioUnidadP.setText("");
			txtDescripcion.setText("");
			txtTamano.setText("");
			
		}
	/*
		@FXML
		private void btnBuscarProveedor_Action() {
			String nombre = null;
			if (!txtNITP.getText().isEmpty()) {
				nombre = buscarP(txtNITP.getText());
				if (nombre != null) {
					txtNombreP.setText(nombre);
				} else {
					MessageBox messageBox = new MessageBox();
					messageBox.message("Información", "El número de NIT no se encuentra registrado");
				}

			} else {
				MessageBox messageBox = new MessageBox();
				messageBox.message("NIT", "El Campo de NIT no puede estar vacio");
			}
		}*/
/*
		@FXML
		private void btnActualizarProveedor_Action() {  //falata modificar
			PreparedStatement preparedStatement = null;
			if (!txtNombreP.getText().isEmpty() && !txtDireccionP.getText().isEmpty() && !txtTelefonoP.getText().isEmpty()) {
				try {
					preparedStatement = connection.query("update proveedor set nombreP = ?, telefono = ? , direccion = ?  where NITProveedor = ?");
					preparedStatement.setString(4, txtNITP.getText());
					preparedStatement.setString(3, txtDireccionP.getText());
					preparedStatement.setString(2, txtTelefonoP.getText());
					preparedStatement.setString(1, txtNombreP.getText());
					preparedStatement.executeUpdate();
					MessageBox messageBox = new MessageBox();
					messageBox.message("Información", "Proveedor actualizado con éxito");
				} catch (SQLException e) {
					MessageBox messageBox = new MessageBox();
					messageBox.message("Error en Consulta", e.getMessage());
				}
			} else {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Error en Cliente", "Debe llenar ambos campos");
			}
		}*/
/*
		@FXML
		private void btnEliminarProveedor_Action() {
			PreparedStatement preparedStatement = null;
			int rows = 0;

			if (!txtNITP.getText().isEmpty()) {
				try {
					preparedStatement = connection.query("delete from proveedor where NITProveedor = ?");
					preparedStatement.setString(1, txtNITP.getText());
					rows = preparedStatement.executeUpdate();
					MessageBox messageBox = new MessageBox();
					if (rows > 0) {
						messageBox.message("Información", "Proveedor eliminado con éxito");
					} else {
						messageBox.message("Información", "El proveedor no esta registrado");
					}
				} catch (SQLException e) {
					MessageBox messageBox = new MessageBox();
					messageBox.message("Error en Consulta", e.getMessage());
				}
			} else {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Error en Cliente", "Debe llenar el campo NIT");
			}
		}*/
		/*
		
		private String buscarNombre(String NIT) {
			 nombre = null;
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
		 */

		/*
		private boolean registrado() {
			PreparedStatement preparedStatement = null;
			boolean registrado= false;
			
				try {
					preparedStatement = connection.query("insert into cliente(nit, nombre) values(?,?)");
					preparedStatement.setString(1, txtNITCliente.getText());
					preparedStatement.setString(2, txtNombreCliente.getText());
					preparedStatement.executeUpdate();
			   
				} catch (SQLException e) {
					//MessageBox messageBox = new MessageBox();
					//messageBox.message("Error en Consulta", e.getMessage());
					registrado=true;
				}
			
			return registrado;
		}*/
		

		
		
		
		
		




	
	


}
