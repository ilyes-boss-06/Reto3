package app;

import dao.*;
import modelo.*;

import java.util.List;
import java.util.Scanner;

public class Main2 {

	static ClienteDAO clienteDAO = new ClienteDAO();
	static VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
	static MascotaDAO mascotaDAO = new MascotaDAO();
	static TratamientoDAO tratamientoDAO = new TratamientoDAO();
	static HistorialDAO historialDAO = new HistorialDAO();
	static FacturaDAO facturaDAO = new FacturaDAO();
	static LineaFacturaDAO lineaFacturaDAO = new LineaFacturaDAO();

	// 3. Muestra todas las mascotas, selecciona una y muestra su historial
	
	public static void ejercicio3(Scanner sc) {
		System.out.println("\n3. MASCOTAS Y SU HISTORIAL");
		for (Mascota m : mascotaDAO.obtenerTodos()) {
			System.out.println(m);
		}
		System.out.print("Selecciona un id de mascota: ");
		int idMascota = sc.nextInt();
		sc.nextLine();
		List<Historial> listaHistorial = historialDAO.obtenerPorMascota(idMascota);
		System.out.println("Historial de la mascota " + idMascota + ":");
		for (Historial h : listaHistorial) {
			Tratamiento tratamiento = tratamientoDAO.obtenerPorId(h.getIdTratamiento());
			Veterinario veterinario = veterinarioDAO.obtenerPorId(h.getIdVeterinario());
			System.out.println("  Fecha: " + h.getFecha() + " | Tratamiento: " + tratamiento.getNombre()
					+ " | Veterinario: " + veterinario.getNombre());
		}
	}

	// 4. Muestra una factura por id junto con todas sus lineas
	
	public static void ejercicio4(Scanner sc) {
		System.out.println("\n4. FACTURA POR ID CON LINEAS");
		System.out.print("Introduce id de factura: ");
		int idFactura = sc.nextInt();
		sc.nextLine();
		Factura factura = facturaDAO.obtenerPorId(idFactura);
		if (factura != null) {
			System.out.println(factura);
			List<LineaFactura> lineas = lineaFacturaDAO.obtenerPorFactura(idFactura);
			for (LineaFactura lf : lineas) {
				Tratamiento tratamiento = tratamientoDAO.obtenerPorId(lf.getIdTratamiento());
				System.out.println("  Linea: " + tratamiento.getNombre() + " | Cant: " + lf.getCantidad()
						+ " | Precio: " + lf.getPrecioTratamiento() + " | Importe: " + lf.getImporte());
			}
		} else {
			System.out.println("Factura no encontrada.");
		}
	}
}
