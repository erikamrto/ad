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
		System.out.print("Introduce un Nombre: ");
		String nombre = tcl.nextLine();
		System.out.print("Introduce una Categoria: ");
		long categoria = tcl.nextLong();
		System.out.print("Introduce un Precio: ");
		BigDecimal precio = tcl.nextBigDecimal();
		PreparedStatement pst = connection.prepareStatement("INSERT INTO articulo (nombre, categoria, precio) VALUES (?, ?, ?)");
		pst.setString(1,nombre);
		pst.setLong(2,categoria);
		pst.setBigDecimal(3,precio);
		pst.executeUpdate();
		pst.close();
		connection.close();
		Listar();
		Menu.Volver();
	}

	public static void Editar() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba", "root", "sistemas");
		Statement stp = connection.createStatement();
		Scanner tcl = new Scanner(System.in);
		System.out.print("Introduce id del dato a modificar: ");
		long id = tcl.nextLong();
		String sql = "SELECT * FROM articulo where ID =" + id;
		ResultSet rs = stp.executeQuery(sql);

		while (rs.next()) {
			String nombre = rs.getString("nombre");
			BigDecimal precio = rs.getBigDecimal("precio");
			long categoria = rs.getLong("categoria");
			System.out.println(
					"ID: " + id + " nombre: " + nombre + " 		categoria: " + categoria + " 		precio: " + precio);
		}
		
		tcl.nextLine();
		System.out.println("¿Qué deseas editar? Nombre/Categoria/Precio");
		String editar = tcl.nextLine().toLowerCase();

		if (editar.equals("nombre")) {
			PreparedStatement pst = connection.prepareStatement("UPDATE articulo SET nombre = ? WHERE id = ?");
			System.out.println("Introduzca el nombre: ");
			String newN = tcl.nextLine();
			pst.setString(1,newN);
			pst.setLong(2,id);
			pst.executeUpdate();
			pst.close();
			System.out.println("Dato actualizado.");

		} else if (editar.equals("categoria")) {
			PreparedStatement pst = connection.prepareStatement("UPDATE articulo SET categoria = ? WHERE id = ? ");
			System.out.println("Introduzca la Categoria: ");
			String newC = tcl.nextLine();
			pst.setString(1,newC);
			pst.setLong(2,id);
			pst.executeUpdate();
			pst.close();
			System.out.println("Dato actualizado.");

		} else if (editar.equals("precio")) {
			PreparedStatement pst = connection.prepareStatement("UPDATE articulo SET precio = ? WHERE id = ?");
			System.out.println("Introduzca la Precio: ");
			String newP = tcl.nextLine();
			pst.setString(1,newP);
			pst.setLong(2,id);
			pst.executeUpdate();
			pst.close();
			System.out.println("Dato actualizado.");
		}
		Listar();
		rs.close();
		stp.close();
		connection.close();
		Menu.Volver();
	}

	public static void Eliminar() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba", "root", "sistemas");

		Scanner tcl = new Scanner(System.in);
		System.out.println("Introduce el numero de la id para eliminar: ");
		long id = tcl.nextLong();
		 {
			PreparedStatement pst = connection.prepareStatement("DELETE FROM articulo where ID = ?");

			pst.setLong(1, id);
			pst.executeUpdate();

			System.out.println("Dato eliminado.");
		}

		
		Listar();
		connection.close();
		Menu.Volver();
	}

	public static void Consultar() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba", "root", "sistemas");
		Statement stp = connection.createStatement();
		Scanner tcl = new Scanner(System.in);
		System.out.println("Dato para consultar: ");
		long id = tcl.nextLong();
		String sql = "SELECT * FROM articulo where ID =" + id;
		ResultSet rs = stp.executeQuery(sql);

		while (rs.next()) {
			String nombre = rs.getString("nombre");
			BigDecimal precio = rs.getBigDecimal("precio");
			long categoria = rs.getLong("categoria");
			System.out.println(
					"ID: " + id + " 	nombre: " + nombre + " 	categoria: " + categoria 
					+ " 		precio: " + precio);
		}
		Menu.Volver();
		rs.close();
		stp.close();
		connection.close();
	}

	public static void Listar() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dbprueba", "root", "sistemas");
		Statement stp = connection.createStatement();

		String sql = "SELECT * FROM articulo";
		ResultSet rs = stp.executeQuery(sql);
		while (rs.next()) {
			long id = rs.getLong("id");
			String nombre = rs.getString("nombre");
			BigDecimal precio = rs.getBigDecimal("precio");
			long categoria = rs.getLong("categoria");
			System.out.println("ID: " + id + " 		nombre: " + nombre + " 		categoria: " + categoria
					+ " 		precio: " + precio);
		}
		Menu.Volver();
		rs.close();
		stp.close();
		connection.close();
	}
}
