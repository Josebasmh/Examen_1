package controller;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import dao.Dao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Producto;


public class ControllerMain implements Initializable{
	
	@FXML
    private Button btnActualizar;

    @FXML
    private Button btnCrear;
    
    @FXML
    private CheckBox cbDisponible;

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
    
    @FXML
    private TextField tfCodigo;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfPrecio;   
    
    Dao dao = new Dao();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tcCodigo.setCellValueFactory(new PropertyValueFactory<Producto, String>("codigo"));
		tcNombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
		tcPrecio.setCellValueFactory(new PropertyValueFactory<Producto, Float>("precio"));
		tcDisponible.setCellValueFactory(new PropertyValueFactory<Producto, Boolean>("disponible"));
		btnActualizar.setDisable(true);
		ObservableList<Producto>listaProducto = dao.cargarDatos();
		tvTabla.setItems(listaProducto);
		
	}
	
    @FXML
    void crearProducto(ActionEvent event) {
    	btnActualizar.setDisable(true);
    	try {
    		ObservableList<String>camposNulos = comprobarCampos();
    		String campoPrecio = comprobarPrecio();
        	if (!campoPrecio.isEmpty()) {
        		throw new NumberFormatException(campoPrecio);
        	}
    		if (!camposNulos.isEmpty()) {
        		Iterator<String> it = camposNulos.iterator();
        		String msg="";
        		while(it.hasNext()) {
        			msg= msg + it.next();
        		}
        		throw new NullPointerException(msg);
        	}
        	boolean bdisponible= Boolean.getBoolean(cbDisponible.getText()); 
        	dao.aniadirProducto(new Producto(tfCodigo.getText(), tfNombre.getText(), Float.valueOf(tfPrecio.getText()) , bdisponible));
        	tvTabla.setItems(dao.cargarDatos());
    	}catch (NullPointerException e) {    		
    		ventanaAlerta("E", e.getMessage());
    	}catch (NumberFormatException e) {
    		ventanaAlerta("E", e.getMessage());
    	}  	
    }
    
    @FXML
    void seleccionarProducto(MouseEvent event) {	
    	btnActualizar.setDisable(false);
    	Producto p =tvTabla.getSelectionModel().getSelectedItem();
    	tfCodigo.setText(p.getCodigo().toString());
    	tfCodigo.setDisable(true);
    	tfNombre.setText(p.getNombre().toString());
    	tfPrecio.setText(p.getPrecio().toString());
    	cbDisponible.setSelected(p.isDisponible());
    	
    }
    

    @FXML
    void actualizarProducto(ActionEvent event) {
    	try {
    		ObservableList<String>camposNulos = comprobarCampos();
    		String campoPrecio = comprobarPrecio();
        	if (!campoPrecio.isEmpty()) {
        		throw new NumberFormatException(campoPrecio);
        	}
    		if (!camposNulos.isEmpty()) {
        		Iterator<String> it = camposNulos.iterator();
        		String msg="";
        		while(it.hasNext()) {
        			msg= msg + it.next();
        		}
        		throw new NullPointerException(msg);
    		}
    		btnActualizar.setDisable(false);
    		dao.actualizarProducto(new Producto(tfCodigo.getText(), tfNombre.getText(), Float.valueOf(tfPrecio.getText()), Boolean.getBoolean(cbDisponible.getText())));
    	}catch (NullPointerException e) {    		
    		ventanaAlerta("E", e.getMessage());
    	}catch (NumberFormatException e) {
    		ventanaAlerta("E", e.getMessage());
    	}
    }
    
    // Metodos auxiliares

    ObservableList<String> comprobarCampos() {
    	ObservableList<String> camposNulos = FXCollections.observableArrayList();
    	if (tfCodigo.getText().isEmpty()) {camposNulos.add("El campo Codigo está vacío.\n");}
    	if (tfNombre.getText().isEmpty()) {camposNulos.add("El campo Nombre está vacío.\n");}
    	if (tfPrecio.getText().isEmpty()) {camposNulos.add("El campo Precio está vacío.\n");}
    	return camposNulos;
    }
    
    String comprobarPrecio() {
    	if (!tfPrecio.getText().matches("^-?[0-9]+([\\.,][0-9]+)?$")) {
    		return "El campo Precio debe ser un decimal.\n";
    	}else {
    		return "";
    	}
    }
    
    // metodo statico para alertas
    static void ventanaAlerta(String tipoAlerta, String mensaje) {
		Alert alert = null;
		switch (tipoAlerta) {
			case ("E"):
				alert = new Alert(Alert.AlertType.ERROR);
				break;
			case ("I"):
				alert = new Alert(Alert.AlertType.INFORMATION);
		}
        alert.setContentText(mensaje);
        alert.showAndWait();
	}
}