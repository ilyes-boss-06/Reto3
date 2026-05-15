package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.LineaFactura;
import util.ConexionBD;

public class LineaFacturaDAO implements GenericDAO<LineaFactura>{

	@Override
	public boolean insertar(LineaFactura objeto) {
		String sql = "INSERT INTO linea_factura (idfactura) values (?)";
		   try (Connection con = ConexionBD.getConnection();
		        PreparedStatement ps = con.prepareStatement(sql)) {
		          ps.setInt(1,objeto.getIdLineaFactura());
		          
		    return ps.executeUpdate() > 0;
		    } catch (SQLException e) {
		            System.out.println("Error: " + e.getMessage());
		    }
		   return false;
	    }



	
	
	
	
	
	
	
	
	
	@Override
	public List<LineaFactura> obtenerTodos() {
		List<LineaFactura> lista = new ArrayList<LineaFactura>();
		String sql = "Select * from Lineas_Factura";
		   try (Connection con = ConexionBD.getConnection();
		        PreparedStatement ps = con.prepareStatement(sql)) {
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
	public LineaFactura obtenerPorId(int id) {
		List<LineaFactura> lista = new ArrayList<LineaFactura>();
		String sql = "...";
		   try (Connection con = ConexionBD.getConnection();
		        PreparedStatement ps = con.prepareStatement(sql)) {
			   
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
	public boolean actualizar(LineaFactura objeto) {
		
		String sql = "UPDATE linea_factura set fecha = ? where id = ? ";
		   try (Connection con = ConexionBD.getConnection();
		        PreparedStatement ps = con.prepareStatement(sql)) {
		          ps.setObject(1, objeto.getFecha());
		          ps.setInt(2, objeto.getIdLineaFactura());
		          
		    return ps.executeUpdate() > 0;
		    } catch (SQLException e) {
		            System.out.println("Error: " + e.getMessage());
		    }
		   return false;
		    
}
		  

	

	@Override
	public boolean eliminar(int id) {
		String sql = "DELETE FROM linea_factura WHERE id = ? ";
		   try (Connection con = ConexionBD.getConnection();
		        PreparedStatement ps = con.prepareStatement(sql)) {
		          ps.setInt(1,id);
		          
		    return ps.executeUpdate() > 0;
		    } catch (SQLException e) {
		            System.out.println("Error: " + e.getMessage());
		    }
		   return false;
	}
	


    private LineaFactura mapear(ResultSet rs) throws SQLException {
        LineaFactura a = new LineaFactura();
	 a.setIdLineaFactura(rs.getInt("id_linea_factura"));
	 a.setIdFactura(rs.getInt("id_factura"));
	 a.setIdTratamiento(rs.getInt("id_tratamiento"));
	 a.setFecha(rs.getObject("fecha",LocalDate.class));
	 a.setCantidad(rs.getInt("cantidad"));
	 a.setPrecioTratamiento(rs.getDouble("precio_tratamiento"));
	 a.setImporte(rs.getDouble("importe"));
	  return a;
    }


}
