package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MostrarMesasFX {
	private Connection connection;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;

	}
	@FXML
	private Button btnCargar;
	@FXML
	private TableView<Mesa> tableMesas;
	@FXML
	private TableColumn<Mesa, Integer> colCodMesa;
	@FXML
	private TableColumn<Mesa, String> colEstado;
	@FXML
	private TableColumn<Mesa, Integer> colCapacidad;
	
	
	@FXML
	private void initialize() 
	{
		
		colCodMesa.setCellValueFactory(new PropertyValueFactory<Mesa, Integer>("codMesa"));
		colEstado.setCellValueFactory(new PropertyValueFactory<Mesa, String>("estado"));
		colCapacidad.setCellValueFactory(new PropertyValueFactory<Mesa, Integer>("capacidad"));

	}
	
	
	@FXML
	private void btnCargar_Action()
	{
		try {


			final ObservableList<Mesa> data = FXCollections.observableArrayList();
			PreparedStatement preparedStatement = connection.query("select * from mesa ");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			Mesa mesa = null;

			while (resultSet.next()) {
				//System.out.println(resultSet.getString("producto") + " , " + resultSet.getString("cantidad"));



				mesa = new Mesa(resultSet.getInt("id_Mesa"),resultSet.getString("Estado"),
						resultSet.getInt("Capacidad"));

				data.add(mesa);


			}

			tableMesas.getItems().addAll(data);
			

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	
	
	public class Mesa
	{
		public Integer codMesa;
		public String estado;
		public Integer capacidad;
		public Mesa(Integer codMesa, String estado, Integer capacidad) {
			super();
			this.codMesa = codMesa;
			this.estado = estado;
			this.capacidad = capacidad;
		}
		public Integer getCodMesa() {
			return codMesa;
		}
		public void setCodMesa(Integer codMesa) {
			this.codMesa = codMesa;
		}
		public String getEstado() {
			return estado;
		}
		public void setEstado(String estado) {
			this.estado = estado;
		}
		public Integer getCapacidad() {
			return capacidad;
		}
		public void setCapacidad(Integer capacidad) {
			this.capacidad = capacidad;
		}
		
		
		
	}

}
