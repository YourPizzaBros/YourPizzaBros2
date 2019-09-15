package controller;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegistroPFX {

	private Connection connection;
	
	@FXML
	private Button btnActualizarP;
	
	
	
	@FXML
	private TextField txtNombreP;
	@FXML
	private TextField txtDireccionP;

	@FXML
	private TextField txtInsumoP;

	@FXML
	private TextField txtPrecioP;

	
	
	
	//private String nombre;
	
	@FXML
	private Button btnRegistrarProveedor;
	
	@FXML
	private Button btnEliminarProveedor;
	


	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;

	}
	
	
	@FXML
	private void btnRegistrarProveedor_Action() {
		PreparedStatement preparedStatement = null;
		if (!txtNombreP.getText().isEmpty() && !txtDireccionP.getText().isEmpty()  && !txtInsumoP.getText().isEmpty()  && !txtPrecioP.getText().isEmpty()) {
			try {
				preparedStatement = connection.query("insert into proveedor( nombre,direccion,insumo,precio) values(?,?,?,?)");
				preparedStatement.setString(1, txtNombreP.getText());
				preparedStatement.setString(2, txtDireccionP.getText());
				preparedStatement.setString(3, txtInsumoP.getText());
				preparedStatement.setDouble(4, Double.parseDouble(txtPrecioP.getText()));  //aqui diria set String
				preparedStatement.executeUpdate();
		    //  txtNIT.setText(txtNITCliente.getText());
			//	txtNombre.setText(txtNombreCliente.getText());
			//	tbpPanel.getSelectionModel().select(tabVenta);
			} catch (SQLException e) {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Error en Consulta", e.getMessage());
			}
		} else {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error en Cliente", "Debe llenar todos campos");
		}
	}

	@FXML
	private void btnCancelarP_Action() {
		cleanScreenCliente();
	}

	private void cleanScreenCliente() {
		txtNombreP.setText("");
		txtDireccionP.setText("");
		txtInsumoP.setText("");
		txtPrecioP.setText("");
	}

	/*@FXML
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
	}*/
/*
	@FXML
	private void btnActualizarCliente_Action() {
		PreparedStatement preparedStatement = null;
		if (!txtNombre.getText().isEmpty() && !txtDireccion.getText().isEmpty()) {
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
*//*
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



