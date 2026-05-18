package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Mascota implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idMascota;
	private int idCliente;
	private String nombre;
	private String especie;
	private LocalDate fechaNacimiento;
	private BigDecimal peso;


	/**
	 * Constructor con todos los campos.
	 * @param idMascota identificador de la mascota
	 * @param idCliente identificador del cliente dueño
	 * @param nombre nombre de la mascota
	 * @param especie especie de la mascota
	 * @param fechaNacimiento fecha de nacimiento
	 * @param peso peso en kg
	 */
	
	public Mascota(int idMascota, int idCliente, String nombre, String especie,
			LocalDate fechaNacimiento, BigDecimal peso) {
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
	
	public Mascota(int idCliente, String nombre, String especie,
			LocalDate fechaNacimiento, BigDecimal peso) {
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

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "Mascota{idMascota=" + idMascota + ", idCliente=" + idCliente + ", nombre='" + nombre
				+ "', especie='" + especie + "', fechaNacimiento=" + fechaNacimiento + ", peso=" + peso + "}";
	}
}
