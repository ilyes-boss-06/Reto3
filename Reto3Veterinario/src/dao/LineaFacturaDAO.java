package dao;

import modelo.LineaFactura;
import util.ConexionBD;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LineaFacturaDAO implements GenericDAO<LineaFactura> {

	/**
	 * Inserta una nueva línea de factura en la base de datos.
	 * @param objeto la línea de factura a insertar
	 * @return true si se insertó correctamente
	 */
	@Override
	public boolean insertar(LineaFactura objeto) {
		String sql = "INSERT INTO lineas_factura(id_factura, id_tratamiento, fecha, cantidad, precio_tratamiento, importe) "
				+ "VALUES(?,?,?,?,?,?)";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, objeto.getIdFactura());
			ps.setInt(2, objeto.getIdTratamiento());
			ps.setObject(3, objeto.getFecha());
			ps.setInt(4, objeto.getCantidad());
			ps.setBigDecimal(5, objeto.getPrecioTratamiento());
			ps.setBigDecimal(6, objeto.getImporte());
			int filas = ps.executeUpdate();
			if (filas > 0) {
				try (ResultSet rs = ps.getGeneratedKeys()) {
					if (rs.next()) {
						objeto.setIdLineaFactura(rs.getInt(1));
						return true;
					}
				}
			}
			return false;
		} catch (SQLException e) {
			System.out.println("Error insertando linea factura: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Obtiene todas las líneas de factura de la base de datos.
	 * @return lista con todas las líneas
	 */
	@Override
	public List<LineaFactura> obtenerTodos() {
		List<LineaFactura> lista = new ArrayList<>();
		String sql = "SELECT * FROM lineas_factura";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				lista.add(mapear(rs));
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo lineas factura: " + e.getMessage());
		}
		return lista;
	}

	/**
	 * Obtiene una línea de factura por su id.
	 * @param id el identificador de la línea
	 * @return la línea encontrada o null si no existe
	 */
	@Override
	public LineaFactura obtenerPorId(int id) {
		String sql = "SELECT * FROM lineas_factura WHERE id_linea_factura=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapear(rs);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error buscando linea factura por id: " + e.getMessage());
		}
		return null;
	}

	/**
	 * Actualiza los datos de una línea de factura existente.
	 * @param objeto la línea con los datos actualizados
	 * @return true si se actualizó correctamente
	 */
	@Override
	public boolean actualizar(LineaFactura objeto) {
		String sql = "UPDATE lineas_factura SET id_factura=?, id_tratamiento=?, fecha=?, cantidad=?, "
				+ "precio_tratamiento=?, importe=? WHERE id_linea_factura=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, objeto.getIdFactura());
			ps.setInt(2, objeto.getIdTratamiento());
			ps.setObject(3, objeto.getFecha());
			ps.setInt(4, objeto.getCantidad());
			ps.setBigDecimal(5, objeto.getPrecioTratamiento());
			ps.setBigDecimal(6, objeto.getImporte());
			ps.setInt(7, objeto.getIdLineaFactura());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error actualizando linea factura: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Elimina una línea de factura por su id.
	 * @param id el identificador de la línea a eliminar
	 * @return true si se eliminó correctamente
	 */
	@Override
	public boolean eliminar(int id) {
		String sql = "DELETE FROM lineas_factura WHERE id_linea_factura=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error eliminando linea factura: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Obtiene todas las líneas de una factura dada su id.
	 * @param idFactura el identificador de la factura
	 * @return lista de líneas de esa factura
	 */
	public List<LineaFactura> obtenerPorFactura(int idFactura) {
		List<LineaFactura> lista = new ArrayList<>();
		String sql = "SELECT * FROM lineas_factura WHERE id_factura=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idFactura);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					lista.add(mapear(rs));
				}
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo lineas por factura: " + e.getMessage());
		}
		return lista;
	}

	/**
	 * Elimina todas las líneas de una factura dada.
	 * @param idFactura el identificador de la factura
	 * @return true si se eliminaron correctamente
	 */
	public boolean eliminarPorFactura(int idFactura) {
		String sql = "DELETE FROM lineas_factura WHERE id_factura=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idFactura);
			return ps.executeUpdate() >= 0;
		} catch (SQLException e) {
			System.out.println("Error eliminando lineas por factura: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Convierte una fila del ResultSet en un objeto LineaFactura.
	 * @param rs el ResultSet posicionado en la fila actual
	 * @return el objeto LineaFactura mapeado
	 * @throws SQLException si ocurre un error de acceso a datos
	 */
	private LineaFactura mapear(ResultSet rs) throws SQLException {
		LineaFactura lf = new LineaFactura();
		lf.setIdLineaFactura(rs.getInt("id_linea_factura"));
		lf.setIdFactura(rs.getInt("id_factura"));
		lf.setIdTratamiento(rs.getInt("id_tratamiento"));
		lf.setFecha(rs.getObject("fecha", LocalDate.class));
		lf.setCantidad(rs.getInt("cantidad"));
		lf.setPrecioTratamiento(rs.getBigDecimal("precio_tratamiento"));
		lf.setImporte(rs.getBigDecimal("importe"));
		return lf;
	}
}
