package modelo;

import java.io.Serializable;

public class Cliente extends Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idCliente;
	private String telefono;

	public Cliente() {
	}

	/**
	 * Constructor con todos los campos.
	 * @param idCliente identificador del cliente
	 * @param idPersona identificador de la persona
	 * @param dni DNI
	 * @param nombre nombre completo
	 * @param telefono telefono de contacto
	 */
	public Cliente(int idCliente, int idPersona, String dni, String nombre, String telefono) {
		super(idPersona, dni, nombre);
		this.idCliente = idCliente;
		this.telefono = telefono;
	}

	/**
	 * Constructor sin ids.
	 * @param dni DNI
	 * @param nombre nombre completo
	 * @param telefono telefono de contacto
	 */
	public Cliente(String dni, String nombre, String telefono) {
		super(dni, nombre);
		this.telefono = telefono;
	}

	

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	@Override
	public String toString() {
		return "Cliente{idCliente=" + idCliente + ", dni='" + getDni() + "', nombre='" + getNombre()
				+ "', telefono='" + telefono + "'}";
	}
}

