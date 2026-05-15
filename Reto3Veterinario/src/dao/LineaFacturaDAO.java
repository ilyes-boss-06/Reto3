package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.LineaFactura;
import util.ConexionBD;

public class LineaFacturaDAO implements GenericDAO<LineaFactura>{

	@Override
	public boolean insertar(LineaFactura objeto) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean actualizar(LineaFactura objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(int id) {
		// TODO Auto-generated method stub
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
