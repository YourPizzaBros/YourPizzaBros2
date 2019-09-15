package controller;


<<<<<<< HEAD
import javafx.fxml.FXML;
//import javafx.scene.control.Label;
=======
>>>>>>> Andy
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	private String nombreCliente;
	private String NITCliente;
	

	private String apellido;
	
	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getNITCliente() {
		return NITCliente;
	}

	public void setNITCliente(String nITCliente) {
		NITCliente = nITCliente;
	}



	@FXML
	private Button btnContinuar;
	

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;

	}
	
	
	@FXML
	private void btnRegistrarCliente_Action() {
		PreparedStatement preparedStatement = null;
		if (!txtNITCliente.getText().isEmpty() && !txtNombreCliente.getText().isEmpty()) {
			try {
				preparedStatement = connection.query("insert into cliente(nit, ApellidoC) values(?,?)");
				preparedStatement.setString(1, txtNITCliente.getText());
				preparedStatement.setString(2, txtNombreCliente.getText());
				preparedStatement.executeUpdate();
				setNombreCliente(txtNombreCliente.getText());
				setNITCliente(txtNITCliente.getText());
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
				preparedStatement = connection.query("update cliente set ApellidoC = ? where nit = ?");
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
		 apellido = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.query("select ApellidoC from cliente " + "where nit = ?");
			preparedStatement.setString(1, NIT);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				apellido = resultSet.getString("ApellidoC");
			}
		} catch (SQLException e) {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error Cliente", e.getMessage());
		}
		return apellido;
	}
<<<<<<< HEAD

	
	
=======
	 //tipo esto da pero ya no
	
	/*
>>>>>>> Andy
	@FXML
	private void btnContinuar_Action() {
		
		
		FormsOperations formsOperations = new FormsOperations();
		
		//ventaFX.loadCBXCategoria();     //esto estaba con producto
		
		//PreparedStatement preparedStatement = null;
		//int rows = 0;
		
		
		
		//String nombre = null;
			if (!txtNITCliente.getText().isEmpty() && !txtNombreCliente.getText().isEmpty()) {
			
				//nombre = buscarNombre(txtNombreCliente.getText());
			
			
			
			if (nombre != null) {
<<<<<<< HEAD
				txtNITCliente.getText();
				setNombreCliente(txtNombreCliente.getText());
				setNITCliente(txtNITCliente.getText());
=======
				//txtNITCliente.getText();
			
>>>>>>> Andy
			
			
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
	
	*/
	

	@FXML
	private void btnContinuar_Action() {
		
		
		FormsOperations formsOperations = new FormsOperations();
		
		//ventaFX.loadCBXCategoria();     //esto estaba con producto
		
		//PreparedStatement preparedStatement = null;
		//int rows = 0;
		
		
		
		//String nombre = null;
			if (!txtNITCliente.getText().isEmpty()) {
			
				//nombre = buscarNombre(txtNombreCliente.getText());
			
			boolean registrado = registrado();
			
			if (registrado) {
				//txtNITCliente.getText();
			
			
			
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
<<<<<<< HEAD
}	
=======
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
	}
	

	}



>>>>>>> Andy
