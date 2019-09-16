package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
public class RegistroMesaFX {

	

		private Connection connection;
		
		@FXML
		private Button btnRegistrarMesa;
		
		@FXML
		private Button btnCancelarMesa;
		
		
		
		@FXML
		private TextField txtCapacidad;

		
		


		public Connection getConnection() {
			return connection;
		}

		public void setConnection(Connection connection) {
			this.connection = connection;

		}
		
		
		@FXML
		private void btnRegistrarMesa_Action() {
			
			
			PreparedStatement preparedStatement = null;
			boolean registradoI =false;
			
			if (!txtCapacidad.getText().isEmpty()  ) {  ///aqui vendria a entrar lo que es precio
				
				registradoI=registrado(txtCapacidad.getText());
			
				if(!registradoI) {
				
				
				try {
					preparedStatement = connection.query("insert into mesa( capacidad, estado) values(?, 'Desocupado')");
					preparedStatement.setString(1, txtCapacidad.getText());
					
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
			txtCapacidad.setText("");
			//txtPrecioUnidadP.setText("");
			
			
			
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
					messageBox.message("Informaci�n", "El n�mero de NIT no se encuentra registrado");
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
					messageBox.message("Informaci�n", "Proveedor actualizado con �xito");
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
						messageBox.message("Informaci�n", "Proveedor eliminado con �xito");
					} else {
						messageBox.message("Informaci�n", "El proveedor no esta registrado");
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

	
	

