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
				boolean disponible = rs.getBoolean("disponible");
				
				listaProductos.add(new Producto(codigo, nombre, precio, disponible));
			}			
			rs.close();
			pstmt.close();
			conexion.CloseConexion();
		} catch (SQLException e) {			
			e.printStackTrace();			
		} 		
		return listaProductos;				
	}

	public void aniadirProducto(Producto p) {
		try {
			conexion = new ConexionBD();
			String consulta = "INSERT INTO productos(codigo,nombre,precio,disponible) values('" +
			p.getCodigo()+"','"+p.getNombre()+"',"+p.getPrecio()+","+p.isDisponible()+");";
			PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
