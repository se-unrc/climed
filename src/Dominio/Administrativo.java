package Dominio;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Conexion.ControlConexion;


public class Administrativo extends JTable {

	private final String SENT = "SELECT identificador, nombre, apellido, dni, direccion, telefono, horasextras FROM persona WHERE ocupacion = 'administrativo' AND borrado = 'f';";
	private final String[] TITULOS = {"Identificador", "Nombre", "Apellido", "DNI", "Direccion", "Telefono", "HorasExtras"};
	
	private ControlConexion con = new ControlConexion();
	
	DefaultTableModel datos = con.ejecutarSentencia(SENT, TITULOS);
	
	public Administrativo() {
		super();

		setAutoCreateRowSorter(true);
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        setModel(datos);
        
	}

}