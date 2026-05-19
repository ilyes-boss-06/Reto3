package modelo;

import java.io.Serializable;

public class Historial implements Serializable {
	private static final long serialVersionUID = 1L;

	protected int idHistorial;
	protected int idMascota;
	protected int idTratamiento;
	protected int idVeterinario;
	protected String fecha;

	public Historial() {
	}

	/**
	 * Constructor con todos los campos.
	 * 
	 * @param idHistorial   identificador del historial
	 * @param idMascota     identificador de la mascota
	 * @param idTratamiento identificador del tratamiento
	 * @param idVeterinario identificador del veterinario
	 * @param fecha         fecha del tratamiento
	 */
	public Historial(int idHistorial, int idMascota, int idTratamiento, int idVeterinario, String fecha) {
		this.idHistorial = idHistorial;
		this.idMascota = idMascota;
		this.idTratamiento = idTratamiento;
		this.idVeterinario = idVeterinario;
		this.fecha = fecha;
	}

	/**
	 * Constructor sin id.
	 * 
	 * @param idMascota     identificador de la mascota
	 * @param idTratamiento identificador del tratamiento
	 * @param idVeterinario identificador del veterinario
	 * @param fecha         fecha del tratamiento
	 */
	public Historial(int idMascota, int idTratamiento, int idVeterinario, String fecha) {
		this.idMascota = idMascota;
		this.idTratamiento = idTratamiento;
		this.idVeterinario = idVeterinario;
		this.fecha = fecha;
	}

	public int getIdHistorial() {
		return idHistorial;
	}

	public void setIdHistorial(int idHistorial) {
		this.idHistorial = idHistorial;
	}

	public int getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(int idMascota) {
		this.idMascota = idMascota;
	}

	public int getIdTratamiento() {
		return idTratamiento;
	}

	public void setIdTratamiento(int idTratamiento) {
		this.idTratamiento = idTratamiento;
	}

	public int getIdVeterinario() {
		return idVeterinario;
	}

	public void setIdVeterinario(int idVeterinario) {
		this.idVeterinario = idVeterinario;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Historial{idHistorial=" + idHistorial + ", idMascota=" + idMascota + ", idTratamiento=" + idTratamiento
				+ ", idVeterinario=" + idVeterinario + ", fecha='" + fecha + "'}";
	}
}
