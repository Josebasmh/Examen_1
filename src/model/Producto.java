package model;

public class Producto {

	String codigo,nombre;
	Float precio;
	boolean disponible;
	
	public Producto(String c, String n, Float p, int d) {
		codigo=c;
		nombre=n;
		precio = p;
		if (d==1) {
			disponible=true;
		}else {
			disponible=false;
		}
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	
}
