package modelo;

import java.time.LocalDate;

public class Factura {
private int IdFactura;
private int IdCliente;
private int IdVeterinario;
private int IdMascota;
private LocalDate fecha;
private double subtotal;
private double total_iva;
private double total;
public Factura(int idFactura, int idCliente, int idVeterinario, int idMascota, LocalDate fecha, double subtotal,
		double total_iva, double total) {
	super();
	IdFactura = idFactura;
	IdCliente = idCliente;
	IdVeterinario = idVeterinario;
	IdMascota = idMascota;
	this.fecha = fecha;
	this.subtotal = subtotal;
	this.total_iva = total_iva;
	this.total = total;
}
public Factura(int idCliente, int idVeterinario, int idMascota, LocalDate fecha, double subtotal, double total_iva,
		double total) {
	super();
	IdCliente = idCliente;
	IdVeterinario = idVeterinario;
	IdMascota = idMascota;
	this.fecha = fecha;
	this.subtotal = subtotal;
	this.total_iva = total_iva;
	this.total = total;
}
public Factura() {
	super();
}
public int getIdFactura() {
	return IdFactura;
}
public void setIdFactura(int idFactura) {
	IdFactura = idFactura;
}
public int getIdCliente() {
	return IdCliente;
}
public void setIdCliente(int idCliente) {
	IdCliente = idCliente;
}
public int getIdVeterinario() {
	return IdVeterinario;
}
public void setIdVeterinario(int idVeterinario) {
	IdVeterinario = idVeterinario;
}
public int getIdMascota() {
	return IdMascota;
}
public void setIdMascota(int idMascota) {
	IdMascota = idMascota;
}
public LocalDate getFecha() {
	return fecha;
}
public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
}
public double getSubtotal() {
	return subtotal;
}
public void setSubtotal(double subtotal) {
	this.subtotal = subtotal;
}
public double getTotal_iva() {
	return total_iva;
}
public void setTotal_iva(double total_iva) {
	this.total_iva = total_iva;
}
public double getTotal() {
	return total;
}
public void setTotal(double total) {
	this.total = total;
}
@Override
public String toString() {
	return "Factura [IdFactura=" + IdFactura + ", IdCliente=" + IdCliente + ", IdVeterinario=" + IdVeterinario
			+ ", IdMascota=" + IdMascota + ", fecha=" + fecha + ", subtotal=" + subtotal + ", total_iva=" + total_iva
			+ ", total=" + total + "]";
}


	
	
}
