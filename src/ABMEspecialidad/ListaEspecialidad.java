/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ABMEspecialidad;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class ListaEspecialidad extends JFrame{
	public Conexion.ControlConexion conexion = new Conexion.ControlConexion();
	public ListaEspecialidad(Container contenedor){
        //Container cp = getContentPane();        
        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabla = new JTable (modelo);
        tabla.setRowHeight (24);
        modelo.addColumn("Nombre Especialidad");
        modelo.addColumn("ID.Especialidad");
        ResultSet resultset = conexion.ejecutarSentencia("SELECT * FROM climed.especialidad ORDER BY id_especialidad");
        try {
	        while (resultset.next()){
	            // Se crea un array que será una de las filas de la tabla.
	            Object [] fila = new Object[2]; // Hay tres columnas en la tabla
	            // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
	            for (int i=0;i<2;i++)
	                fila[i] = resultset.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.
	            // Se añade al modelo la fila completa.
	            modelo.addRow(fila);
	        } 
        } catch (Exception ex){
	           ex.printStackTrace();        	
        }       
        JScrollPane jsp = new JScrollPane(tabla);
        contenedor.add (jsp, BorderLayout.CENTER);
    }
}
