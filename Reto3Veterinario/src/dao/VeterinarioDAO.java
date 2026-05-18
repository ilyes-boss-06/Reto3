package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Veterinario;
import util.ConexionBD;

public class VeterinarioDAO implements GenericDAO<Veterinario>{

	/**
	 * Inserta un nuevo veterinario en la tabla veterinarios.
	 * @param objeto el veterinario a insertar (debe tener idPersona asignado)
	 * @return true si se insertó correctamente
	 */
	@Override
	public boolean insertar(Veterinario objeto) {
		String sql = "INSERT INTO veterinarios(id_persona, num_colegiado) VALUES(?,?)";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, objeto.getIdPersona());
			ps.setString(2, objeto.getNumColegiado());
			int filas = ps.executeUpdate();
			if (filas > 0) {
				try (ResultSet rs = ps.getGeneratedKeys()) {
					if (rs.next()) {
						objeto.setIdVeterinario(rs.getInt(1));
						return true;
					}
				}
			}
			return false;
		} catch (SQLException e) {
			System.out.println("Error insertando veterinario: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Obtiene todos los veterinarios con sus datos de persona mediante INNER JOIN.
	 * @return lista con todos los veterinarios
	 */
	@Override
	public List<Veterinario> obtenerTodos() {
		List<Veterinario> lista = new ArrayList<>();
		String sql = "SELECT v.id_veterinario, p.id_persona, p.dni, p.nombre, v.num_colegiado FROM veterinarios v INNER JOIN personas p ON v.id_persona = p.id_persona";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				lista.add(mapear(rs));
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo veterinarios: " + e.getMessage());
		}
		return lista;
	}
	/**
	 * Convierte una fila del ResultSet en un objeto Veterinario.
	 * @param rs el ResultSet posicionado en la fila actual
	 * @return el objeto Veterinario mapeado
	 * @throws SQLException si ocurre un error de acceso a datos
	 */
	private Veterinario mapear(ResultSet rs) throws SQLException {
		Veterinario v = new Veterinario();
		v.setIdVeterinario(rs.getInt("id_veterinario"));
		v.setIdPersona(rs.getInt("id_persona"));
		v.setDni(rs.getString("dni"));
		v.setNombre(rs.getString("nombre"));
		v.setNumColegiado(rs.getString("num_colegiado"));
		return v;
	}
	/**
	 * Obtiene un veterinario por su id_veterinario.
	 * @param id el identificador del veterinario
	 * @return el veterinario encontrado o null si no existe
	 */
	@Override
	public Veterinario obtenerPorId(int id) {
		String sql = "SELECT v.id_veterinario, p.id_persona, p.dni, p.nombre, v.num_colegiado FROM veterinarios v INNER JOIN personas p ON v.id_persona = p.id_persona WHERE v.id_veterinario = ?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapear(rs);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error buscando veterinario por id: " + e.getMessage());
		}
		return null;
	}

	/**
	 * Actualiza el número de colegiado de un veterinario existente.
	 * @param objeto el veterinario con los datos actualizados
	 * @return true si se actualizó correctamente
	 */
	@Override
	public boolean actualizar(Veterinario objeto) {
		String sql = "UPDATE veterinarios SET num_colegiado=? WHERE id_veterinario=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, objeto.getNumColegiado());
			ps.setInt(2, objeto.getIdVeterinario());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error actualizando veterinario: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Elimina un veterinario por su id_veterinario.
	 * @param id el identificador del veterinario a eliminar
	 * @return true si se eliminó correctamente
	 */
	@Override
	public boolean eliminar(int id) {
		String sql = "DELETE FROM veterinarios WHERE id_veterinario=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error eliminando veterinario: " + e.getMessage());
			return false;
		}
	}

}
