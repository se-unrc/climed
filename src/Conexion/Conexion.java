package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {   
	private static Conexion instancia = null;
	private static final String driver = "org.postgresql.Driver";
	private final String url = "jdbc:postgresql://localhost:5432";
	private final String bd = "climed_bd";
	private final String usuario = "postgres";
	private final String password = "root";
	private static Connection conexion;
	private String baseDeDatos = url + "/" + bd;
	private final String urlBDPostgres = url+"/postgres";
	
	public synchronized static Connection getInstancia(){
		if (instancia == null)
			instancia = new Conexion();
		return conexion;
	}

	/**
	 * Realiza la conexion a la BD climed_db con el usuario climed
	 **/
	private Conexion(){
		try{
			Class.forName(driver);
			conexion = DriverManager.getConnection(baseDeDatos, usuario, password);
		}
		catch(SQLException E){
			System.out.println("Creando la base de datos " + bd);
			Statement st;
			try {
				conexion = DriverManager.getConnection(urlBDPostgres, usuario, password);
				st = conexion.createStatement();
				st.executeUpdate("CREATE DATABASE " + bd);
				conexion.close();

				conexion = DriverManager.getConnection(baseDeDatos, usuario, password);
			} catch (SQLException e) {
				System.out.println("Fallo de conexion");
				e.printStackTrace();
			}
		}
		catch(ClassNotFoundException E){
			System.out.println("Driver incorrecto");
		}
	}

	public void cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			System.out.println("Fallo al cerrar");
		}
	}
}
