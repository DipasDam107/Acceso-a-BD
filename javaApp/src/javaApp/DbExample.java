package javaApp;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class DbExample {

	static public Connection connect() throws SQLException
    {
     String url = "jdbc:postgresql://192.168.56.101/calendar";
     Properties props = new Properties();
     props.setProperty("user","postgres");
     props.setProperty("password","1234abc.");
     props.setProperty("ssl","false");
     return DriverManager.getConnection(url, props);     
    }
    
  public static void main(String[] args) throws SQLException  
	{
	 try
	   {
	    Class.forName("org.postgresql.Driver");
	   }
	 catch(ClassNotFoundException ex)
	   {
	    System.out.println("Error al registrar el driver de PostgreSQL: "+ex); 
	   }
	 
	 
	 Scanner teclado = new Scanner(System.in);
	 System.out.println("Dime nombre: ");
	 String nom = teclado.nextLine();
	
     Connection conn=connect();
     PreparedStatement ps = conn.prepareStatement("SELECT nombre_usuario, nombre, apellido FROM calendar.usuarios WHERE nombre LIKE ?;");
	 ps.setString(1, nom);
	 ResultSet rs = ps.executeQuery();
	 while(rs.next())
		{
		 System.out.printf("Usuario: %s\tNombre: %s\tApellido: %s\n",rs.getString(1),rs.getString(2),rs.getString(3));
		}
	 rs.close();
	 ps.close();
	}
}
