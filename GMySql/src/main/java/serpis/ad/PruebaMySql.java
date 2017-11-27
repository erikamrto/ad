package serpis.ad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PruebaMySql {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/dbprueba", "root", "sistemas");
		
		Statement stmt = connection.createStatement();

	    Class.forName("com.mysql.jdbc.Driver");

	    String sql = "SELECT * FROM categoria";
	    ResultSet rs = stmt.executeQuery(sql);
	    
	    while(rs.next()){
	         int id  = rs.getInt("id");
	         String nombre = rs.getString("nombre");

	         System.out.println("ID: " + id + " nombre: " + nombre);
	      }
	    
	      rs.close();
	      stmt.close();
	      connection.close();
	}
}
