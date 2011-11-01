package ABMObraSocial;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Conexion.Conexion;

public class ControlObraSocial {
	private Connection con;
	private Statement st;
	
	public ControlObraSocial(){
		con = Conexion.getInstancia();
		try{
			st = con.createStatement();
		} catch (Exception e){
			System.out.println(e);
		}
	}
	
	public void insertar(int num, String nomb, String dir, String tel, String cuit) throws SQLException{
		String query = "INSERT INTO obrasocial VALUES('"+num+"','"+nomb+"','"+dir+"','"+tel+"','"+cuit+"')";
		st.executeUpdate(query);
	}
	
	public LinkedList listar(){
		LinkedList nombres = new LinkedList();
		try{
			String query = "SELECT * from obrasocial";
			ResultSet result = st.executeQuery(query);
			while (result.next()){
				nombres.add(result.getString(2));
			}
		} catch (SQLException e){
			System.out.println(e);
		}
		return nombres;
	}
	
	public void eliminar(String nombre){
		try{
			String query = "DELETE FROM obrasocial where nombre='"+nombre+"'";
			st.executeUpdate(query);
		} catch (SQLException e){
			System.out.println(e);
		}
	}
	
	public String[] seleccionar(String nombre){
		ResultSet result;
		try{
			String query = "SELECT * FROM obrasocial where nombre='"+nombre+"'";
			result = st.executeQuery(query);
			result.next();
			return (new String[] {result.getString(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5)});
		} catch (SQLException e){
			System.out.println(e);
		}
		return new String[5];
	}
	
	public void modificar(int num, String nomb, String dir, String tel, String cuit) throws SQLException{
		String query = "UPDATE obrasocial SET id = '"+num+"',nombre = '"+nomb+"',direccion = '"+dir+"',telefono = '"+tel+"',cuit = '"+cuit+"' WHERE nombre = '"+nomb+"'";
		st.executeUpdate(query);
	}
}
