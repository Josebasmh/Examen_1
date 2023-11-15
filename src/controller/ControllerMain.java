package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dao.Dao;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Producto;


public class ControllerMain implements Initializable{

    @FXML
    private TableColumn<Producto, String> tcCodigo;

    @FXML
    private TableColumn<Producto, Boolean> tcDisponible;

    @FXML
    private TableColumn<Producto, String> tcNombre;

    @FXML
    private TableColumn<Producto, Float> tcPrecio;

    @FXML
    private TableView<Producto> tvTabla;
    
    Dao dao = new Dao();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tcCodigo.setCellValueFactory(new PropertyValueFactory<Producto, String>("codigo"));
		tcNombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
		tcPrecio.setCellValueFactory(new PropertyValueFactory<Producto, Float>("precio"));
		tcDisponible.setCellValueFactory(new PropertyValueFactory<Producto, Boolean>("disponible"));
		ObservableList<Producto>listaProducto = dao.cargarDatos();
		tvTabla.setItems(listaProducto);
		
	}

}