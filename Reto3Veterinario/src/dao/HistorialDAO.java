package dao;

import modelo.Historial;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialDAO implements GenericDAO<Historial> {

	/**
	 * Inserta un nuevo registro en el historial.
	 * @param objeto el historial a insertar
	 * @return true si se inserto correctamente
	 */
	@Override
	public boolean insertar(Historial objeto) {
		String sql = "INSERT INTO historial(id_mascota, id_tratamiento, id_veterinario, fecha) VALUES(?,?,?,?)";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, objeto.getIdMascota());
			ps.setInt(2, objeto.getIdTratamiento());
			ps.setInt(3, objeto.getIdVeterinario());
			ps.setString(4, objeto.getFecha());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error insertando historial: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Obtiene todos los registros del historial.
	 * @return lista de registros
	 */
	@Override
	public List<Historial> obtenerTodos() {
		List<Historial> lista = new ArrayList<>();
		String sql = "SELECT * FROM historial";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				lista.add(mapear(rs));
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo historial: " + e.getMessage());
		}
		return lista;
	}

	/**
	 * Obtiene un registro del historial por su id.
	 * @param id identificador del historial
	 * @return el registro o null
	 */
	
	@Override
	public Historial obtenerPorId(int id) {
		String sql = "SELECT * FROM historial WHERE id_historial=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapear(rs);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error buscando historial por id: " + e.getMessage());
		}
		return null;
	}

	/**
	 * Actualiza un registro del historial.
	 * @param objeto el historial con datos actualizados
	 * @return true si se actualizo
	 */
	
	@Override
	public boolean actualizar(Historial objeto) {
		String sql = "UPDATE historial SET id_mascota=?, id_tratamiento=?, id_veterinario=?, fecha=? WHERE id_historial=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, objeto.getIdMascota());
			ps.setInt(2, objeto.getIdTratamiento());
			ps.setInt(3, objeto.getIdVeterinario());
			ps.setString(4, objeto.getFecha());
			ps.setInt(5, objeto.getIdHistorial());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error actualizando historial: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Elimina un registro del historial por su id.
	 * @param id identificador del historial
	 * @return true si se elimino
	 */
	
	@Override
	public boolean eliminar(int id) {
		String sql = "DELETE FROM historial WHERE id_historial=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error eliminando historial: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Obtiene el historial de una mascota.
	 * @param idMascota identificador de la mascota
	 * @return lista de registros
	 */
	
	public List<Historial> obtenerPorMascota(int idMascota) {
		List<Historial> lista = new ArrayList<>();
		String sql = "SELECT * FROM historial WHERE id_mascota=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idMascota);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					lista.add(mapear(rs));
				}
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo historial por mascota: " + e.getMessage());
		}
		return lista;
	}

	/**
	 * Obtiene los tratamientos realizados por un veterinario.
	 * @param idVeterinario identificador del veterinario
	 * @return lista de registros
	 */
	public List<Historial> obtenerPorVeterinario(int idVeterinario) {
		List<Historial> lista = new ArrayList<>();
		String sql = "SELECT * FROM historial WHERE id_veterinario=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idVeterinario);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					lista.add(mapear(rs));
				}
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo historial por veterinario: " + e.getMessage());
		}
		return lista;
	}

	/**
	 * Obtiene el historial de una mascota en una fecha concreta.
	 * 
	 * @param idMascota identificador de la mascota
	 * @param fecha     la fecha a buscar
	 * @return lista de registros
	 */
	public List<Historial> obtenerPorMascotaYFecha(int idMascota, String fecha) {
		List<Historial> lista = new ArrayList<>();
		String sql = "SELECT * FROM historial WHERE id_mascota=? AND fecha=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idMascota);
			ps.setString(2, fecha);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					lista.add(mapear(rs));
				}
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo historial por mascota y fecha: " + e.getMessage());
		}
		return lista;
	}

	/**
	 * Cuenta tratamientos de un veterinario en un mes dado.
	 * 
	 * @param idVeterinario identificador del veterinario
	 * @param mes           numero de mes (1-12)
	 * @return numero de tratamientos
	 */
	
	public int contarPorVeterinarioYMes(int idVeterinario, int mes) {
		String sql = "SELECT COUNT(*) FROM historial WHERE id_veterinario=? AND MONTH(fecha)=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idVeterinario);
			ps.setInt(2, mes);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error contando historial por vet y mes: " + e.getMessage());
		}
		return 0;
	}

	/**
	 * Mapea un ResultSet a un objeto Historial.
	 * 
	 * @param rs el ResultSet
	 * @return el Historial mapeado
	 */
	
	private Historial mapear(ResultSet rs) throws SQLException {
		return new Historial(
				rs.getInt("id_historial"),
				rs.getInt("id_mascota"),
				rs.getInt("id_tratamiento"),
				rs.getInt("id_veterinario"),
				rs.getString("fecha"));
	}
}
