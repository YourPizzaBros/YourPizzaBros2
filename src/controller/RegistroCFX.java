package controller;



import javafx.fxml.FXML;
//import javafx.scene.control.Label;
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
	
	

	private String apellido;
	private String nit;
	
	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
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


	@FXML
	private void btnContinuar_Action() {
		
		
		FormsOperations formsOperations = new FormsOperations();
		
		
			if (!txtNITCliente.getText().isEmpty()) {
			
			
			
			boolean registrado = registrado();
		
			
			if (registrado) {
				
			
			
			FXMLLoader fXMLLoader = formsOperations.OpenForm ("Pedir o pagar" , "/view/ElegirFX.fxml");
			
			ElegirFX ElegirFX = fXMLLoader.getController();
			ElegirFX.setConnection(connection);
		
			ElegirFX.setClienteNIT(txtNITCliente.getText());
			
			
			
			} else {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Información", "El número de NIT no se encuentra registrado");
			}

		} else {
			MessageBox messageBox = new MessageBox();
			messageBox.message("NIT", "El Campo de NIT no puede estar vacio");
		}
		
				


	
	}
	private boolean registrado() {
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		boolean registrado= false;
		
			try {
				preparedStatement = connection.query("select cliente.apellidoC from cliente "
						+ "  where cliente.nit = ?" );
				preparedStatement.setString(1, txtNITCliente.getText());
			//	preparedStatement.setString(2, txtNombreCliente.getText());
			 resultset =preparedStatement.executeQuery();
				
				if ( resultset.next()) {
					registrado=true;
					
				}
				
		   
			} catch (SQLException e) {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Error en Consulta Registrado", e.getMessage());
				
			}
		
		return registrado;
	}


}

