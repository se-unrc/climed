import java.sql.*;

public class ControlConexion{
	PreparedStatement stmt = null;
	/*private ConectaBD conexion;
	private String user = "root";
	private String pass = "root";
	private String host = "jdbc:postgresql://localhost:5432/climed_bd";*/
	
	public ControlConexion(){
		
		/*conexion = new ConectaBD(host,user,pass);*/
	
	}

	public void insertar (Horario h) throws SQLException{
		String query= "insert into horario (horario, id_enfermera, piso) values (" + h.getHorario() +"," + h.getId_enfermera() + "," +h.getPiso()+");";
		try{
			stmt = Conexion.getInstancia().prepareStatement(query);
			stmt.executeQuery();
		}catch(SQLException e){
			System.out.println(e);
		}
	}
	
	public void borrar (Horario h) throws SQLException{
		//conexion.doUpdate("delete from Horario where (horario='" + horario +"');");
		String query= "DELETE From horario Where id_enfermera= "+h.id_enfermera+";";
		try{
			stmt = Conexion.getInstancia().prepareStatement(query);
			stmt.executeUpdate();
		}
		catch(SQLException e){
			System.out.println(e);
		}
	}
	//No funciona!
	public void modificar (Horario h, String update) throws SQLException{
		//conexion.doUpdate("update Horario set " + campo + " = '" + nuevoValor + "' where " + campo + "='" + valor+"';");
		try {
            Statement stm = Conexion.getInstancia().createStatement();
            stm.executeUpdate(update);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
	}

	/*public void desconectar(){
	
		conexion.destroy();	
		
	}*/

}
