package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Producto;

public class Dao {

	private ConexionBD conexion;
	
	public ObservableList<Producto> cargarDatos(){
		
		ObservableList<Producto>listaProductos= FXCollections.observableArrayList();
		String consulta = "SELECT * FROM productos;";
		PreparedStatement pstmt;
		try {
			conexion = new ConexionBD();
			pstmt = conexion.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String codigo = rs.getString("codigo");
				String nombre = rs.getString("nombre");
				Float precio = rs.getFloat("precio");
				Integer disponible = rs.getInt("disponible");
				
				listaProductos.add(new Producto(codigo, nombre, precio, disponible));
			}			
			
		} catch (SQLException e) {			
			e.printStackTrace();			
		}      
		return listaProductos;				
	}
}
