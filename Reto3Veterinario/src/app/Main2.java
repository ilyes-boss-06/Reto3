package app;
 
import dao.*;
import modelo.*;
 
import java.time.LocalDate;
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
 
    // 9. Selecciona un tratamiento y muestra su precio y veces en historial
    public static void ejercicio9(Scanner sc) {
        System.out.println("\n9. DETALLE DE TRATAMIENTO");
        for (Tratamiento t : tratamientoDAO.obtenerTodos()) {
            System.out.println(t);
        }
        System.out.print("Selecciona id de tratamiento: ");
        int idTratamiento = sc.nextInt();
        sc.nextLine();
        Tratamiento tratamiento = tratamientoDAO.obtenerPorId(idTratamiento);
        if (tratamiento != null) {
            int veces = tratamientoDAO.contarEnHistorial(idTratamiento);
            System.out.println("Tratamiento: " + tratamiento.getNombre() + " | Precio: " + tratamiento.getPrecio()
                    + " | Apariciones en historial: " + veces);
        }
    }
 
    // 10. Facturas por mes y total facturado
    public static void ejercicio10(Scanner sc) {
        System.out.println("\n10. FACTURAS POR MES");
        for (Factura f : facturaDAO.obtenerTodos()) {
            System.out.println(f);
        }
        System.out.print("Introduce numero de mes (1-12): ");
        int mes = sc.nextInt();
        sc.nextLine();
        List<Factura> facturasMes = facturaDAO.obtenerPorMes(mes);
        double totalMes = 0;
        for (Factura f : facturasMes) {
            System.out.println("  " + f);
            totalMes = totalMes + f.getTotal();
        }
        System.out.println("Total facturado en mes " + mes + ": " + totalMes);
    }
 
    // 15. Resumen mensual por veterinario
    public static void ejercicio15(Scanner sc) {
        System.out.println("\n15. RESUMEN MENSUAL POR VETERINARIO");
        System.out.print("Introduce numero de mes (1-12): ");
        int mes = sc.nextInt();
        sc.nextLine();
        for (Veterinario v : veterinarioDAO.obtenerTodos()) {
            int numTratamientos = historialDAO.contarPorVeterinarioYMes(v.getIdVeterinario(), mes);
            int numFacturas = facturaDAO.contarPorVeterinarioYMes(v.getIdVeterinario(), mes);
            double importeTotal = facturaDAO.totalPorVeterinarioYMes(v.getIdVeterinario(), mes);
            System.out.println("Veterinario: " + v.getNombre() + " | Tratamientos: " + numTratamientos
                    + " | Facturas: " + numFacturas + " | Importe total: " + importeTotal);
        }
    }
 
    // 16. Duplicar una factura con fecha actual
    public static void ejercicio16(Scanner sc) {
        System.out.println("\n16. DUPLICAR FACTURA");
        System.out.print("Introduce id de factura a duplicar: ");
        int idFactura = sc.nextInt();
        sc.nextLine();
        Factura original = facturaDAO.obtenerPorId(idFactura);
        if (original != null) {
            String hoy = LocalDate.now().toString();
            Factura nueva = new Factura(original.getIdCliente(), original.getIdVeterinario(),
                    original.getIdMascota(), hoy, original.getSubtotal(),
                    original.getTotalIva(), original.getTotal());
            if (facturaDAO.insertar(nueva)) {
                List<LineaFactura> lineasOriginal = lineaFacturaDAO.obtenerPorFactura(idFactura);
                for (LineaFactura lfOriginal : lineasOriginal) {
                    LineaFactura lfNueva = new LineaFactura(nueva.getIdFactura(), lfOriginal.getIdTratamiento(),
                            hoy, lfOriginal.getCantidad(), lfOriginal.getPrecioTratamiento(), lfOriginal.getImporte());
                    lineaFacturaDAO.insertar(lfNueva);
                }
                System.out.println("Factura duplicada: " + nueva);
                System.out.println("Lineas de la nueva factura:");
                for (LineaFactura lf : lineaFacturaDAO.obtenerPorFactura(nueva.getIdFactura())) {
                    Tratamiento tratamiento = tratamientoDAO.obtenerPorId(lf.getIdTratamiento());
                    System.out.println("  " + tratamiento.getNombre() + " | Importe: " + lf.getImporte());
                }
            }
        } else {
            System.out.println("Factura no encontrada.");
        }
    }
}