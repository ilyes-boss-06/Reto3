package app;

import dao.*;
import modelo.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main3 {

	static ClienteDAO clienteDAO = new ClienteDAO();
	static VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
	static MascotaDAO mascotaDAO = new MascotaDAO();
	static TratamientoDAO tratamientoDAO = new TratamientoDAO();
	static HistorialDAO historialDAO = new HistorialDAO();
	static FacturaDAO facturaDAO = new FacturaDAO();
	static LineaFacturaDAO lineaFacturaDAO = new LineaFacturaDAO();

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

	

	}
