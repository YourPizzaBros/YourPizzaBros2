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
		private Button btnRegistrarInsumo;
		
		@FXML
		private Button btnCancelarInsumo;
		
		@FXML
		private TextField txtNombreInsumo;
		/*
		@FXML
		private TextField txtPrecioUnidadP;
		*/
		
		@FXML
		private TextField txtDescripcionInsumo;

		
		
		
		
		
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
		private void btnRegistrarInsumo_Action() {
			
			PreparedStatement preparedStatement = null;
			boolean registradoI =false;
			if (!txtNombreInsumo.getText().isEmpty() && !txtDescripcionInsumo.getText().isEmpty() ) {  ///aqui vendria a entrar lo que es precio
				
				registradoI=registrado(txtNombreInsumo.getText());
			
				if(!registradoI) {
				
				
				try {
					preparedStatement = connection.query("insert into insumo( nombre ,descripcion, cantidad) values(?,?,0)");
					preparedStatement.setString(1, txtNombreInsumo.getText());
					preparedStatement.setString(2, txtDescripcionInsumo.getText());
					//preparedStatement.setString(3, txtDescripcion.getText());
				
				//	preparedStatement.setDouble(4, Double.parseDouble(txtPrecioP.getText()));  //aqui diria set String
					preparedStatement.executeUpdate();
			    
				} catch (SQLException e) {
					MessageBox messageBox = new MessageBox();
					messageBox.message("Error en Consulta", e.getMessage());
				}
				
				
				
			     }
				
				else{
					
					MessageBox messageBox = new MessageBox();
					messageBox.message("Error en Registro", "Insumo Ya Registrado");
					
					
				}
				
			}
				
				
				else {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Error en Cliente", "Debe llenar todos los campos");
			}
		}

		@FXML
		private void btnCancelarInsumo_Action() {
			cleanScreenCliente();
		}

		private void cleanScreenCliente() {
			txtNombreInsumo.setText("");
			//txtPrecioUnidadP.setText("");
			txtDescripcionInsumo.setText("");
			
			
		}
	/*
		@FXML
		private void btnBuscarInsumo_Action() {
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
		
		private String buscarCodigo(String nombre) {
			 cod = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				preparedStatement = connection.query("select insumo.codInsumo from insumo " + "where insumo.nombre = ?");
				preparedStatement.setString(1, nombre);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					cod = resultSet.getString("nombre");
				}
			} catch (SQLException e) {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Error Cliente", e.getMessage());
			}
			return nombre;
		}
		 */

		
		private boolean registrado(String nombre) {
			  //= null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			boolean registrado=false;

			try {
				preparedStatement = connection.query("select insumo.codInsumo from insumo " + "where insumo.nombre like ?  ");
				preparedStatement.setString(1, nombre);
			
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
		

		
		
		
		
		




	
	


}
