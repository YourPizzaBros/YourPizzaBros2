package controller;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class PagarFX {
	private Connection connection;
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;

	}
	@FXML
	private Button btnPagar;
	@FXML
	private Button btnCargarPedidos;
	
	@FXML
	private TextArea txtAPedidos;
	
	@FXML
	private void btnCargarPedidos_Action() 
	{
		try 
		{
			 
			java.sql.Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(" select " + 
					" venta.codVenta as \"Codigo de Venta\", NIT, Fecha, Estado,\r\n" + 
					" producto.Nombre as \"Nombre Producto\",\r\n" + 
					" producto.codProducto as \"Codigo Producto\", Cantidad, producto.Precio as \"Precio Producto\"\r\n" + 
					"\r\n" + 
					"from producto \r\n" + 
					"inner join detalle_venta\r\n" + 
					"on producto.codProducto = detalle_venta.codProducto\r\n" + 
					"inner join venta\r\n" + 
					"on  detalle_venta.codVenta = venta.codVenta\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"");
			while (rs.next()) 
			{
				System.out.println(rs.getString("venta.codVenta")+rs.getString("NIT")+
						rs.getString("Fecha")+rs.getString("Estado")+" , "+rs.getString("Precio"));
			}
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
		}
	}
	
	
	

}
