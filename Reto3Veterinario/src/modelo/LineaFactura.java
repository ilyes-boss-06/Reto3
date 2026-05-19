package modelo;

import java.io.Serializable;

public class LineaFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idLineaFactura;
	private int idFactura;
	private int idTratamiento;
	private String fecha;
	private int cantidad;
	private double precioTratamiento;
	private double importe;

	public LineaFactura() {
	}

	/**
	 * Constructor con todos los campos.
	 * 
	 * @param idLineaFactura    identificador de la linea
	 * @param idFactura         identificador de la factura
	 * @param idTratamiento     identificador del tratamiento
	 * @param fecha             fecha de la linea
	 * @param cantidad          cantidad de unidades
	 * @param precioTratamiento precio unitario
	 * @param importe           importe total de la linea
	 */
	public LineaFactura(int idLineaFactura, int idFactura, int idTratamiento, String fecha, int cantidad,
			double precioTratamiento, double importe) {
		this.idLineaFactura = idLineaFactura;
		this.idFactura = idFactura;
		this.idTratamiento = idTratamiento;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.precioTratamiento = precioTratamiento;
		this.importe = importe;
	}

	/**
	 * Constructor sin id.
	 * 
	 * @param idFactura         identificador de la factura
	 * @param idTratamiento     identificador del tratamiento
	 * @param fecha             fecha de la linea
	 * @param cantidad          cantidad de unidades
	 * @param precioTratamiento precio unitario
	 * @param importe           importe total de la linea
	 */
	public LineaFactura(int idFactura, int idTratamiento, String fecha, int cantidad, double precioTratamiento,
			double importe) {
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecioTratamiento() {
		return precioTratamiento;
	}

	public void setPrecioTratamiento(double precioTratamiento) {
		this.precioTratamiento = precioTratamiento;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "LineaFactura{idLineaFactura=" + idLineaFactura + ", idFactura=" + idFactura + ", idTratamiento="
				+ idTratamiento + ", fecha='" + fecha + "', cantidad=" + cantidad + ", precioTratamiento="
				+ precioTratamiento + ", importe=" + importe + "}";
	}
}
