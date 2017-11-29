package serpis.ad;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class ArticuloDao {

	
	public static void Nuevo() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba", "root", "sistemas");
		Statement stp = connection.createStatement();
		Scanner tcl = new Scanner(System.in);
		System.out.println("Introduce un dato nuevo: ");
		String sql = "INSERT FROM articulo";
		ResultSet rs = stp.executeQuery(sql);
	}
	
	
	public static void Editar() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba", "root", "sistemas");
		Statement stp = connection.createStatement();
		Scanner tcl = new Scanner(System.in);
		System.out.println("Introduce un dato nuevo: ");
		String sql = "UPDATE FROM articulo";
		ResultSet rs = stp.executeQuery(sql);
	}
	
	
	public static void Eliminar() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba", "root", "sistemas");
		Statement stp = connection.createStatement();
		
		Scanner tcl = new Scanner(System.in);
		System.out.println("Introduce el numero de la id para eliminar: ");
		long id = tcl.nextLong();
		String sql = "DELETE FROM articulo where ID = ?";
		

		PreparedStatement pst = connection.prepareStatement(sql);
		
		pst.setLong(1, id);
		pst.executeUpdate();
		
		System.out.println("Dato eliminado.");
		stp.close();
		connection.close();
		
	}

	public static void Consultar() throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba", "root", "sistemas");
		Statement stp = connection.createStatement();
		Scanner tcl = new Scanner(System.in);
		System.out.println("Introduce el numero de la id a consultar: ");
		long id = tcl.nextLong();
		String sql = "SELECT * FROM articulo where ID =" + id;
		ResultSet rs = stp.executeQuery(sql);

		while (rs.next()) {
			String nombre = rs.getString("nombre");
			BigDecimal precio = rs.getBigDecimal("precio");
			long categoria = rs.getLong("categoria");
			System.out.println("ID: " + id + " nombre: " + nombre + " categoria: " + categoria + " precio: " + precio);
		}

		rs.close();
		stp.close();
		connection.close();
	}

	public static void Listar() throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba", "root", "sistemas");
		Statement stp = connection.createStatement();

		String sql = "SELECT * FROM articulo";
		ResultSet rs = stp.executeQuery(sql);
		while (rs.next()) {
			long id = rs.getLong("id");
			String nombre = rs.getString("nombre");
			BigDecimal precio = rs.getBigDecimal("precio");
			long categoria = rs.getLong("categoria");
			System.out.println("ID: " + id + " 	nombre: " + nombre + " 	categoria: " + categoria + " 	precio: " + precio);
		}

		rs.close();
		stp.close();
		connection.close();
	}
}
