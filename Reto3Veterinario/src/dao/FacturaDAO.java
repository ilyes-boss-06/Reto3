package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.Factura;
import modelo.LineaFactura;
import util.ConexionBD;

public class FacturaDAO implements GenericDAO<Factura> {

	@Override
	public boolean insertar(Factura objeto) {
		String sql = "INSERT INTO facturas (idfactura) values (?)";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, objeto.getIdFactura());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return false;
	}
	

	@Override
	public List<Factura> obtenerTodos() {
		List<Factura> lista = new ArrayList<Factura>();
		String sql = "Select * from Facturas";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				lista.add(mapear(rs));
			}
			return lista;

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	@Override
	public Factura obtenerPorId(int id) {
		List<Factura> lista = new ArrayList<Factura>();
		String sql = "...";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return mapear(rs);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	@Override
	public boolean actualizar(Factura objeto) {
		String sql = "UPDATE facturas set fecha = ? where id = ? ";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setObject(1, objeto.getFecha());
			ps.setInt(2, objeto.getIdFactura());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		String sql = "DELETE FROM facturas WHERE id = ? ";
		try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return false;
	}
	
	private Factura mapear(ResultSet rs) throws SQLException {
        Factura a = new Factura();
	 a.setIdFactura(rs.getInt("id_factura"));
	 a.setIdCliente(rs.getInt("id_cliente"));
	 a.setIdVeterinario(rs.getInt("id_veterinatio"));
	 a.setIdMascota(rs.getInt("id_mascota"));
	 a.setFecha(rs.getObject("fecha",LocalDate.class));
	 a.setSubtotal(rs.getDouble("subtotal"));
	 a.setTotal_iva(rs.getDouble("total_iva"));
	 a.setTotal(rs.getDouble("total"));
	  return a;
    }

}
