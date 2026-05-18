package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LineaFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idLineaFactura;
	private int idFactura;
	private int idTratamiento;
	private LocalDate fecha;
	private int cantidad;
	private BigDecimal precioTratamiento;
	private BigDecimal importe;

	/**
	 * Constructor vacío.
	 */
	public LineaFactura() {
	}

	/**
	 * Constructor con todos los campos.
	 * @param idLineaFactura identificador de la línea
	 * @param idFactura identificador de la factura
	 * @param idTratamiento identificador del tratamiento
	 * @param fecha fecha de la línea
	 * @param cantidad cantidad de unidades
	 * @param precioTratamiento precio unitario del tratamiento
	 * @param importe importe total de la línea
	 */
	public LineaFactura(int idLineaFactura, int idFactura, int idTratamiento,
			LocalDate fecha, int cantidad, BigDecimal precioTratamiento, BigDecimal importe) {
		this.idLineaFactura = idLineaFactura;
		this.idFactura = idFactura;
		this.idTratamiento = idTratamiento;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.precioTratamiento = precioTratamiento;
		this.importe = importe;
	}

	/**
	 * Constructor sin id (para INSERT).
	 * @param idFactura identificador de la factura
	 * @param idTratamiento identificador del tratamiento
	 * @param fecha fecha de la línea
	 * @param cantidad cantidad de unidades
	 * @param precioTratamiento precio unitario del tratamiento
	 * @param importe importe total de la línea
	 */
	public LineaFactura(int idFactura, int idTratamiento, LocalDate fecha,
			int cantidad, BigDecimal precioTratamiento, BigDecimal importe) {
		this.idFactura = idFactura;
		this.idTratamiento = idTratamiento;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.precioTratamiento = precioTratamiento;
		this.importe = importe;
	}

	public int getIdLineaFactura() {
		return idLineaFactura;
	}

	public void setIdLineaFactura(int idLineaFactura) {
		this.idLineaFactura = idLineaFactura;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public int getIdTratamiento() {
		return idTratamiento;
	}

	public void setIdTratamiento(int idTratamiento) {
		this.idTratamiento = idTratamiento;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecioTratamiento() {
		return precioTratamiento;
	}

	public void setPrecioTratamiento(BigDecimal precioTratamiento) {
		this.precioTratamiento = precioTratamiento;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "LineaFactura{idLineaFactura=" + idLineaFactura + ", idFactura=" + idFactura
				+ ", idTratamiento=" + idTratamiento + ", fecha=" + fecha + ", cantidad=" + cantidad
				+ ", precioTratamiento=" + precioTratamiento + ", importe=" + importe + "}";
	}
}
