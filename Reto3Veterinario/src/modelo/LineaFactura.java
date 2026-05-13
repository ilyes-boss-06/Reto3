package modelo;

import java.time.LocalDate;

public class LineaFactura {
private int IdLineaFactura;
private int IdFactura;
private int IdTratamiento;
private LocalDate Fecha;
private int Cantidad;
private double PrecioTratamiento;
private double Importe;
public LineaFactura(int idLineaFactura, int idFactura, int idTratamiento, LocalDate fecha, int cantidad,
		double precioTratamiento, double importe) {
	super();
	IdLineaFactura = idLineaFactura;
	IdFactura = idFactura;
	IdTratamiento = idTratamiento;
	Fecha = fecha;
	Cantidad = cantidad;
	PrecioTratamiento = precioTratamiento;
	Importe = importe;
}
public LineaFactura(int idFactura, int idTratamiento, LocalDate fecha, int cantidad, double precioTratamiento,
		double importe) {
	super();
	IdFactura = idFactura;
	IdTratamiento = idTratamiento;
	Fecha = fecha;
	Cantidad = cantidad;
	PrecioTratamiento = precioTratamiento;
	Importe = importe;
}
public LineaFactura() {
	super();
}
public int getIdLineaFactura() {
	return IdLineaFactura;
}
public void setIdLineaFactura(int idLineaFactura) {
	IdLineaFactura = idLineaFactura;
}
public int getIdFactura() {
	return IdFactura;
}
public void setIdFactura(int idFactura) {
	IdFactura = idFactura;
}
public int getIdTratamiento() {
	return IdTratamiento;
}
public void setIdTratamiento(int idTratamiento) {
	IdTratamiento = idTratamiento;
}
public LocalDate getFecha() {
	return Fecha;
}
public void setFecha(LocalDate fecha) {
	Fecha = fecha;
}
public int getCantidad() {
	return Cantidad;
}
public void setCantidad(int cantidad) {
	Cantidad = cantidad;
}
public double getPrecioTratamiento() {
	return PrecioTratamiento;
}
public void setPrecioTratamiento(double precioTratamiento) {
	PrecioTratamiento = precioTratamiento;
}
public double getImporte() {
	return Importe;
}
public void setImporte(double importe) {
	Importe = importe;
}
@Override
public String toString() {
	return "LineaFactura [IdLineaFactura=" + IdLineaFactura + ", IdFactura=" + IdFactura + ", IdTratamiento="
			+ IdTratamiento + ", Fecha=" + Fecha + ", Cantidad=" + Cantidad + ", PrecioTratamiento=" + PrecioTratamiento
			+ ", Importe=" + Importe + "]";
}




}
