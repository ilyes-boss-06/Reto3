package dao;

import modelo.Factura;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO implements GenericDAO<Factura> {

	/**
	 * Inserta una nueva factura.
	 * 
	 * @param objeto la factura a insertar
	 * @return true si se inserto correctamente
	 */
	@Override
	public boolean insertar(Factura objeto) {
		String sql = "INSERT INTO facturas(id_cliente, id_veterinario, id_mascota, fecha, subtotal, total_iva, total) "
				+ "VALUES(?,?,?,?,?,?,?)";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, objeto.getIdCliente());
			ps.setInt(2, objeto.getIdVeterinario());
			ps.setInt(3, objeto.getIdMascota());
			ps.setString(4, objeto.getFecha());
			ps.setDouble(5, objeto.getSubtotal());
			ps.setDouble(6, objeto.getTotalIva());
			ps.setDouble(7, objeto.getTotal());
			int filas = ps.executeUpdate();
			if (filas > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					objeto.setIdFactura(rs.getInt(1));
				}
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.out.println("Error insertando factura: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Obtiene todas las facturas.
	 * 
	 * @return lista de facturas
	 */
	@Override
	public List<Factura> obtenerTodos() {
		List<Factura> lista = new ArrayList<>();
		String sql = "SELECT * FROM facturas";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				lista.add(mapear(rs));
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo facturas: " + e.getMessage());
		}
		return lista;
	}

	/**
	 * Obtiene una factura por su id.
	 * 
	 * @param id identificador de la factura
	 * @return la factura o null
	 */
	@Override
	public Factura obtenerPorId(int id) {
		String sql = "SELECT * FROM facturas WHERE id_factura=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapear(rs);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error buscando factura por id: " + e.getMessage());
		}
		return null;
	}

	/**
	 * Actualiza una factura.
	 * 
	 * @param objeto la factura con datos actualizados
	 * @return true si se actualizo
	 */
	@Override
	public boolean actualizar(Factura objeto) {
		String sql = "UPDATE facturas SET id_cliente=?, id_veterinario=?, id_mascota=?, fecha=?, "
				+ "subtotal=?, total_iva=?, total=? WHERE id_factura=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, objeto.getIdCliente());
			ps.setInt(2, objeto.getIdVeterinario());
			ps.setInt(3, objeto.getIdMascota());
			ps.setString(4, objeto.getFecha());
			ps.setDouble(5, objeto.getSubtotal());
			ps.setDouble(6, objeto.getTotalIva());
			ps.setDouble(7, objeto.getTotal());
			ps.setInt(8, objeto.getIdFactura());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error actualizando factura: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Elimina una factura por su id.
	 * 
	 * @param id identificador de la factura
	 * @return true si se elimino
	 */
	@Override
	public boolean eliminar(int id) {
		String sql = "DELETE FROM facturas WHERE id_factura=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error eliminando factura: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Obtiene las facturas de un cliente.
	 * 
	 * @param idCliente identificador del cliente
	 * @return lista de facturas
	 */
	public List<Factura> obtenerPorCliente(int idCliente) {
		List<Factura> lista = new ArrayList<>();
		String sql = "SELECT * FROM facturas WHERE id_cliente=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idCliente);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					lista.add(mapear(rs));
				}
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo facturas por cliente: " + e.getMessage());
		}
		return lista;
	}

	/**
	 * Obtiene las facturas de un veterinario.
	 * 
	 * @param idVeterinario identificador del veterinario
	 * @return lista de facturas
	 */
	public List<Factura> obtenerPorVeterinario(int idVeterinario) {
		List<Factura> lista = new ArrayList<>();
		String sql = "SELECT * FROM facturas WHERE id_veterinario=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idVeterinario);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					lista.add(mapear(rs));
				}
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo facturas por veterinario: " + e.getMessage());
		}
		return lista;
	}

	/**
	 * Obtiene las facturas de un mes dado.
	 * 
	 * @param mes numero de mes (1-12)
	 * @return lista de facturas
	 */
	public List<Factura> obtenerPorMes(int mes) {
		List<Factura> lista = new ArrayList<>();
		String sql = "SELECT * FROM facturas WHERE MONTH(fecha)=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, mes);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					lista.add(mapear(rs));
				}
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo facturas por mes: " + e.getMessage());
		}
		return lista;
	}

	/**
	 * Cuenta las facturas de un veterinario en un mes.
	 * 
	 * @param idVeterinario identificador del veterinario
	 * @param mes           numero de mes (1-12)
	 * @return numero de facturas
	 */
	public int contarPorVeterinarioYMes(int idVeterinario, int mes) {
		String sql = "SELECT COUNT(*) FROM facturas WHERE id_veterinario=? AND MONTH(fecha)=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idVeterinario);
			ps.setInt(2, mes);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error contando facturas por vet y mes: " + e.getMessage());
		}
		return 0;
	}

	/**
	 * Obtiene el total facturado por un veterinario en un mes.
	 * 
	 * @param idVeterinario identificador del veterinario
	 * @param mes           numero de mes (1-12)
	 * @return total facturado
	 */
	public double totalPorVeterinarioYMes(int idVeterinario, int mes) {
		String sql = "SELECT COALESCE(SUM(total), 0) FROM facturas WHERE id_veterinario=? AND MONTH(fecha)=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idVeterinario);
			ps.setInt(2, mes);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getDouble(1);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error sumando total por vet y mes: " + e.getMessage());
		}
		return 0;
	}

	/**
	 * Mapea un ResultSet a un objeto Factura.
	 * 
	 * @param rs el ResultSet
	 * @return la Factura mapeada
	 */
	private Factura mapear(ResultSet rs) throws SQLException {
		return new Factura(rs.getInt("id_factura"), rs.getInt("id_cliente"), rs.getInt("id_veterinario"),
				rs.getInt("id_mascota"), rs.getString("fecha"), rs.getDouble("subtotal"), rs.getDouble("total_iva"),
				rs.getDouble("total"));
	}
}
