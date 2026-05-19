package modelo;

import java.io.Serializable;

public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idFactura;
	private int idCliente;
	private int idVeterinario;
	private int idMascota;
	private String fecha;
	private double subtotal;
	private double totalIva;
	private double total;

	public Factura() {
	}

	/**
	 * Constructor con todos los campos.
	 * 
	 * @param idFactura     identificador de la factura
	 * @param idCliente     identificador del cliente
	 * @param idVeterinario identificador del veterinario
	 * @param idMascota     identificador de la mascota
	 * @param fecha         fecha de la factura
	 * @param subtotal      subtotal sin IVA
	 * @param totalIva      importe del IVA
	 * @param total         total con IVA
	 */
	public Factura(int idFactura, int idCliente, int idVeterinario, int idMascota, String fecha, double subtotal,
			double totalIva, double total) {
		this.idFactura = idFactura;
		this.idCliente = idCliente;
		this.idVeterinario = idVeterinario;
		this.idMascota = idMascota;
		this.fecha = fecha;
		this.subtotal = subtotal;
		this.totalIva = totalIva;
		this.total = total;
	}

	/**
	 * Constructor sin id.
	 * 
	 * @param idCliente     identificador del cliente
	 * @param idVeterinario identificador del veterinario
	 * @param idMascota     identificador de la mascota
	 * @param fecha         fecha de la factura
	 * @param subtotal      subtotal sin IVA
	 * @param totalIva      importe del IVA
	 * @param total         total con IVA
	 */
	public Factura(int idCliente, int idVeterinario, int idMascota, String fecha, double subtotal, double totalIva,
			double total) {
		this.idCliente = idCliente;
		this.idVeterinario = idVeterinario;
		this.idMascota = idMascota;
		this.fecha = fecha;
		this.subtotal = subtotal;
		this.totalIva = totalIva;
		this.total = total;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdVeterinario() {
		return idVeterinario;
	}

	public void setIdVeterinario(int idVeterinario) {
		this.idVeterinario = idVeterinario;
	}

	public int getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(int idMascota) {
		this.idMascota = idMascota;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTotalIva() {
		return totalIva;
	}

	public void setTotalIva(double totalIva) {
		this.totalIva = totalIva;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Factura{idFactura=" + idFactura + ", idCliente=" + idCliente + ", idVeterinario=" + idVeterinario
				+ ", idMascota=" + idMascota + ", fecha='" + fecha + "', subtotal=" + subtotal + ", totalIva="
				+ totalIva + ", total=" + total + "}";
	}
}
