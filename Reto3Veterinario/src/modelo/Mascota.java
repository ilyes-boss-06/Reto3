package modelo;

import java.time.LocalDate;

public class Mascota {
	protected int idMascota;
	protected int idCliente;
	protected String nombre;
	protected String especie;
	protected LocalDate fechaNacimiento;
	protected double peso;
	
	/**
	 * Constructor con todos los campos.
	 * @param idMascota identificador de la mascota
	 * @param idCliente identificador del cliente dueño
	 * @param nombre nombre de la mascota
	 * @param especie especie de la mascota
	 * @param fechaNacimiento fecha de nacimiento
	 * @param peso peso en kg
	 */
	
	public Mascota(int idMascota, int idCliente, String nombre, String especie, LocalDate fechaNacimiento,
			double peso) {
		super();
		this.idMascota = idMascota;
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.especie = especie;
		this.fechaNacimiento = fechaNacimiento;
		this.peso = peso;
	}

	/**
	 * Constructor sin id (para INSERT).
	 * @param idCliente identificador del cliente dueño
	 * @param nombre nombre de la mascota
	 * @param especie especie de la mascota
	 * @param fechaNacimiento fecha de nacimiento
	 * @param peso peso en kg
	 */
	
	public Mascota(int idCliente, String nombre, String especie, LocalDate fechaNacimiento, double peso) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.especie = especie;
		this.fechaNacimiento = fechaNacimiento;
		this.peso = peso;
	}
	
	/**
	 * Constructor vacío.
	 */
	
	public Mascota() {
		super();
	}

	public int getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(int idMascota) {
		this.idMascota = idMascota;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "Mascota [idMascota=" + idMascota + ", idCliente=" + idCliente + ", nombre=" + nombre + ", especie="
				+ especie + ", fechaNacimiento=" + fechaNacimiento + ", peso=" + peso + "]";
	}
	
}
