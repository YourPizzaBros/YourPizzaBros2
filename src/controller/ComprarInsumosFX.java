package controller;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import controller.VentaFX.DetalleTBV;
import controller.VentaFX.ProductoCBX;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Insumo;
import model.Proveedor;

public class ComprarInsumosFX {
	private Connection connection;
	
	@FXML
	private DatePicker dtpFecha;
	
	@FXML
	private ComboBox<Insumo> cbxInsumo;
	
	@FXML
	private ComboBox<Proveedor> cbxProveedor;
	
	@FXML
	private TextField txtCantidad;
	
	@FXML
	private Button btnGuardarCompra;
	
	@FXML
	private Button btnCargarP;
	@FXML
	private Button btnCancelarCompra;
	
	@FXML
	private void initialize() {
		
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	

	@FXML
	private void btnComprarInsumo_Action() {
		int numeroCompra = 0;

		if (dtpFecha.getValue() != null  && cbxInsumo.getValue() != null && !txtCantidad.getText().isEmpty()) {
				
				
					guardarDetalleCompra(   getIdRelacion(), convertToDate(dtpFecha.getValue()),   txtCantidad.getText());
				
				//preparedStatement2.setInt(1,  resultSetb.getInt("id_mesa") );
					
					try {
						PreparedStatement preparedStatement2 = connection.query( "update insumo  " + 
								" set cantidad=  ? " + 
								" where insumo.nombre =? " );
						preparedStatement2.setString(1,  txtCantidad.getText());
						preparedStatement2.setString(2, cbxInsumo.getValue().getNombre()  );
						 preparedStatement2.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	
		    	 
					cleanScreen(); 	
				}
		
		else {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error en la venta", "Los campos no deben estar vacios");
				cleanScreen(); 
			}
		} 
    
		
	
	@FXML
	private void btnCancelarCompra_Action() {
		cleanScreen(); 
	}
	
	@FXML
	private void btnCargarP_Action() {
		if(cbxInsumo.getValue() != null) {
			cbxProveedor.getItems().clear();
			this.loadcbxProveedor();
		}
		
		else {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error en la venta", "El campo de Insumo no debe estar vacio");
				cleanScreen(); 
			
		}
		
	}
	
	public void loadcbxInsumo() {
		try {
			//cbxProveedor.getItems().removeAll();
			PreparedStatement preparedStatement = connection.query("Select * from insumo");
			ResultSet resultSet = preparedStatement.executeQuery();
			InsumoCBX insumoCBX = null;

			while (resultSet.next()) {
				insumoCBX = new InsumoCBX(resultSet.getInt("codInsumo"), resultSet.getString("Nombre"),
						resultSet.getString("Descripcion"), resultSet.getInt("Cantidad"));
				cbxInsumo.getItems().add(insumoCBX);
			}

		} catch (SQLException e) {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error", e.getMessage());
		}
	}
	
	public void loadcbxProveedor() {
		try {
			
			PreparedStatement preparedStatement = connection.query("select  " + 
					"proveedor.NITProveedor, " + 
					"proveedor.NombreP, " + 
					"proveedor.Telefono, " +
					"proveedor.Direccion, " + 
					"Relacion.precio " + 
					"from  " + 
					"Relacion  inner join proveedor " + 
					"on relacion.NITProveedor =proveedor.NITProveedor  " + 
					"where Relacion.codInsumo = ?" );
			preparedStatement.setInt(1, cbxInsumo.getValue().getCodigoInsumo());
			
			/*PreparedStatement preparedStatement2 = connection.query("select Relacion.NITProveedor from Relacion "
					+ "  where Relacion.codInsumo = ? " );
			preparedStatement2.setInt(1, cbxInsumo.getValue().getCodigoInsumo());*/
			ResultSet resultSet = preparedStatement.executeQuery();
			ProveedorCBX proveedorCBX = null;

			while (resultSet.next()) {
				proveedorCBX = new ProveedorCBX(resultSet.getInt("NITProveedor"), resultSet.getString("NombreP"),
						resultSet.getString("Telefono"), resultSet.getString("Direccion"));
				cbxProveedor.getItems().add(proveedorCBX);
				
				
			}

		} catch (SQLException e) {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error", e.getMessage());
		}
	}

	
	private void guardarDetalleCompra(int claveRelacion, Date fecha , String cantidad) {
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;

		try {
			preparedStatement = connection.query("insert into DetalleCompra(cantidad, fecha, idRelacion ) " + "values(?,?,?)" );                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
            preparedStatement.setString(1, cantidad);
			preparedStatement.setDate(2, new java.sql.Date(fecha.getTime()));
			preparedStatement.setInt(3, claveRelacion);
	
		preparedStatement.executeUpdate();
        
		
		preparedStatement2  = connection.query("select " + 
							"proveedor.NombreP, " + 
							"insumo.Nombre, " + 
							"relacion.precio " + 
							"from " + 
							"proveedor inner join relacion " + 
							"on proveedor.NITProveedor = relacion.NITProveedor " + 
							"inner join insumo " + 
							"on relacion.codInsumo = insumo.codInsumo " + 
							" " + 
							"where relacion.idRelacion = ? "); 
			
			
			preparedStatement2.setInt(1,claveRelacion  );
			 
		} catch (SQLException e) {
			MessageBox messageBox = new MessageBox();
			messageBox.message("Error en Consulta", e.getMessage());
		}
	}
public int getIdRelacion () {
		
		
		PreparedStatement preparedStatement = null;
		ResultSet resultset = null;
		int id= 0;
		
			try {
				preparedStatement = connection.query("select Relacion.idRelacion from Relacion "
						+ "  where Relacion.NITProveedor = ? "
						+ "&&  Relacion.codInsumo = ? " );
				preparedStatement.setInt(1, cbxProveedor.getValue().getNITProveedor());   ///poner vaolr de los combobox
				preparedStatement.setInt(2, cbxInsumo.getValue().getCodigoInsumo());
				
		
			 resultset =preparedStatement.executeQuery();
				
				if ( resultset.next()) {
					id = resultset.getInt("idRelacion");   ///decir el resultado  de la prepare d statement
					
				}
				
		   
			} catch (SQLException e) {
				MessageBox messageBox = new MessageBox();
				messageBox.message("Error en Consulta Registrado", e.getMessage());
				
			}
		
		return id;
			
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



class InsumoCBX extends model.Insumo {

	public InsumoCBX(int codigoInsumo, String nombre, String descripción , int cantidad) {
		super(codigoInsumo, nombre, descripción,cantidad);
		
	}
		// TODO Auto-generated constructor stub
		@Override
		public String toString() {
			return  getNombre();
	}

	
	}

class ProveedorCBX extends model.Proveedor {

	public ProveedorCBX(int nitP, String nombre, String telefono , String direccion) {
		super(nitP, nombre, telefono ,direccion);
		
	}
		// TODO Auto-generated constructor stub
		@Override
		public String toString() {
			return  " NIT: " + getNITProveedor() + "Nombre: "+ getNombreP();
	}

	
	}



private void cleanScreen() {
	now();

	cbxInsumo.setValue(null);
	cbxProveedor.setValue(null);
	txtCantidad.setText("");

}
public void now() {

	dtpFecha.setValue(LocalDate.now());
	
	
	
}

private java.util.Date convertToDate(LocalDate localDate) {
	return Date.from(localDate.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
}
	

	
	
}
