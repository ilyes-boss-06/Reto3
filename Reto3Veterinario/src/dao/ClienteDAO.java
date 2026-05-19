package dao;

import modelo.Cliente;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements GenericDAO<Cliente> {

	/**
	 * Inserta un cliente. Primero inserta en personas y luego en clientes.
	 * @param objeto el cliente a insertar
	 * @return true si se inserto correctamente
	 */
	@Override
	public boolean insertar(Cliente objeto) {
		String sqlPersona = "INSERT INTO personas(dni, nombre) VALUES(?,?)";
		String sqlCliente = "INSERT INTO clientes(id_persona, telefono) VALUES(?,?)";
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
			PreparedStatement psC = con.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS);
			psC.setInt(1, objeto.getIdPersona());
			psC.setString(2, objeto.getTelefono());
			psC.executeUpdate();
			ResultSet rs2 = psC.getGeneratedKeys();
			if (rs2.next()) {
				objeto.setIdCliente(rs2.getInt(1));
			}
			con.commit();
			return true;
		} catch (SQLException e) {
			System.out.println("Error insertando cliente: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Obtiene todos los clientes con JOIN a personas.
	 * @return lista de clientes
	 */
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

	/**
	 * Obtiene un cliente por su id.
	 * @param id identificador del cliente
	 * @return el cliente o null
	 */
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

	/**
	 * Actualiza el telefono de un cliente.
	 * @param objeto el cliente con datos actualizados
	 * @return true si se actualizo
	 */
	@Override
	public boolean actualizar(Cliente objeto) {
		String sql = "UPDATE clientes SET telefono=? WHERE id_cliente=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, objeto.getTelefono());
			ps.setInt(2, objeto.getIdCliente());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error actualizando cliente: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Elimina un cliente por su id.
	 * @param id identificador del cliente
	 * @return true si se elimino
	 */
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

	/**
	 * Mapea un ResultSet a un objeto Cliente.
	 * @param rs el ResultSet
	 * @return el Cliente mapeado
	 */
	private Cliente mapear(ResultSet rs) throws SQLException {
		return new Cliente(
				rs.getInt("id_cliente"),
				rs.getInt("id_persona"),
				rs.getString("dni"),
				rs.getString("nombre"),
				rs.getString("telefono")
		);
	}
}
