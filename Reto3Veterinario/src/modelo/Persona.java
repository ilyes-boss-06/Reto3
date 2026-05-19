package modelo;

import java.io.Serializable;

public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idPersona;
	private String dni;
	private String nombre;

	public Persona() {
	}

	/**
	 * Constructor con todos los campos.
	 * @param idPersona identificador
	 * @param dni DNI
	 * @param nombre nombre completo
	 */
	public Persona(int idPersona, String dni, String nombre) {
		this.idPersona = idPersona;
		this.dni = dni;
		this.nombre = nombre;
	}

	/**
	 * Constructor sin id.
	 * @param dni DNI
	 * @param nombre nombre completo
	 */
	public Persona(String dni, String nombre) {
		this.dni = dni;
		this.nombre = nombre;
	}

	

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public String toString() {
		return "Persona{idPersona=" + idPersona + ", dni='" + dni + "', nombre='" + nombre + "'}";
	}
}
