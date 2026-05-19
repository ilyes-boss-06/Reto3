package dao;

import modelo.Cliente;
import util.ConexionBD;

<<<<<<< HEAD
=======
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

>>>>>>> branch 'main' of https://github.com/ilyes-boss-06/Reto3.git
public class ClienteDAO implements GenericDAO<Cliente> {

	/**
<<<<<<< HEAD
	 * Inserta un nuevo cliente en la tabla clientes.
	 * 
	 * @param objeto el cliente a insertar (debe tener idPersona asignado)
	 * @return true si se insertó correctamente
=======
	 * Inserta un cliente. Primero inserta en personas y luego en clientes.
	 * @param objeto el cliente a insertar
	 * @return true si se inserto correctamente
>>>>>>> branch 'main' of https://github.com/ilyes-boss-06/Reto3.git
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
<<<<<<< HEAD
	 * Obtiene todos los clientes con sus datos de persona mediante INNER JOIN.
	 * 
	 * @return lista con todos los clientes
=======
	 * Obtiene todos los clientes con JOIN a personas.
	 * @return lista de clientes
>>>>>>> branch 'main' of https://github.com/ilyes-boss-06/Reto3.git
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
<<<<<<< HEAD
	 * Obtiene un cliente por su id_cliente.
	 * 
	 * @param id el identificador del cliente
	 * @return el cliente encontrado o null si no existe
=======
	 * Obtiene un cliente por su id.
	 * @param id identificador del cliente
	 * @return el cliente o null
>>>>>>> branch 'main' of https://github.com/ilyes-boss-06/Reto3.git
	 */

	@Override
	public Cliente obtenerPorId(int id) {
		String sql = "SELECT c.id_cliente, p.id_persona, p.dni, p.nombre, c.telefono "
				+ "FROM clientes c INNER JOIN personas p ON c.id_persona = p.id_persona " + "WHERE c.id_cliente = ?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
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
<<<<<<< HEAD
	 * Actualiza el teléfono de un cliente existente.
	 * 
	 * @param objeto el cliente con los datos actualizados
	 * @return true si se actualizó correctamente
=======
	 * Actualiza el telefono de un cliente.
	 * @param objeto el cliente con datos actualizados
	 * @return true si se actualizo
>>>>>>> branch 'main' of https://github.com/ilyes-boss-06/Reto3.git
	 */

	@Override
	public boolean actualizar(Cliente objeto) {
		String sql = "UPDATE clientes SET telefono=? WHERE id_cliente=?";
<<<<<<< HEAD
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, objeto.getTenefono());
=======
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, objeto.getTelefono());
>>>>>>> branch 'main' of https://github.com/ilyes-boss-06/Reto3.git
			ps.setInt(2, objeto.getIdCliente());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error actualizando cliente: " + e.getMessage());
			return false;
		}
	}

	/**
<<<<<<< HEAD
	 * Elimina un cliente por su id_cliente.
	 * 
	 * @param id el identificador del cliente a eliminar
	 * @return true si se eliminó correctamente
=======
	 * Elimina un cliente por su id.
	 * @param id identificador del cliente
	 * @return true si se elimino
>>>>>>> branch 'main' of https://github.com/ilyes-boss-06/Reto3.git
	 */

	@Override
	public boolean eliminar(int id) {
		String sql = "DELETE FROM clientes WHERE id_cliente=?";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error eliminando cliente: " + e.getMessage());
			return false;
		}
	}

	/**
<<<<<<< HEAD
	 * Convierte una fila del ResultSet en un objeto Cliente.
	 * 
	 * @param rs el ResultSet posicionado en la fila actual
	 * @return el objeto Cliente mapeado
	 * @throws SQLException si ocurre un error de acceso a datos
=======
	 * Mapea un ResultSet a un objeto Cliente.
	 * @param rs el ResultSet
	 * @return el Cliente mapeado
>>>>>>> branch 'main' of https://github.com/ilyes-boss-06/Reto3.git
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
