package ABMConsultaMedica;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Conexion.Conexion;
import Dominio.ConsultaMedica;

public class ControlConsultaMedica {

	private Connection c = null;
	private ConsultaMedica cm;
	private static Statement instruccion;
	private static ResultSet tabla;
	private String nomTabla;
	private int cantCol;

	public ControlConsultaMedica(String nomTabla, int cantCol, ConsultaMedica cm) throws SQLException {
		this.cm = cm;
		this.nomTabla = nomTabla;
		this.cantCol = cantCol;
		Connection c = Conexion.getInstancia();
		instruccion = c.createStatement();
	}

	public void insertarConsulta() throws SQLException {
		try {
			String comando = "INSERT INTO " + nomTabla + " (idMedico,idPaciente,fecha,diagnostico) VALUES (" + cm.getIdMedico() + "," + cm.getIdPaciente() + ",'" + cm.getFecha() + "','" + cm.getDiagnostico() + "')";
			instruccion.executeUpdate(comando);
		} catch (SQLException e) {
			System.out.println("Ocurrió una excepción al insertar.\n" + e + "\n No se insertó en la tabla " + nomTabla);
			e.printStackTrace();
		}
	}

	public void eliminarConsulta() {
		try {
			String comando = "DELETE FROM consulta WHERE idConsulta = " + cm.getIdConsulta();
			instruccion.executeUpdate(comando);
		} catch (SQLException e) {
			System.out.println("Ocurrió una excepción al eliminar.\n" + e + "\n No se eliminó en la tabla " + nomTabla);
		}
	}

	public void modificarConsulta() {
		try {
			String comando = "DELETE FROM consulta WHERE idConsulta = " + cm.getIdConsulta();
			instruccion.executeUpdate(comando);
			comando = "INSERT INTO " + nomTabla + " (idMedico,idPaciente,fecha,diagnostico) VALUES (" + cm.getIdMedico() + "," + cm.getIdPaciente() + ",'" + cm.getFecha() + "','" + cm.getDiagnostico() + "')";
			instruccion.executeUpdate(comando);
		} 
		catch (SQLException e) {
			System.out.println("Ocurrió una excepción al modificar.\n" + e + "\n No se modificó la tabla " + nomTabla);
			e.printStackTrace();
		}
	}

	public Vector buscarConsulta(int idPaciente) throws SQLException {
		try {
			String consulta = "SELECT * FROM consulta WHERE idPaciente=" + cm.getIdPaciente();
			tabla = instruccion.executeQuery(consulta);
		} 
		catch (SQLException e) {
			System.out.println("Ocurrió una excepción al buscar.\n" + e);
			e.printStackTrace();
		}

		//---------------------------------------------------------//
		//Inicializacion de atributos para armar el vector de salida
		Vector vectorResultado = new Vector();
		int indVector = 0;
		int indAux;
		//----------------------------------------------------------------//
		//Ciclo para armar el vector de salida, (tratamiento de la consulta)
		while (tabla.next()) {
			String[] arr = new String[cantCol];
			indAux=0;
			while (indAux<cantCol){
				arr[indAux]=tabla.getString(indAux+1);		
				indAux++;
			}
			vectorResultado.add(indVector, arr);
			indVector++;
		}

		return(vectorResultado);
	}
	
	public String diagnostico(int id) {
		String diag = "";
		try {
			String consulta =("SELECT diagnostico FROM consulta WHERE idPaciente=" + id);
			tabla = instruccion.executeQuery(consulta);
			System.out.println(tabla.getString(0));
			diag= tabla.getString("diagnostico");

		} 
		catch (SQLException e) {
			System.out.println("Ocurrió una excepción al buscar.\n" + e);
			e.printStackTrace();
		}
		return diag;
	}

	public boolean existe(String valor,String nomColum,String nomTabla) throws SQLException {
		try {
			String consulta = "SELECT * FROM "+nomTabla +" WHERE "+nomColum+" = '"+valor+"';";
			tabla = instruccion.executeQuery(consulta);
		}
		catch (SQLException e) {
			System.out.println("Ocurrió una excepción al verificar existencia de dato.\n" + e);
		}
		return(tabla.next());
	}

	public void setConsultaMedica(ConsultaMedica cm) {
		this.cm = cm;
	}

}
