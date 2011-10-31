/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ABMHabitacion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Conexion.Conexion;

/**
 *
 * @author G4
 */
class ControlHabitacion {


	/**
	 *  
	 * @param nroHabitacion
	 * @param nroPiso
	 * @param fechaInternacion
	 * @throws SQLException
	 * 
	 * Esta clase nos permite cargar un numero de habitacion con su respectivo 
	 * numero de piso y fecha de internacion a la base de datos. 
	 */

	public void cargarHabitacion(int nroHabitacion, int nroPiso,String fechaInternacion) throws SQLException {
		Connection connection = Conexion.getInstancia();
		String npiso = "SELECT * FROM internacion where num_piso='" + nroPiso + "'";
		Statement statement =  connection.createStatement();
		ResultSet resultSetPiso = statement.executeQuery(npiso);
		String fecha = "SELECT * FROM internacion WHERE fecha_internacion='" + fechaInternacion + "'";
		statement = connection.createStatement();
		ResultSet resultSetFecha = statement.executeQuery(fecha);
		boolean esPiso =  resultSetPiso.next();
		boolean esFecha = resultSetFecha.next();
		if ((esPiso)&&(esFecha)){
			String query = "INSERT INTO habitacion values ('"+ nroHabitacion +"','"+nroPiso+"','"+fechaInternacion+"')";
			statement =  connection.createStatement();
			statement.executeUpdate(query);

		}
		
	}   

	/**
	 * 
	 * @param nroHabitacion
	 * @throws SQLException
	 * 
	 * Esta clase nos permite borrar de la base de datos un numero de habitacion, con lo cual 
	 * elimina todas las columnas del mismo. Es decir al eliminar una habitacion tambien lo 
	 * hara con el numero de piso y fecha de internacion. 
	 * 
	 */
	void eliminarHabitacion(int nroHabitacion) throws SQLException {
		Connection connection = Conexion.getInstancia();

		String query = "DELETE FROM habitacion WHERE num_habitacion='"+nroHabitacion+"'";
		Statement statement=connection.createStatement();
		statement.executeUpdate(query);

	}
	
	/**
	 * 
	 * @param nroHabitacion
	 * @param nroPiso
	 * @param fechaInternacion
	 * @throws SQLException
	 * 
	 * Este metodo permite modificar en la base de datos dependiendo del numero de habitacion
	 * la fecha de internacion o el numero de piso.
	 */

	void modificarHabitacion(int nroHabitacion, int nroPiso, String fechaInternacion) throws SQLException {

		Connection connection = Conexion.getInstancia();

		String nHabitacion = "SELECT * FROM habitacion WHERE num_habitacion='"+nroHabitacion+"'";
		Statement statement =  connection.createStatement();
		ResultSet resultSetHabitacion = statement.executeQuery(nHabitacion);

		boolean encontroHabitacion = resultSetHabitacion.next();
		String piso = "SELECT * FROM internacion where num_piso='"+nroPiso+"'";
		
		ResultSet resultSetPiso = statement.executeQuery(piso);
		boolean encontroPiso = resultSetPiso.next();
		String fecha = "SELECT * FROM internacion where fecha_internacion='"+fechaInternacion+"'";


		
		ResultSet resultSetFecha = statement.executeQuery(fecha);
		boolean encontroFecha = resultSetFecha.next();

		if ((encontroHabitacion)&&(encontroPiso)&&(encontroFecha)){
			
			String query = "UPDATE habitacion SET num_piso='"+nroPiso+"',fecha_internacion='"+fechaInternacion+"' WHERE num_habitacion='"+nroHabitacion+"'";
			
			statement.executeUpdate(query);
		}
	}


}





