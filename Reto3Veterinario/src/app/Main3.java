package app;

import dao.*;
import modelo.*;

import java.util.*;

public class Main3 {
	
	

	static ClienteDAO clienteDAO = new ClienteDAO();
	static VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
	static MascotaDAO mascotaDAO = new MascotaDAO();
	static TratamientoDAO tratamientoDAO = new TratamientoDAO();
	static HistorialDAO historialDAO = new HistorialDAO();
	static FacturaDAO facturaDAO = new FacturaDAO();
	static LineaFacturaDAO lineaFacturaDAO = new LineaFacturaDAO();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ejercicio17(sc);	}
	
	// 5. Selecciona un cliente y muestra sus datos, mascotas y facturas
	public static void ejercicio5(Scanner sc) {
		System.out.println("\n5. CLIENTE COMPLETO: DATOS + MASCOTAS + FACTURAS");
		for (Cliente c : clienteDAO.obtenerTodos()) {
			System.out.println(c);
		}
		System.out.print("Selecciona un id de cliente: ");
		int idCliente = sc.nextInt();
		sc.nextLine();
		Cliente cliente = clienteDAO.obtenerPorId(idCliente);
		if (cliente != null) {
			System.out.println("Datos: " + cliente);
			System.out.println("Mascotas:");
			for (Mascota m : mascotaDAO.obtenerPorCliente(idCliente)) {
				System.out.println("  " + m);
			}
			System.out.println("Facturas:");
			for (Factura f : facturaDAO.obtenerPorCliente(idCliente)) {
				System.out.println("  " + f);
			}
		}
	}

	// 6. Añade un veterinario: 12345678Z Pepe Carrera, COL-1005
	public static void ejercicio6() {
		System.out.println("\n6. AÑADIR VETERINARIO");
		Veterinario veterinario = new Veterinario("12345678Z", "Pepe Carrera", "COL-1005");
		if (veterinarioDAO.insertar(veterinario)) {
			System.out.println("Veterinario insertado: " + veterinario);
		} else {
			System.out.println("Error al insertar veterinario (puede que ya exista).");
		}
	}

	// 11. Muestra veterinarios y las facturas de uno seleccionado
	public static void ejercicio11(Scanner sc) {
		System.out.println("\n11. FACTURAS POR VETERINARIO");
		for (Veterinario v : veterinarioDAO.obtenerTodos()) {
			System.out.println(v);
		}
		System.out.print("Selecciona id de veterinario: ");
		int idVeterinario = sc.nextInt();
		sc.nextLine();
		List<Factura> listaFacturas = facturaDAO.obtenerPorVeterinario(idVeterinario);
		for (Factura f : listaFacturas) {
			System.out.println("  " + f);
		}
	}

	// 12. Muestra mascotas y actualiza el peso de una seleccionada
	public static void ejercicio12(Scanner sc) {
		System.out.println("\n12. ACTUALIZAR PESO DE MASCOTA");
		for (Mascota m : mascotaDAO.obtenerTodos()) {
			System.out.println(m);
		}
		System.out.print("Selecciona id de mascota: ");
		int idMascota = sc.nextInt();
		sc.nextLine();
		Mascota mascota = mascotaDAO.obtenerPorId(idMascota);
		if (mascota != null) {
			System.out.print("Nuevo peso (kg): ");
			double nuevoPeso = Double.parseDouble(sc.nextLine());
			mascota.setPeso(nuevoPeso);
			if (mascotaDAO.actualizar(mascota)) {
				System.out.println("Peso actualizado: " + mascota);
			}
		}
	}

	// 17. Elimina un tratamiento del historial y rehace la factura
	public static void ejercicio17(Scanner sc) {
		System.out.println("\n17. ELIMINAR TRATAMIENTO DEL HISTORIAL Y REHACER FACTURA");
		for (Mascota m : mascotaDAO.obtenerTodos()) {
			System.out.println(m);
		}
		System.out.print("Selecciona id de mascota: ");
		int idMascota = sc.nextInt();
		sc.nextLine();

		System.out.print("Introduce fecha (yyyy-MM-dd): ");
		String fecha = sc.nextLine();

		List<Historial> listaHistorial = historialDAO.obtenerPorMascotaYFecha(idMascota, fecha);
		if (listaHistorial.isEmpty()) {
			System.out.println("No hay registros en el historial para esa mascota y fecha.");
		} else {
			System.out.println("Historial de esa fecha:");
			for (Historial h : listaHistorial) {
				Tratamiento tratamiento = tratamientoDAO.obtenerPorId(h.getIdTratamiento());
				System.out.println("  idHistorial=" + h.getIdHistorial() + " | Tratamiento: " + tratamiento.getNombre()
						+ " | Precio: " + tratamiento.getPrecio());
			}
			System.out.print("Selecciona id_historial a eliminar: ");
			int idHistorial = sc.nextInt();
			sc.nextLine();

			Historial historialEliminar = historialDAO.obtenerPorId(idHistorial);
			if (historialEliminar != null) {
				historialDAO.eliminar(idHistorial);
				System.out.println("Registro eliminado del historial.");

				// Buscar factura asociada (misma mascota, misma fecha)
				Factura facturaAsociada = null;
				for (Factura f : facturaDAO.obtenerTodos()) {
					if (f.getIdMascota() == idMascota && f.getFecha().equals(fecha)) {
						facturaAsociada = f;
						break;
					}
				}

				if (facturaAsociada != null) {
					// Eliminar la linea del tratamiento eliminado
					List<LineaFactura> lineas = lineaFacturaDAO.obtenerPorFactura(facturaAsociada.getIdFactura());
					for (LineaFactura lf : lineas) {
						if (lf.getIdTratamiento() == historialEliminar.getIdTratamiento()) {
							lineaFacturaDAO.eliminar(lf.getIdLineaFactura());
							System.out.println("Linea de factura eliminada.");
							break;
						}
					}

					// Recalcular importes
					List<LineaFactura> lineasRestantes = lineaFacturaDAO.obtenerPorFactura(facturaAsociada.getIdFactura());
					double nuevoSubtotal = 0;
					for (LineaFactura lf : lineasRestantes) {
						nuevoSubtotal = nuevoSubtotal + lf.getImporte();
					}
					double nuevoIva = nuevoSubtotal * 0.21;
					double nuevoTotal = nuevoSubtotal + nuevoIva;

					facturaAsociada.setSubtotal(nuevoSubtotal);
					facturaAsociada.setTotalIva(nuevoIva);
					facturaAsociada.setTotal(nuevoTotal);
					facturaDAO.actualizar(facturaAsociada);

					System.out.println("Factura actualizada: " + facturaAsociada);
					System.out.println("Lineas restantes:");
					for (LineaFactura lf : lineasRestantes) {
						Tratamiento tratamiento = tratamientoDAO.obtenerPorId(lf.getIdTratamiento());
						System.out.println("  " + tratamiento.getNombre() + " | Importe: " + lf.getImporte());
					}
				} else {
					System.out.println("No se encontro factura asociada a esa mascota y fecha.");
				}
			}
		}
	}
}
