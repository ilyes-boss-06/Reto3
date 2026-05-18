package dao;

import modelo.Tratamiento;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TratamientoDAO implements GenericDAO<Tratamiento> {

	/**
	 * Inserta un nuevo tratamiento en la base de datos.
	 * @param objeto el tratamiento a insertar
	 * @return true si se insertó correctamente
	 */
	
	@Override
	public boolean insertar(Tratamiento objeto) {
		String sql = "INSERT INTO tratamientos(nombre, precio) VALUES(?,?)";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, objeto.getNombre());
			ps.setBigDecimal(2, objeto.getPrecio());
			int filas = ps.executeUpdate();
			if (filas > 0) {
				try (ResultSet rs = ps.getGeneratedKeys()) {
					if (rs.next()) {
						objeto.setIdTratamiento(rs.getInt(1));
						return true;
					}
				}
			}
			return false;
		} catch (SQLException e) {
			System.out.println("Error insertando tratamiento: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Obtiene todos los tratamientos de la base de datos.
	 * @return lista con todos los tratamientos
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
	 * @param id el identificador del tratamiento
	 * @return el tratamiento encontrado o null si no existe
	 */
	
	@Override
	public Tratamiento obtenerPorId(int id) {
		String sql = "SELECT * FROM tratamientos WHERE id_tratamiento=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
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
	 * Actualiza los datos de un tratamiento existente.
	 * @param objeto el tratamiento con los datos actualizados
	 * @return true si se actualizó correctamente
	 */
	
	@Override
	public boolean actualizar(Tratamiento objeto) {
		String sql = "UPDATE tratamientos SET nombre=?, precio=? WHERE id_tratamiento=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, objeto.getNombre());
			ps.setBigDecimal(2, objeto.getPrecio());
			ps.setInt(3, objeto.getIdTratamiento());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error actualizando tratamiento: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Elimina un tratamiento por su id.
	 * @param id el identificador del tratamiento a eliminar
	 * @return true si se eliminó correctamente
	 */
	
	@Override
	public boolean eliminar(int id) {
		String sql = "DELETE FROM tratamientos WHERE id_tratamiento=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error eliminando tratamiento: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Cuenta cuántas veces aparece un tratamiento en el historial.
	 * @param idTratamiento el identificador del tratamiento
	 * @return número de apariciones en el historial
	 */
	
	public int contarEnHistorial(int idTratamiento) {
		String sql = "SELECT COUNT(*) FROM historial WHERE id_tratamiento=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
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
	 * Convierte una fila del ResultSet en un objeto Tratamiento.
	 * @param rs el ResultSet posicionado en la fila actual
	 * @return el objeto Tratamiento mapeado
	 * @throws SQLException si ocurre un error de acceso a datos
	 */
	
	private Tratamiento mapear(ResultSet rs) throws SQLException {
		Tratamiento t = new Tratamiento();
		t.setIdTratamiento(rs.getInt("id_tratamiento"));
		t.setNombre(rs.getString("nombre"));
		t.setPrecio(rs.getBigDecimal("precio"));
		return t;
	}
}
