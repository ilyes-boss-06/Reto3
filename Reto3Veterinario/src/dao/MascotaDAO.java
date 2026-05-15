package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Mascota;
import util.ConexionBD;

/**
 * Clase DAO para gestionar las operaciones de persistencia de la tabla Mascotas.
 */
public class MascotaDAO implements GenericDAO<Mascota> {

    /**
     * Inserta una nueva mascota en la base de datos.
     * @param m Objeto Mascota con los datos a insertar.
     * @return true si se insertó correctamente, false en caso contrario.
     */
	
    @Override
    public boolean insertar(Mascota m) {
        String sql = "INSERT INTO mascotas (id_cliente, nombre, especie, fecha_nacimiento, peso) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, m.getIdCliente());
            ps.setString(2, m.getNombre());
            ps.setString(3, m.getEspecie());
            ps.setObject(4, m.getFechaNacimiento());
            ps.setDouble(5, m.getPeso());
            int filas = ps.executeUpdate();
            if (filas > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    m.setIdMascota(rs.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar mascota: " + e.getMessage());
            return false;
        }
		return false;
    }

    /**
     * Obtiene todas las mascotas registradas.
     * @return Lista de objetos Mascota.
     */
    
    @Override
    public List<Mascota> obtenerTodos() {
        List<Mascota> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascotas";
        try (Connection con = ConexionBD.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener mascotas: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Busca una mascota por su identificador único.
     * @param id Identificador de la mascota.
     * @return Objeto Mascota si existe, null si no.
     */
    
    @Override
    public Mascota obtenerPorId(int id) {
        String sql = "SELECT * FROM mascotas WHERE id_mascota = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar mascota: " + e.getMessage());
        }
        return null;
    }

    /**
     * Obtiene la lista de mascotas que pertenecen a un cliente concreto.
     * @param idCliente ID del dueño de las mascotas.
     * @return Lista de mascotas del cliente.
     */
    
    public List<Mascota> obtenerPorCliente(int idCliente) {
        List<Mascota> lista = new ArrayList<>();
        String sql = "SELECT * FROM mascotas WHERE id_cliente = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener mascotas por cliente: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Actualiza los datos de una mascota existente.
     * @param m Objeto Mascota con los nuevos datos.
     * @return true si se actualizó, false si hubo error.
     */
    
    @Override
    public boolean actualizar(Mascota m) {
        String sql = "UPDATE mascotas SET nombre=?, especie=?, peso=? WHERE id_mascota=?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getEspecie());
            ps.setDouble(3, m.getPeso());
            ps.setInt(4, m.getIdMascota());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Elimina una mascota de la base de datos.
     * @param id ID de la mascota a borrar.
     * @return true si se borró, false en caso contrario.
     */
    
    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM mascotas WHERE id_mascota = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Convierte una fila del ResultSet en un objeto Mascota.
     */
    
    private Mascota mapear(ResultSet rs) throws SQLException {
        return new Mascota(
            rs.getInt("id_mascota"),
            rs.getInt("id_cliente"),
            rs.getString("nombre"),
            rs.getString("especie"),
            rs.getDate("fecha_nacimiento").toLocalDate(),
            rs.getDouble("peso")
        );
    }
}