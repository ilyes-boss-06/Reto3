package dao;

import modelo.Mascota;
import util.ConexionBD;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO implements GenericDAO<Mascota> {

	/**
	 * Inserta una nueva mascota en la base de datos.
	 * @param objeto la mascota a insertar
	 * @return true si se insertó correctamente
	 */
	@Override
	public boolean insertar(Mascota objeto) {
		String sql = "INSERT INTO mascotas(id_cliente, nombre, especie, fecha_nacimiento, peso) VALUES(?,?,?,?,?)";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, objeto.getIdCliente());
			ps.setString(2, objeto.getNombre());
			ps.setString(3, objeto.getEspecie());
			ps.setObject(4, objeto.getFechaNacimiento());
			ps.setBigDecimal(5, objeto.getPeso());
			int filas = ps.executeUpdate();
			if (filas > 0) {
				try (ResultSet rs = ps.getGeneratedKeys()) {
					if (rs.next()) {
						objeto.setIdMascota(rs.getInt(1));
						return true;
					}
				}
			}
			return false;
		} catch (SQLException e) {
			System.out.println("Error insertando mascota: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Obtiene todas las mascotas de la base de datos.
	 * @return lista con todas las mascotas
	 */
	@Override
	public List<Mascota> obtenerTodos() {
		List<Mascota> lista = new ArrayList<>();
		String sql = "SELECT * FROM mascotas";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				lista.add(mapear(rs));
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo mascotas: " + e.getMessage());
		}
		return lista;
	}

	/**
	 * Obtiene una mascota por su id.
	 * @param id el identificador de la mascota
	 * @return la mascota encontrada o null si no existe
	 */
	@Override
	public Mascota obtenerPorId(int id) {
		String sql = "SELECT * FROM mascotas WHERE id_mascota=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapear(rs);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error buscando mascota por id: " + e.getMessage());
		}
		return null;
	}

	/**
	 * Actualiza los datos de una mascota existente.
	 * @param objeto la mascota con los datos actualizados
	 * @return true si se actualizó correctamente
	 */
	@Override
	public boolean actualizar(Mascota objeto) {
		String sql = "UPDATE mascotas SET id_cliente=?, nombre=?, especie=?, fecha_nacimiento=?, peso=? WHERE id_mascota=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, objeto.getIdCliente());
			ps.setString(2, objeto.getNombre());
			ps.setString(3, objeto.getEspecie());
			ps.setObject(4, objeto.getFechaNacimiento());
			ps.setBigDecimal(5, objeto.getPeso());
			ps.setInt(6, objeto.getIdMascota());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error actualizando mascota: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Elimina una mascota por su id.
	 * @param id el identificador de la mascota a eliminar
	 * @return true si se eliminó correctamente
	 */
	@Override
	public boolean eliminar(int id) {
		String sql = "DELETE FROM mascotas WHERE id_mascota=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error eliminando mascota: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Obtiene las mascotas de un cliente dado su id.
	 * @param idCliente el identificador del cliente
	 * @return lista de mascotas del cliente
	 */
	public List<Mascota> obtenerPorCliente(int idCliente) {
		List<Mascota> lista = new ArrayList<>();
		String sql = "SELECT * FROM mascotas WHERE id_cliente=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idCliente);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					lista.add(mapear(rs));
				}
			}
		} catch (SQLException e) {
			System.out.println("Error obteniendo mascotas por cliente: " + e.getMessage());
		}
		return lista;
	}

	/**
	 * Comprueba si una mascota tiene facturas asociadas.
	 * @param idMascota el identificador de la mascota
	 * @return true si tiene facturas asociadas
	 */
	public boolean tieneFacturas(int idMascota) {
		String sql = "SELECT COUNT(*) FROM facturas WHERE id_mascota=?";
		try (Connection con = ConexionBD.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idMascota);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (SQLException e) {
			System.out.println("Error comprobando facturas de mascota: " + e.getMessage());
		}
		return true;
	}

	/**
	 * Convierte una fila del ResultSet en un objeto Mascota.
	 * @param rs el ResultSet posicionado en la fila actual
	 * @return el objeto Mascota mapeado
	 * @throws SQLException si ocurre un error de acceso a datos
	 */
	private Mascota mapear(ResultSet rs) throws SQLException {
		Mascota m = new Mascota();
		m.setIdMascota(rs.getInt("id_mascota"));
		m.setIdCliente(rs.getInt("id_cliente"));
		m.setNombre(rs.getString("nombre"));
		m.setEspecie(rs.getString("especie"));
		m.setFechaNacimiento(rs.getObject("fecha_nacimiento", LocalDate.class));
		m.setPeso(rs.getBigDecimal("peso"));
		return m;
	}
}
