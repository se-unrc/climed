import java.io.*;
import java.sql.*;

public class ConectaBD {
    
    Connection connection = null;
 
    public ConectaBD(String host, String user, String pas){
		try {
			//Leemos el driver de Oracle
			Class.forName("org.postgresql.Driver");

			//Nos conectamos a la BD local	
			connection = DriverManager.getConnection(host, user, pas);
		  	
		} 
		catch (ClassNotFoundException e1) {
		            //Error si no puedo leer el driver de Oracle 
			System.out.println("ERROR:No encuentro el driver de la BD: "+
			e1.getMessage());
		}
		catch (SQLException e2) {
		            //Error SQL: login/passwd mal
			System.out.println(e2.getMessage());
		}

	  }

	public void destroy(){
		
		try {
				if (connection!=null)
					connection.close();
			} catch (SQLException e3) {
				System.out.println("ERROR:Fallo al desconectar de la BD: "+
					e3.getMessage());
			}
	
	}
	 
	 public ResultSet doQuery(String query){
	  	
	  	try{
	 		PreparedStatement statement = connection.prepareStatement(query);
			return (ResultSet)statement.executeQuery();
		}catch(SQLException e){
			System.out.println(e);
			return null;
		}	  
	}

	public void doUpdate(String query){
		try{
			PreparedStatement statement = connection.prepareStatement(query);
			statement.executeUpdate();
		}catch(SQLException e){
			System.out.println(e);
		}
	}
}
