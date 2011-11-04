package Propio;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;

public class ControlConexion{
	PreparedStatement stmt = null;
	
	
	public ControlConexion(){
		
	}

	
	public void insertar (Horario h) throws Exception{
		String query= "insert into horario (horario, id_enfermera, piso) values ('" + h.getHorario() + "'," + h.getId_enfermera() + "," +h.getPiso()+");";
		try{
			stmt = Conexion.getInstancia().prepareStatement(query);
			stmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void borrar (Horario h) throws SQLException{
		String query= "DELETE From horario Where id_enfermera = '"+h.getId_enfermera()+"';"; //
		try{
			stmt = Conexion.getInstancia().prepareStatement(query);
			stmt.executeUpdate();
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public void modificar (Horario h) throws SQLException{
		String query = "UPDATE horario set horario = '"+h.getHorario()+"',piso = "+h.getPiso()+" Where id_enfermera = "+h.getId_enfermera()+";";
		try {
            stmt = (PreparedStatement) Conexion.getInstancia().prepareStatement(query);
            stmt.executeUpdate();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
	}

	
	public Collection<Horario> obtenerHorarios(){
		Collection<Horario> retorno = new HashSet();
		ResultSet temporal;
		int i=0;
		try{
			String query = "Select * From horario";
            Statement sent = Conexion.getInstancia().createStatement();
			temporal = sent.executeQuery(query);
			while(temporal.next()){
				Horario r = new Horario(temporal.getString("horario"),temporal.getInt("id_enfermera"),temporal.getInt("piso"));
				retorno.add(r);
				i++;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return retorno;
	}
	
	
}
