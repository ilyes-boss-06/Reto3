package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import util.ConexionBD;

public class ClienteDAO implements GenericDAO<Cliente>{

	@Override
	public boolean insertar(Cliente objeto) {
		String sql = "INSERT INTO clientes(id_persona, telefono) VALUES(?,?)";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, objeto.getIdPersona());
			ps.setString(2, objeto.getTenefono());
			int filas = ps.executeUpdate();
			if (filas > 0) {
				try (ResultSet rs = ps.getGeneratedKeys()) {
					if (rs.next()) {
						objeto.setIdCliente(rs.getInt(1));
						return true;
					}
				}
			}
			return false;
		} catch (SQLException e) {
			System.out.println("Error insertando cliente: " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<Cliente> obtenerTodos() {
		List<Cliente> lista = new ArrayList<>();
		String sql = "SELECT c.id_cliente, p.id_persona, p.dni, p.nombre, c.telefono "
				+ "FROM clientes c INNER JOIN personas p ON c.id_persona = p.id_persona";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				lista.add(mapear(rs));
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo clientes: " + e.getMessage());
		}
		return lista;
	}

	@Override
	public Cliente obtenerPorId(int id) {
		String sql = "SELECT c.id_cliente, p.id_persona, p.dni, p.nombre, c.telefono "
				+ "FROM clientes c INNER JOIN personas p ON c.id_persona = p.id_persona "
				+ "WHERE c.id_cliente = ?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapear(rs);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error buscando cliente por id: " + e.getMessage());
		}
		return null;
	}

	@Override
	public boolean actualizar(Cliente objeto) {
		String sql = "UPDATE clientes SET telefono=? WHERE id_cliente=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, objeto.getTenefono());
			ps.setInt(2, objeto.getIdCliente());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error actualizando cliente: " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean eliminar(int id) {
		String sql = "DELETE FROM clientes WHERE id_cliente=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error eliminando cliente: " + e.getMessage());
			return false;
		}
	}
	private Cliente mapear(ResultSet rs) throws SQLException {
		Cliente c = new Cliente();
		c.setIdCliente(rs.getInt("id_cliente"));
		c.setIdPersona(rs.getInt("id_persona"));
		c.setDni(rs.getString("dni"));
		c.setNombre(rs.getString("nombre"));
		c.setTenefono(rs.getString("telefono"));
		return c;
	}

}
