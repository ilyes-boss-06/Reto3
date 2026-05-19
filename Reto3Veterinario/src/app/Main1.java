package app;

import dao.*;
import modelo.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

	// 7. Añadir nueva mascota al cliente id=2, pide datos por teclado
	public static void ejercicio7(Scanner sc) {
		System.out.println("\n7. AÑADIR MASCOTA AL CLIENTE ID=2");
		System.out.print("Nombre de la mascota: ");
		String nombre = sc.nextLine();
		System.out.print("Especie: ");
		String especie = sc.nextLine();
		System.out.print("Fecha nacimiento (yyyy-MM-dd): ");
		String fechaNacimiento = sc.nextLine();
		System.out.print("Peso (kg): ");
		double peso = Double.parseDouble(sc.nextLine());
		Mascota mascota = new Mascota(2, nombre, especie, fechaNacimiento, peso);
		if (mascotaDAO.insertar(mascota)) {
			System.out.println("Mascota insertada: " + mascota);
		}
	}

	// 8. Tratar mascota: historial + factura con lineas
	public static void ejercicio8(Scanner sc) {
		System.out.println("\n8. TRATAR MASCOTA");
		for (Mascota m : mascotaDAO.obtenerTodos()) {
			System.out.println(m);
		}
		System.out.print("Selecciona id de mascota: ");
		int idMascota = sc.nextInt();
		sc.nextLine();
		Mascota mascota = mascotaDAO.obtenerPorId(idMascota);

		System.out.println("Tratamientos disponibles:");
		for (Tratamiento t : tratamientoDAO.obtenerTodos()) {
			System.out.println("  " + t);
		}

		System.out.print("Selecciona id de veterinario: ");
		int idVeterinario = sc.nextInt();
		sc.nextLine();

		List<Integer> idsTratamientos = new ArrayList<>();
		String hoy = LocalDate.now().toString();
		System.out.println("Introduce id de tratamiento (0 para terminar):");
		int idTratamiento = -1;
		while (idTratamiento != 0) {
			System.out.print("  Id tratamiento: ");
			idTratamiento = sc.nextInt();
			sc.nextLine();
			if (idTratamiento != 0) {
				idsTratamientos.add(idTratamiento);
				Historial historial = new Historial(idMascota, idTratamiento, idVeterinario, hoy);
				historialDAO.insertar(historial);
				System.out.println("  Tratamiento añadido al historial.");
			}
		}

		if (!idsTratamientos.isEmpty()) {
			double subtotal = 0;
			List<LineaFactura> lineas = new ArrayList<>();
			for (int idT : idsTratamientos) {
				Tratamiento tratamiento = tratamientoDAO.obtenerPorId(idT);
				subtotal = subtotal + tratamiento.getPrecio();
				lineas.add(new LineaFactura(0, idT, hoy, 1, tratamiento.getPrecio(), tratamiento.getPrecio()));
			}
			double iva = subtotal * 0.21;
			double total = subtotal + iva;

			Factura factura = new Factura(mascota.getIdCliente(), idVeterinario, idMascota, hoy, subtotal, iva, total);
			if (facturaDAO.insertar(factura)) {
				for (LineaFactura lf : lineas) {
					lf.setIdFactura(factura.getIdFactura());
					lineaFacturaDAO.insertar(lf);
				}
				System.out.println("Factura creada: " + factura);
				System.out.println("Lineas:");
				for (LineaFactura lf : lineaFacturaDAO.obtenerPorFactura(factura.getIdFactura())) {
					Tratamiento tratamiento = tratamientoDAO.obtenerPorId(lf.getIdTratamiento());
					System.out.println("  " + tratamiento.getNombre() + " | " + lf.getImporte());
				}
			}
		}
	}

	// 13. Eliminar mascota si no tiene facturas
	public static void ejercicio13(Scanner sc) {
		System.out.println("\n13. ELIMINAR MASCOTA SIN FACTURAS");
		for (Mascota m : mascotaDAO.obtenerTodos()) {
			System.out.println(m);
		}
		System.out.print("Selecciona id de mascota a eliminar: ");
		int idMascota = sc.nextInt();
		sc.nextLine();
		if (mascotaDAO.tieneFacturas(idMascota)) {
			System.out.println("No se puede eliminar: la mascota tiene facturas asociadas.");
		} else {
			if (mascotaDAO.eliminar(idMascota)) {
				System.out.println("Mascota eliminada correctamente.");
			}
		}
	}

	// 14. Serializar veterinarios en veterinarios.dat y leerlos
	public static void ejercicio14() {
		System.out.println("\n14. SERIALIZAR VETERINARIOS");
		List<Veterinario> listaVeterinarios = veterinarioDAO.obtenerTodos();

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("veterinarios.dat"))) {
			oos.writeObject(listaVeterinarios);
			System.out.println("Veterinarios guardados en veterinarios.dat");
		} catch (IOException e) {
			System.out.println("Error serializando: " + e.getMessage());
		}

		List<Veterinario> listaLeida = new ArrayList<>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("veterinarios.dat"))) {
			listaLeida = (List<Veterinario>) ois.readObject();
			System.out.println("Veterinarios leidos del fichero:");
			for (Veterinario v : listaLeida) {
				System.out.println("  " + v);
			}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error deserializando: " + e.getMessage());
		}
	}
}