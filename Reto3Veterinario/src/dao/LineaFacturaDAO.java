package dao;

import modelo.LineaFactura;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LineaFacturaDAO implements GenericDAO<LineaFactura> {

	/**
	 * Inserta una nueva linea de factura.
	 * 
	 * @param objeto la linea a insertar
	 * @return true si se inserto correctamente
	 */
	@Override
	public boolean insertar(LineaFactura objeto) {
		String sql = "INSERT INTO lineas_factura(id_factura, id_tratamiento, fecha, cantidad, precio_tratamiento, importe) "
				+ "VALUES(?,?,?,?,?,?)";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, objeto.getIdFactura());
			ps.setInt(2, objeto.getIdTratamiento());
			ps.setString(3, objeto.getFecha());
			ps.setInt(4, objeto.getCantidad());
			ps.setDouble(5, objeto.getPrecioTratamiento());
			ps.setDouble(6, objeto.getImporte());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error insertando linea factura: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Obtiene todas las lineas de factura.
	 * 
	 * @return lista de lineas
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
	 * Obtiene una linea de factura por su id.
	 * 
	 * @param id identificador de la linea
	 * @return la linea o null
	 */
	@Override
	public LineaFactura obtenerPorId(int id) {
		String sql = "SELECT * FROM lineas_factura WHERE id_linea_factura=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
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
	 * Actualiza una linea de factura.
	 * 
	 * @param objeto la linea con datos actualizados
	 * @return true si se actualizo
	 */
	@Override
	public boolean actualizar(LineaFactura objeto) {
		String sql = "UPDATE lineas_factura SET id_factura=?, id_tratamiento=?, fecha=?, cantidad=?, "
				+ "precio_tratamiento=?, importe=? WHERE id_linea_factura=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, objeto.getIdFactura());
			ps.setInt(2, objeto.getIdTratamiento());
			ps.setString(3, objeto.getFecha());
			ps.setInt(4, objeto.getCantidad());
			ps.setDouble(5, objeto.getPrecioTratamiento());
			ps.setDouble(6, objeto.getImporte());
			ps.setInt(7, objeto.getIdLineaFactura());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error actualizando linea factura: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Elimina una linea de factura por su id.
	 * 
	 * @param id identificador de la linea
	 * @return true si se elimino
	 */
	@Override
	public boolean eliminar(int id) {
		String sql = "DELETE FROM lineas_factura WHERE id_linea_factura=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error eliminando linea factura: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Obtiene todas las lineas de una factura.
	 * 
	 * @param idFactura identificador de la factura
	 * @return lista de lineas
	 */
	public List<LineaFactura> obtenerPorFactura(int idFactura) {
		List<LineaFactura> lista = new ArrayList<>();
		String sql = "SELECT * FROM lineas_factura WHERE id_factura=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
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
	 * Mapea un ResultSet a un objeto LineaFactura.
	 * 
	 * @param rs el ResultSet
	 * @return la LineaFactura mapeada
	 */
	private LineaFactura mapear(ResultSet rs) throws SQLException {
		return new LineaFactura (rs.getInt("id_linea_factura"), 
				rs.getInt("id_factura"),
				rs.getInt("id_tratamiento"),
				rs.getString("fecha"),
				rs.getInt("cantidad"), 
				rs.getDouble("precio_tratamiento"),
				rs.getDouble("importe"));
	}
}
