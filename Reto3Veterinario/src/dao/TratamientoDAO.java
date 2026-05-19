package dao;

import modelo.Tratamiento;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TratamientoDAO implements GenericDAO<Tratamiento> {

	/**
	 * Inserta un nuevo tratamiento.
	 * 
	 * @param objeto el tratamiento a insertar
	 * @return true si se inserto correctamente
	 */
	@Override
	public boolean insertar(Tratamiento objeto) {
		String sql = "INSERT INTO tratamientos(nombre, precio) VALUES(?,?)";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, objeto.getNombre());
			ps.setDouble(2, objeto.getPrecio());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error insertando tratamiento: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Obtiene todos los tratamientos.
	 * 
	 * @return lista de tratamientos
	 */
	@Override
	public List<Tratamiento> obtenerTodos() {
		List<Tratamiento> lista = new ArrayList<>();
		String sql = "SELECT * FROM tratamientos";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				lista.add(mapear(rs));
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo tratamientos: " + e.getMessage());
		}
		return lista;
	}

	/**
	 * Obtiene un tratamiento por su id.
	 * 
	 * @param id identificador del tratamiento
	 * @return el tratamiento o null
	 */
	@Override
	public Tratamiento obtenerPorId(int id) {
		String sql = "SELECT * FROM tratamientos WHERE id_tratamiento=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapear(rs);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error buscando tratamiento por id: " + e.getMessage());
		}
		return null;
	}

	/**
	 * Actualiza un tratamiento.
	 * 
	 * @param objeto el tratamiento con datos actualizados
	 * @return true si se actualizo
	 */
	@Override
	public boolean actualizar(Tratamiento objeto) {
		String sql = "UPDATE tratamientos SET nombre=?, precio=? WHERE id_tratamiento=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, objeto.getNombre());
			ps.setDouble(2, objeto.getPrecio());
			ps.setInt(3, objeto.getIdTratamiento());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error actualizando tratamiento: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Elimina un tratamiento por su id.
	 * 
	 * @param id identificador del tratamiento
	 * @return true si se elimino
	 */
	@Override
	public boolean eliminar(int id) {
		String sql = "DELETE FROM tratamientos WHERE id_tratamiento=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error eliminando tratamiento: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Cuenta cuantas veces aparece un tratamiento en el historial.
	 * 
	 * @param idTratamiento identificador del tratamiento
	 * @return numero de apariciones
	 */
	public int contarEnHistorial(int idTratamiento) {
		String sql = "SELECT COUNT(*) FROM historial WHERE id_tratamiento=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idTratamiento);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error contando tratamiento en historial: " + e.getMessage());
		}
		return 0;
	}

	/**
	 * Mapea un ResultSet a un objeto Tratamiento.
	 * 
	 * @param rs el ResultSet
	 * @return el Tratamiento mapeado
	 */
	private Tratamiento mapear(ResultSet rs) throws SQLException {
		return new Tratamiento(rs.getInt("id_tratamiento"), rs.getString("nombre"), rs.getDouble("precio"));
	}
}