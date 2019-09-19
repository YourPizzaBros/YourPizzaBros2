package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import controller.RegistroRelacionPCFX.InsumoCBX;
import controller.RegistroRelacionPCFX.ProveedorCBX;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

public class RegistroRelacionPCFX {
	private Connection connection;
	/*@FXML
	private DatePicker dtpFecha;
	*/
	@FXML
	private TabPane tbpPanel;
	@FXML
	private Tab tabRelacion;
	@FXML
	private Tab tabBC;
	
	@FXML
	private TextField txtCodInsumo;  //pp
	@FXML
	private TextField txtCodProv; //pp
	
	@FXML
	private Button btnRegistrar;
	@FXML
	private Button btnCancelar;
	
	@FXML
	private Button btnActualizar;
	
	@FXML
	private TextField txtPrecio;
	
	
	@FXML
	private TextField txtCodigoI;//sp
	@FXML
	private TextField txtCodigoP;//sp
	
	@FXML
	private ComboBox <ProveedorCBX> cbxNombreP;
	
	/*@FXML
	private ComboBox <ProductoTamanoCBX> cbxTamanoProducto;*/
	
	@FXML
	private ComboBox <InsumoCBX> cbxNombreI;
	
	@FXML
	private Button btnBuscarP;
	@FXML
	private Button btnBuscarI;
	
	
	
	
	
	
	
	@FXML
	private void initialize() {
		
		
	}

	

	@FXML
	private void btnCancelar_Action() {
		cleanScreen(); // Limpiamos la pantalla
	}


	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	

	public void loadcbxProveedor() {
		try {
			PreparedStatement preparedStatement = connection.query("Select * from proveedor");
			ResultSet resultSet = preparedStatement.executeQuery();
			ProveedorCBX provCBX = null;

			while (resultSet.next()) {
<<<<<<< HEAD
				productoCBX = new ProductoCBX(resultSet.getInt("codProducto"), resultSet.getString("nombre"),
						resultSet.getDouble("precio"), resultSet.getString("descripcion"),
						resultSet.getString("tamano"), resultSet.getInt("cantidad"));
				cbxNombreProducto.getItems().add(productoCBX);
=======
				provCBX = new ProveedorCBX(resultSet.getInt("NITProveedor"), resultSet.getString("NombreP"),
						resultSet.getString("Telefono"), resultSet.getString("Direccion"));
				cbxNombreP.getItems().add(provCBX);
>>>>>>> Andy
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
				insumoCBX = new InsumoCBX(resultSet.getInt("codInsumo"), resultSet.getString("nombre"), resultSet.getString("descripcion"),resultSet.getInt("cantidad"));
				cbxNombreI.getItems().add(insumoCBX);
			}

		} catch (SQLException e) {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error", e.getMessage());
		}
	}


	private void cleanScreen() {
		//now();
		txtCodProv.setText(" ");
		txtCodInsumo.setText(" ");
		txtCodigoP.setText(" ");
		txtCodigoI.setText(" ");
		
	}

	class ProveedorCBX extends model.Proveedor {

<<<<<<< HEAD
		public ProductoCBX(int codigoProducto, String nombre, Double precio, String descripción, String tamano, int cantidad) {
			super(codigoProducto, nombre, precio, descripción, tamano, cantidad);
=======
		public ProveedorCBX(int codigoC, String nombreC, String TelC, String direccionC) {
			super(codigoC, nombreC, TelC, direccionC);
			// TODO Auto-generated constructor stub
>>>>>>> Andy
		}


		

		
		@Override
		public String toString() {
			return "" + getNombreP() ;
		}
		
	
		
	}
	
	
	
	class InsumoCBX extends model.Insumo {

