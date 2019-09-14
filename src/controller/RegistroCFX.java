package controller;

<<<<<<< HEAD
<<<<<<< HEAD
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RegistroCFX {
	private Connection connection;

=======
=======
//<<<<<<< HEAD
>>>>>>> Andy
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegistroCFX {

	private Connection connection;
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
	private Button btnContinuar;
	
<<<<<<< HEAD
	
	
	
>>>>>>> Andy
=======


>>>>>>> Andy
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
<<<<<<< HEAD
<<<<<<< HEAD

	}

=======
=======

>>>>>>> Andy
	}
	
	
	@FXML
	private void btnRegistrarCliente_Action() {
		PreparedStatement preparedStatement = null;
		if (!txtNITCliente.getText().isEmpty() && !txtNombreCliente.getText().isEmpty()) {
			try {
				preparedStatement = connection.query("insert into cliente(nit, nombre) values(?,?)");
				preparedStatement.setString(1, txtNITCliente.getText());
				preparedStatement.setString(2, txtNombreCliente.getText());
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
	
<<<<<<< HEAD
>>>>>>> Andy
}
=======
	
	
	@FXML
	private void btnContinuar_Action() {
		
		
		FormsOperations formsOperations = new FormsOperations();
		
		//ventaFX.loadCBXCategoria();     //esto estaba con producto
		
		//PreparedStatement preparedStatement = null;
		//int rows = 0;
		
		
		
		String nombre = null;
			if (!txtNITCliente.getText().isEmpty()) {
			nombre = buscarNombre(txtNombreCliente.getText());
			if (nombre != null) {
				txtNITCliente.getText();
			
			
			
			FXMLLoader fXMLLoader = formsOperations.OpenForm ("Pedir o pagar" , "/view/ElegirFX.fxml");
			
			ElegirFX ElegirFX = fXMLLoader.getController();
			ElegirFX.setConnection(connection);
			
			
			
			
			} else {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Información", "El número de NIT no se encuentra registrado");
			}

		} else {
			MessageBox messageBox = new MessageBox();
			messageBox.message("NIT", "El Campo de NIT no puede estar vacio");
		}
			
			
			
			
			
			

}	
	
	
	


	}



>>>>>>> Andy
