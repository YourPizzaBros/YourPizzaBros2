package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;

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
					cleanScreenCliente();
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
			txtCapacidad.setText(" ");
			
			
		}
	

		private boolean registrado(String nombre) {
		
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

	
	
