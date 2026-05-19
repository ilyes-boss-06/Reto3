package modelo;

import java.io.Serializable;

public class Tratamiento implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idTratamiento;
	private String nombre;
	private double precio;

	public Tratamiento() {
	}

	/**
	 * Constructor con todos los campos.
	 * 
	 * @param idTratamiento identificador del tratamiento
	 * @param nombre        nombre del tratamiento
	 * @param precio        precio del tratamiento
	 */
	public Tratamiento(int idTratamiento, String nombre, double precio) {
		this.idTratamiento = idTratamiento;
		this.nombre = nombre;
		this.precio = precio;
	}

	/**
	 * Constructor sin id.
	 * 
	 * @param nombre nombre del tratamiento
	 * @param precio precio del tratamiento
	 */
	public Tratamiento(String nombre, double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}

	public int getIdTratamiento() {
		return idTratamiento;
	}

	public void setIdTratamiento(int idTratamiento) {
		this.idTratamiento = idTratamiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Tratamiento{idTratamiento=" + idTratamiento + ", nombre='" + nombre + "', precio=" + precio + "}";
	}
}
