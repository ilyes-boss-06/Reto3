package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Veterinario;
import util.ConexionBD;

public class VeterinarioDAO implements GenericDAO<Veterinario>{

	@Override
	public boolean insertar(Veterinario objeto) {
		
		return false;
	}

	@Override
	public List<Veterinario> obtenerTodos() {
		List<Veterinario> editorial = new ArrayList<>();
        String sql = "select id, nombre, ciudad from editorial;";

            try (Connection conn = ConexionBD.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    editorial.add(mapearFila(rs));

                }

            } catch (SQLException e) {
                System.err.println("Error SQL al obtener todos los alumnos: " + e.getMessage());
            }
            return editorial;
	}
	private Veterinario mapearFila(ResultSet rs) throws SQLException {
        Veterinario d = new Veterinario();

        d.

        return d;

    }
	@Override
	public Veterinario obtenerPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean actualizar(Veterinario objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
