import java.sql.*;

public class ControlHorario{
	
	private Horario h;
	private ControlConexion gestor = new ControlConexion();
	
	public ControlHorario(Horario h) {		
		this.h= h;
	}
			
	public void alta(){
		try{
			gestor.insertar(h);
		}catch(SQLException e){System.out.println(e);}
	}

	public void baja(){
		try{
			gestor.borrar(h);
		}catch(SQLException e){System.out.println(e);}
	}

	/*public void modificar(int campo, int nuevoValor){
		try{
			if (campo==id_enfermera){			
				gestor.modificar(id_enfermera, campo, nuevoValor);
			}else{
				gestor.modificar(horario, campo, nuevoValor);
			}
		}catch(SQLException e){System.out.println(e);}
	}*/
}
