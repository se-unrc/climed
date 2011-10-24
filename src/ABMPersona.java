import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ABMPersona{
	//private static Conexion conexion;
	
	public static void ABM(String querry) throws SQLException{
		Connection instanciaConexion;
		instanciaConexion = Conexion.getInstancia();
		Statement estado = instanciaConexion.createStatement();
		estado.executeUpdate(querry);
		Conexion.cerrarConexion();
	}
	
	public static Persona levantarBaseDatos(){
		Persona tupla = new Persona();
		return tupla;
	}
}
