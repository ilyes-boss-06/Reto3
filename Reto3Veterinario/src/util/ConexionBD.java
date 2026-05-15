package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
	private static final String URL = "jdbc:mysql://localhost:3306/prog_veterinaria";
	private static final String USER = "dam";
	private static final String PASS = "1234";

	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			System.out.println("Error al conectar " + e.getMessage());
		}
		return con;
	}
}