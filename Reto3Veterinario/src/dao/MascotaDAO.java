package dao;

import modelo.Mascota;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO implements GenericDAO<Mascota> {

	/**
	 * Inserta una nueva mascota.
	 * @param objeto la mascota a insertar
	 * @return true si se inserto correctamente
	 */

	@Override
	public boolean insertar(Mascota objeto) {
		String sql = "INSERT INTO mascotas(id_cliente, nombre, especie, fecha_nacimiento, peso) VALUES(?,?,?,?,?)";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, objeto.getIdCliente());
			ps.setString(2, objeto.getNombre());
			ps.setString(3, objeto.getEspecie());
			ps.setString(4, objeto.getFechaNacimiento());
			ps.setDouble(5, objeto.getPeso());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error insertando mascota: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Obtiene todas las mascotas.
	 * @return lista de mascotas
	 */

	@Override
	public List<Mascota> obtenerTodos() {
		List<Mascota> lista = new ArrayList<>();
		String sql = "SELECT * FROM mascotas";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				lista.add(mapear(rs));
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo mascotas: " + e.getMessage());
		}
		return lista;
	}

	/**
	 * Obtiene una mascota por su id.
	 * @param id identificador de la mascota
	 * @return la mascota o null
	 */

	@Override
	public Mascota obtenerPorId(int id) {
		String sql = "SELECT * FROM mascotas WHERE id_mascota=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapear(rs);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error buscando mascota por id: " + e.getMessage());
		}
		return null;
	}

	/**
	 * Actualiza una mascota.
	 * @param objeto la mascota con datos actualizados
	 * @return true si se actualizo
	 */

	@Override
	public boolean actualizar(Mascota objeto) {
		String sql = "UPDATE mascotas SET id_cliente=?, nombre=?, especie=?, fecha_nacimiento=?, peso=? WHERE id_mascota=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, objeto.getIdCliente());
			ps.setString(2, objeto.getNombre());
			ps.setString(3, objeto.getEspecie());
			ps.setString(4, objeto.getFechaNacimiento());
			ps.setDouble(5, objeto.getPeso());
			ps.setInt(6, objeto.getIdMascota());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error actualizando mascota: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Elimina una mascota por su id.
	 * @param id identificador de la mascota
	 * @return true si se elimino
	 */

	@Override
	public boolean eliminar(int id) {
		String sql = "DELETE FROM mascotas WHERE id_mascota=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error eliminando mascota: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Obtiene las mascotas de un cliente.
	 * @param idCliente identificador del cliente
	 * @return lista de mascotas del cliente
	 */

	public List<Mascota> obtenerPorCliente(int idCliente) {
		List<Mascota> lista = new ArrayList<>();
		String sql = "SELECT * FROM mascotas WHERE id_cliente=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idCliente);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					lista.add(mapear(rs));
				}
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo mascotas por cliente: " + e.getMessage());
		}
		return lista;
	}

	/**
	 * Comprueba si una mascota tiene facturas asociadas. 
	 * @param idMascota identificador de la mascota
	 * @return true si tiene facturas
	 */

	public boolean tieneFacturas(int idMascota) {
		String sql = "SELECT COUNT(*) FROM facturas WHERE id_mascota=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idMascota);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (SQLException e) {
			System.out.println("Error comprobando facturas de mascota: " + e.getMessage());
		}
		return true;
	}

	/**
	 * Mapea un ResultSet a un objeto Mascota.
	 * @param rs el ResultSet
	 * @return la Mascota mapeada
	 */

	private Mascota mapear(ResultSet rs) throws SQLException {
		return new Mascota(
				rs.getInt("id_mascota"),
				rs.getInt("id_cliente"),
				rs.getString("nombre"),
				rs.getString("especie"),
				rs.getString("fecha_nacimiento"),
				rs.getDouble("peso")
			);
	}
}
