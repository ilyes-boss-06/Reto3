package dao;

import modelo.Historial;
import util.ConexionBD;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistorialDAO implements GenericDAO<Historial> {

	/**
	 * Inserta un nuevo registro en el historial.
	 * @param objeto el historial a insertar
	 * @return true si se insertó correctamente
	 */
	@Override
	public boolean insertar(Historial objeto) {
		String sql = "INSERT INTO historial(id_mascota, id_tratamiento, id_veterinario, fecha) VALUES(?,?,?,?)";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, objeto.getIdMascota());
			ps.setInt(2, objeto.getIdTratamiento());
			ps.setInt(3, objeto.getIdVeterinario());
			ps.setObject(4, objeto.getFecha());
			int filas = ps.executeUpdate();
			if (filas > 0) {
				try (ResultSet rs = ps.getGeneratedKeys()) {
					if (rs.next()) {
						objeto.setIdHistorial(rs.getInt(1));
						return true;
					}
				}
			}
			return false;
		} catch (SQLException e) {
			System.out.println("Error insertando historial: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Obtiene todos los registros del historial.
	 * @return lista con todos los registros
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
	 * @param id el identificador del historial
	 * @return el registro encontrado o null si no existe
	 */
	@Override
	public Historial obtenerPorId(int id) {
		String sql = "SELECT * FROM historial WHERE id_historial=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
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
	 * Actualiza un registro del historial existente.
	 * @param objeto el historial con los datos actualizados
	 * @return true si se actualizó correctamente
	 */
	@Override
	public boolean actualizar(Historial objeto) {
		String sql = "UPDATE historial SET id_mascota=?, id_tratamiento=?, id_veterinario=?, fecha=? WHERE id_historial=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, objeto.getIdMascota());
			ps.setInt(2, objeto.getIdTratamiento());
			ps.setInt(3, objeto.getIdVeterinario());
			ps.setObject(4, objeto.getFecha());
			ps.setInt(5, objeto.getIdHistorial());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error actualizando historial: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Elimina un registro del historial por su id.
	 * @param id el identificador del historial a eliminar
	 * @return true si se eliminó correctamente
	 */
	@Override
	public boolean eliminar(int id) {
		String sql = "DELETE FROM historial WHERE id_historial=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error eliminando historial: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Obtiene el historial completo de una mascota.
	 * @param idMascota el identificador de la mascota
	 * @return lista de registros del historial de esa mascota
	 */
	public List<Historial> obtenerPorMascota(int idMascota) {
		List<Historial> lista = new ArrayList<>();
		String sql = "SELECT * FROM historial WHERE id_mascota=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
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
	 * @param idVeterinario el identificador del veterinario
	 * @return lista de registros del historial de ese veterinario
	 */
	public List<Historial> obtenerPorVeterinario(int idVeterinario) {
		List<Historial> lista = new ArrayList<>();
		String sql = "SELECT * FROM historial WHERE id_veterinario=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
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
	 * @param idMascota el identificador de la mascota
	 * @param fecha la fecha a buscar
	 * @return lista de registros del historial en esa fecha
	 */
	public List<Historial> obtenerPorMascotaYFecha(int idMascota, LocalDate fecha) {
		List<Historial> lista = new ArrayList<>();
		String sql = "SELECT * FROM historial WHERE id_mascota=? AND fecha=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idMascota);
			ps.setObject(2, fecha);
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
	 * Cuenta los tratamientos realizados por un veterinario en un mes dado.
	 * @param idVeterinario el identificador del veterinario
	 * @param mes el número de mes (1-12)
	 * @return número de tratamientos en ese mes
	 */
	public int contarPorVeterinarioYMes(int idVeterinario, int mes) {
		String sql = "SELECT COUNT(*) FROM historial WHERE id_veterinario=? AND MONTH(fecha)=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
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
	 * Convierte una fila del ResultSet en un objeto Historial.
	 * @param rs el ResultSet posicionado en la fila actual
	 * @return el objeto Historial mapeado
	 * @throws SQLException si ocurre un error de acceso a datos
	 */
	private Historial mapear(ResultSet rs) throws SQLException {
		Historial h = new Historial();
		h.setIdHistorial(rs.getInt("id_historial"));
		h.setIdMascota(rs.getInt("id_mascota"));
		h.setIdTratamiento(rs.getInt("id_tratamiento"));
		h.setIdVeterinario(rs.getInt("id_veterinario"));
		h.setFecha(rs.getObject("fecha", LocalDate.class));
		return h;
	}
}
