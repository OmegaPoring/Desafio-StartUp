package StartUp.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	//Propiedades
	
		private static Connection conn = null;

		//Constructor privado
		/*
		 * private Conexion() { String url="jdbc:mysql://localhost:3306/StartUp";
		 * String driver = "com.mysql.cj.jdbc.Driver"; String user = "root"; String
		 * password = "";
		 * 
		 * try { Class.forName(driver); try { conn = DriverManager.getConnection(url,
		 * user, password); } catch (SQLException se) { //Handle errors for JDBC
		 * se.printStackTrace(); } } catch (Exception ex) { //Handle errors for
		 * Class.forName ex.printStackTrace(); } }
		 */
		
		private Conexion() {
			String url="jdbc:postgresql://localhost:5432/StartUp";
			String driver = "org.postgresql.Driver";
			String user = "postgres";
			String password = "1234";
			
			try {
	            Class.forName(driver);
	            try {
	                conn = DriverManager.getConnection(url, user, password);
	            } catch (SQLException se) {
	                //Handle errors for JDBC
	                se.printStackTrace();
	            }
	        } catch (Exception ex) {
	            //Handle errors for Class.forName
	            ex.printStackTrace();
	        }
		}

		
		//METODOS para constructos privado
		public static Connection getConnection() {
			if(conn == null) {
				new Conexion();
				System.out.println("Se creo la conexion"); //DEBUG
				System.out.println("Metodo getConnection: "+conn);
				
				return conn;
			}
			else {
			System.out.println("Retorno conexion..."); //DEBUG
			return conn;
			}
		}
}