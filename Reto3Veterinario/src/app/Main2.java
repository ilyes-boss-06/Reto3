package app;

import dao.*;
import modelo.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main2 {

	static ClienteDAO clienteDAO = new ClienteDAO();
	static VeterinarioDAO vetDAO = new VeterinarioDAO();
	static MascotaDAO mascotaDAO = new MascotaDAO();
	static TratamientoDAO tratDAO = new TratamientoDAO();
	static HistorialDAO histDAO = new HistorialDAO();
	static FacturaDAO facturaDAO = new FacturaDAO();
	static LineaFacturaDAO lineaDAO = new LineaFacturaDAO();

	/**
	 * Ejercicio 3: Muestra todas las mascotas, selecciona una y muestra su historial.
	 * @param sc Scanner para leer entrada del usuario
	 */
	
	public static void ejercicio3(Scanner sc) {
		System.out.println("3. MASCOTAS Y SU HISTORIAL");
		List<Mascota> todasMascotas = mascotaDAO.obtenerTodos();
		for (Mascota m : todasMascotas) {
			System.out.println(m);
		}
		System.out.print("Selecciona un id de mascota: ");
		int idMascSel = sc.nextInt();
		sc.nextLine();
		List<Historial> histMasc = histDAO.obtenerPorMascota(idMascSel);
		System.out.println("Historial de la mascota " + idMascSel + ":");
		for (Historial h : histMasc) {
			Tratamiento t = tratDAO.obtenerPorId(h.getIdTratamiento());
			Veterinario v = vetDAO.obtenerPorId(h.getIdVeterinario());
			System.out.println("  Fecha: " + h.getFecha() + " | Tratamiento: " + t.getNombre() + " | Veterinario: " + v.getNombre());
		}
	}
	
	/**
	 * Ejercicio 4: Muestra una factura por id junto con todas sus líneas.
	 * @param sc Scanner para leer entrada del usuario
	 */
	public static void ejercicio4(Scanner sc) {
		System.out.println("4. FACTURA POR ID CON LINEAS");
		System.out.print("Introduce id de factura: ");
		int idFactSel = sc.nextInt();
		sc.nextLine();
		Factura fact = facturaDAO.obtenerPorId(idFactSel);
		if (fact != null) {
			System.out.println(fact);
			List<LineaFactura> lineas = lineaDAO.obtenerPorFactura(idFactSel);
			for (LineaFactura lf : lineas) {
				Tratamiento t = tratDAO.obtenerPorId(lf.getIdTratamiento());
				System.out.println("  Linea: " + t.getNombre() + " | Cant: " + lf.getCantidad()
						+ " | Precio: " + lf.getPrecioTratamiento() + " | Importe: " + lf.getImporte());
			}
		} else {
			System.out.println("Factura no encontrada.");
		}
	}
}