		public InsumoCBX(int codigoProducto, String nombre, String descripción,int cantidad) {
			super(codigoProducto, nombre, descripción,cantidad);
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return getNombre() ;
		}
	}

	
	

	
	@FXML
	private void btnBuscarP_Action() {
		String codigo = null;
		
		
		if (cbxNombreP.getValue() != null) {
			
			
			codigo = buscarProveedor(cbxNombreP.getValue().getNombreP(),cbxNombreP.getValue().getTelefono());
			if (codigo != null) {
				txtCodigoP.setText(codigo);
				txtCodProv.setText(codigo);
			} else {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Información", "El proveedor no se encuentra registrado");
			}

		} else {
			MessageBox messageBox = new MessageBox();
			messageBox.message("NIT", "El Campo de nombre Proveedor no puede estar vacio");
		}
	}
	
	
	@FXML
	private void btnBuscarI_Action() {
		String codigo = null;
		
		
		if (cbxNombreI.getValue() != null) {
			
			System.out.println("entro en aqi1");
			codigo = buscarInsumo(cbxNombreI.getValue().getNombre());
			System.out.println(codigo);
			if (codigo != null) {
				txtCodigoI.setText(codigo);
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
	
	
	
	private String buscarProveedor (String nombre, String telefono) {
		  //= null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String codigo=null;

		try {
			preparedStatement = connection.query("select proveedor.NITProveedor from proveedor " + " where proveedor.NombreP = ? " + 
					" and proveedor.Telefono = ? ");
			preparedStatement.setString(1, nombre);
			preparedStatement.setString(2, telefono);
			//preparedStatement.setString(3, precio);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				
				codigo = resultSet.getString(1);
			}
			
		} catch (SQLException e) {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error Proveedor", e.getMessage());
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
	private void btnRegistrar_Action() {
		PreparedStatement preparedStatement = null;
		if (!txtCodProv.getText().isEmpty() && !txtCodInsumo.getText().isEmpty() && !txtPrecio.getText().isEmpty()) {
			boolean registrado =  false;
			registrado=registrado( txtCodInsumo.getText(), txtCodProv.getText());
			
			if(!registrado) {
			try {
				preparedStatement = connection.query("insert into relacion(codInsumo, NITProveedor , precio) values(?,?,?)");
			
				preparedStatement.setString(1, txtCodInsumo.getText());
				preparedStatement.setString(2, txtCodProv.getText());
				preparedStatement.setString(3, txtPrecio.getText());
				preparedStatement.executeUpdate();
				txtCodProv.setText(txtCodigoP.getText());
				txtCodInsumo.setText(txtCodigoI.getText());
				tbpPanel.getSelectionModel().select(tabRelacion);   ///decia tabVneta
				cleanScreen();
			} catch (SQLException e) {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Error en Consulta", e.getMessage());
			}
			}
			
			else {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Error en Consulta", "relacion ya registrada anteriormente");	
			}
			
			
		}
		
		
		
		
		else {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error en Cliente", "Debe llenar todos los campos");
		}
		
		
		
		
		
	}
	
	private boolean registrado(String codigoInsumo, String codigoProv) {
		  //= null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean registrado=false;

		try {
			preparedStatement = connection.query("select * from relacion " + "where relacion.codInsumo like ?  && relacion.NITProveedor like ? ");
			preparedStatement.setString(1, codigoInsumo);
			preparedStatement.setString(2, codigoProv);
		
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
	private void btnActualizarRelacion_Action() {
		PreparedStatement preparedStatement = null;
		if (!txtCodigoP.getText().isEmpty() && !txtCodigoI.getText().isEmpty()) {
			try {
				preparedStatement = connection.query("update receta set precio = ? where codInsumo = ? && codProducto = ?");
				preparedStatement.setString(1, txtPrecio.getText());
				preparedStatement.setString(2, txtCodInsumo.getText());
				preparedStatement.setString(3, txtCodProv.getText());
				preparedStatement.executeUpdate();
				MessageBox messageBox = new MessageBox();
				messageBox.message("Información", "Relacion actualizada con éxito");
			} catch (SQLException e) {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Error en Consulta", e.getMessage());
			}
		} else {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error en Relacion", "Debe llenar ambos campos");
		}
	}


}

