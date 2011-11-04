package ABMHorario;

import java.sql.*;
import java.util.Collection;

import Dominio.Horario;

public class ControlHorario{
	
	private Horario h;
	private ControlConexion gestor = new ControlConexion();
	
	public ControlHorario(){
	}
	
	public ControlHorario(Horario h) {		
		this.h= h;
	}
			
	public void alta(Horario hor){
		try{
			gestor.insertar(hor);
		}catch(Exception e){System.out.println(e);}
	}

	public void baja(Horario id){
		try{
			gestor.borrar(id);
		}catch(SQLException e){System.out.println(e);}
	}

	public void modificar(Horario h){
		try{			
				gestor.modificar(h);

		}catch(SQLException e){System.out.println(e);}
	}
	
	public Collection<Horario> obtenerHorarios(){
		return gestor.obtenerHorarios();
	}
}
