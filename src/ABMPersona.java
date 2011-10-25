import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ABMPersona{
	//private static Conexion conexion;
	
	public static void ABM(String querry) throws SQLException{
		Connection instanciaConexion;
		instanciaConexion = Conexion.getInstancia();
		Statement estado = instanciaConexion.createStatement();
		estado.executeUpdate(querry);
		Conexion.cerrarConexion();
	}
	
	public static String armarQuerry(String comando,String tabla, String[] values, String[] where){
		String retorno = "";
		comando = comando.toLowerCase();
		if (comando.equals("insert")){
			retorno += "INSERT INTO " + tabla + " VALUES (";
			for (int i =0; i<values.length;i++){
				retorno += values[i];
				if (i<values.length-1){
					retorno += ", ";
				}
			}
			retorno += ")";
		}else if(comando.equals("delete")){
			retorno = "Delete from " + tabla + " WHERE ";
			for (int i = 0; i< where.length ; i++ ){
				retorno += "(" + where[i] + ")" ;
				if (i<where.length-1){
					retorno += " and ";
				}
			}
			retorno += ")";
		}else if (comando.equals("update")){
			retorno = "UPDATE " + tabla + " SET ";
			for (int i = 0; i<values.length; i++){
				retorno += values[i];
				if (i<values.length-1){
					retorno +=" , ";
				}
			}
			retorno += " WHERE ";
			for (int i = 0; i<where.length;i++){
				retorno += " (" + values[i]+ " ) ";
				if (i<where.length-1){
					retorno += " AND ";
				}
			}
			retorno += ")";
		}else{
			System.out.println("error comando incorrecto");
			return "";
		}
		return retorno;
	}
}
