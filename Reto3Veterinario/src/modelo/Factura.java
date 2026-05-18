package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idFactura;
	private int idCliente;
	private int idVeterinario;
	private int idMascota;
	private LocalDate fecha;
	private BigDecimal subtotal;
	private BigDecimal totalIva;
	private BigDecimal total;

	/**
	 * Constructor vacío.
	 */
	public Factura() {
	}

	/**
	 * Constructor con todos los campos.
	 * @param idFactura identificador de la factura
	 * @param idCliente identificador del cliente
	 * @param idVeterinario identificador del veterinario
	 * @param idMascota identificador de la mascota
	 * @param fecha fecha de la factura
	 * @param subtotal subtotal sin IVA
	 * @param totalIva importe del IVA
	 * @param total total con IVA
	 */
	public Factura(int idFactura, int idCliente, int idVeterinario, int idMascota,
			LocalDate fecha, BigDecimal subtotal, BigDecimal totalIva, BigDecimal total) {
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
	 * Constructor sin id (para INSERT).
	 * @param idCliente identificador del cliente
	 * @param idVeterinario identificador del veterinario
	 * @param idMascota identificador de la mascota
	 * @param fecha fecha de la factura
	 * @param subtotal subtotal sin IVA
	 * @param totalIva importe del IVA
	 * @param total total con IVA
	 */
	public Factura(int idCliente, int idVeterinario, int idMascota,
			LocalDate fecha, BigDecimal subtotal, BigDecimal totalIva, BigDecimal total) {
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

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getTotalIva() {
		return totalIva;
	}

	public void setTotalIva(BigDecimal totalIva) {
		this.totalIva = totalIva;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Factura{idFactura=" + idFactura + ", idCliente=" + idCliente + ", idVeterinario="
				+ idVeterinario + ", idMascota=" + idMascota + ", fecha=" + fecha + ", subtotal="
				+ subtotal + ", totalIva=" + totalIva + ", total=" + total + "}";
	}
}
