package app;

import dao.*;
import modelo.*;

import java.util.*;

public class Main1 {

	static ClienteDAO clienteDAO = new ClienteDAO();
	static VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
	static MascotaDAO mascotaDAO = new MascotaDAO();
	static TratamientoDAO tratamientoDAO = new TratamientoDAO();
	static HistorialDAO historialDAO = new HistorialDAO();
	static FacturaDAO facturaDAO = new FacturaDAO();
	static LineaFacturaDAO lineaFacturaDAO = new LineaFacturaDAO();

	// 1. Muestra los tratamientos que ha realizado el veterinario id=2
	public static void ejercicio1() {
		System.out.println("1. TRATAMIENTOS DEL VETERINARIO ID=2");
		Veterinario veterinario = veterinarioDAO.obtenerPorId(2);
		if (veterinario != null) {
			System.out.println("Veterinario: " + veterinario.getNombre());
			List<Historial> listaHistorial = historialDAO.obtenerPorVeterinario(2);
			for (Historial h : listaHistorial) {
				Tratamiento tratamiento = tratamientoDAO.obtenerPorId(h.getIdTratamiento());
				Mascota mascota = mascotaDAO.obtenerPorId(h.getIdMascota());
				System.out.println("  Fecha: " + h.getFecha() + " | Mascota: " + mascota.getNombre()
						+ " | Tratamiento: " + tratamiento.getNombre() + " | Precio: " + tratamiento.getPrecio());
			}
		}
	}

	// 2. Muestra todos los clientes, selecciona un id y muestra sus mascotas
	public static void ejercicio2(Scanner sc) {
		System.out.println("\n2. CLIENTES Y SUS MASCOTAS");
		List<Cliente> listaClientes = clienteDAO.obtenerTodos();
		for (Cliente c : listaClientes) {
			System.out.println(c);
		}
		System.out.print("Selecciona un id de cliente: ");
		int idCliente = sc.nextInt();
		sc.nextLine();
		List<Mascota> listaMascotas = mascotaDAO.obtenerPorCliente(idCliente);
		System.out.println("Mascotas del cliente " + idCliente + ":");
		for (Mascota m : listaMascotas) {
			System.out.println("  " + m);
		}
	}
}
