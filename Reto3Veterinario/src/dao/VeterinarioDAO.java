package dao;

import modelo.Veterinario;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO implements GenericDAO<Veterinario> {

	/**
	 * Inserta un veterinario. Primero inserta en personas y luego en veterinarios.
	 * 
	 * @param objeto el veterinario a insertar
	 * @return true si se inserto correctamente
	 */
	@Override
	public boolean insertar(Veterinario objeto) {
		String sqlPersona = "INSERT INTO personas(dni, nombre) VALUES(?,?)";
		String sqlVet = "INSERT INTO veterinarios(id_persona, num_colegiado) VALUES(?,?)";
		try (Connection con = ConexionBD.getConnection()) {
			con.setAutoCommit(false);
			PreparedStatement psP = con.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS);
			psP.setString(1, objeto.getDni());
			psP.setString(2, objeto.getNombre());
			psP.executeUpdate();
			ResultSet rs = psP.getGeneratedKeys();
			if (rs.next()) {
				objeto.setIdPersona(rs.getInt(1));
			}
			PreparedStatement psV = con.prepareStatement(sqlVet, Statement.RETURN_GENERATED_KEYS);
			psV.setInt(1, objeto.getIdPersona());
			psV.setString(2, objeto.getNumColegiado());
			psV.executeUpdate();
			ResultSet rs2 = psV.getGeneratedKeys();
			if (rs2.next()) {
				objeto.setIdVeterinario(rs2.getInt(1));
			}
			con.commit();
			return true;
		} catch (SQLException e) {
			System.out.println("Error insertando veterinario: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Obtiene todos los veterinarios con JOIN a personas.
	 * 
	 * @return lista de veterinarios
	 */
	@Override
	public List<Veterinario> obtenerTodos() {
		List<Veterinario> lista = new ArrayList<>();
		String sql = "SELECT v.id_veterinario, p.id_persona, p.dni, p.nombre, v.num_colegiado "
				+ "FROM veterinarios v INNER JOIN personas p ON v.id_persona = p.id_persona";
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
	 * Obtiene un veterinario por su id.
	 * 
	 * @param id identificador del veterinario
	 * @return el veterinario o null
	 */
	@Override
	public Veterinario obtenerPorId(int id) {
		String sql = "SELECT v.id_veterinario, p.id_persona, p.dni, p.nombre, v.num_colegiado "
				+ "FROM veterinarios v INNER JOIN personas p ON v.id_persona = p.id_persona "
				+ "WHERE v.id_veterinario = ?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
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
	 * Actualiza el numero de colegiado de un veterinario.
	 * 
	 * @param objeto el veterinario con datos actualizados
	 * @return true si se actualizo
	 */
	@Override
	public boolean actualizar(Veterinario objeto) {
		String sql = "UPDATE veterinarios SET num_colegiado=? WHERE id_veterinario=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, objeto.getNumColegiado());
			ps.setInt(2, objeto.getIdVeterinario());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error actualizando veterinario: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Elimina un veterinario por su id.
	 * 
	 * @param id identificador del veterinario
	 * @return true si se elimino
	 */
	@Override
	public boolean eliminar(int id) {
		String sql = "DELETE FROM veterinarios WHERE id_veterinario=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error eliminando veterinario: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Mapea un ResultSet a un objeto Veterinario.
	 * 
	 * @param rs el ResultSet
	 * @return el Veterinario mapeado
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
}
