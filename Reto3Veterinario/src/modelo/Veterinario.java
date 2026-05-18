package modelo;

import java.io.Serializable;

public class Veterinario extends Persona {
	
	private static final long serialVersionUID = 1L;
	
	private int idVeterinario;
	private int idPersona;
	private String numColegiado;
	
	/**
	 * Constructor con todos los campos.
	 * @param idVeterinario identificador del veterinario
	 * @param idPersona identificador de la persona asociada
	 * @param dni DNI del veterinario
	 * @param nombre nombre completo del veterinario
	 * @param numColegiado número de colegiado
	 */
	public Veterinario(int idPersona, String dni, String nombre, int idVeterinario, int idPersona2,
			String numColegiado) {
		super(idPersona, dni, nombre);
		this.idVeterinario = idVeterinario;
		idPersona = idPersona2;
		this.numColegiado = numColegiado;
	}
	/**
	 * Constructor sin ids (para INSERT).
	 * @param dni DNI del veterinario
	 * @param nombre nombre completo del veterinario
	 * @param numColegiado número de colegiado
	 */
	public Veterinario(String dni, String nombre, int idPersona2, String numColegiado) {
		super(dni, nombre);
		idPersona = idPersona2;
		this.numColegiado = numColegiado;
	}
	/**
	 * Constructor vacío.
	 */
	public Veterinario() {
		super();
	}
	public int getIdVeterinario() {
		return idVeterinario;
	}
	public void setIdVeterinario(int idVeterinario) {
		this.idVeterinario = idVeterinario;
	}
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getNumColegiado() {
		return numColegiado;
	}
	public void setNumColegiado(String numColegiado) {
		this.numColegiado = numColegiado;
	}
	@Override
	public String toString() {
		return "Veterinario [idVeterinario=" + idVeterinario + ", idPersona=" + idPersona + ", numColegiado="
				+ numColegiado + ", toString()=" + super.toString() + "]";
	}
	
	
}
