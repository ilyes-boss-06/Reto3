package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Tratamiento;
import util.ConexionBD;

public class TratamientoDAO implements GenericDAO<Tratamiento> {

	@Override
	public boolean insertar(Tratamiento objeto) {

		return false;
	}

	@Override
	public List<Tratamiento> obtenerTodos() {
		return null;
	}

	@Override
	public Tratamiento obtenerPorId(int id) {
		ArrayList<Tratamiento> lista = new ArrayList<Tratamiento>();
		String sql = "SELECT t.id_tratamiento,t.nombre,t.precio from historial h inner join tratamientos t on h.id_tratamiento = t.id_tratamiento inner join veterinarios v on v.id_veterinario = h.id_veterinario where v.id_veterinario = ?";
		try (Connection conn = ConexionBD.getConnection(); 
		PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(2, id);
			try (ResultSet rs = pstmt.executeQuery()) {
			    while (rs.next()) {
			          lista.add(mapear(rs));
			    }
			}
		} catch (SQLException e) {
			System.err.println("Error SQL al buscar ID " + id + ": " + e.getMessage());
		}
		return null; // no encontrado
	}

	public List<Tratamiento> obtenerPorIdVeterinario(int id) {
		ArrayList<Tratamiento> lista = new ArrayList<Tratamiento>();
		String sql = "SELECT t.id_tratamiento,t.nombre,t.precio from historial h inner join tratamientos t on h.id_tratamiento = t.id_tratamiento inner join veterinarios v on v.id_veterinario = h.id_veterinario where v.id_veterinario = ?";
		try (Connection conn = ConexionBD.getConnection(); 
		PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
			    while (rs.next()) {
			          lista.add(mapear(rs));
			    }
			    return lista;
			}
		} catch (SQLException e) {
			System.err.println("Error SQL al buscar ID " + id + ": " + e.getMessage());
		}
		return null; // no encontrado
	}

	@Override
	public boolean actualizar(Tratamiento objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	private Tratamiento mapear(ResultSet rs) throws SQLException {
		Tratamiento a = new Tratamiento();
		a.setIdTratamiento(rs.getInt("id_tratamiento"));
		a.setNombre(rs.getString("nombre"));
		a.setPrecio(rs.getDouble("precio"));
		return a;
	}
}
