package Dominio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Conexion.ControlConexion;

import java.awt.*;

public class Medicos extends JTable {
	
	private final String SENT = "SELECT identificador, nombre, apellido, dni, direccion, telefono, especialidad FROM persona WHERE ocupacion = 'medico';";
	private final String[] TITULOS = {"Identificador", "Nombre", "Apellido", "DNI", "Direccion", "Telefono", "Especialidad"};
	
	private ControlConexion con = new ControlConexion();
	
	DefaultTableModel datos = con.ejecutarSentencia(SENT, TITULOS);
	
	public Medicos() {
		super();
		
		setAutoCreateRowSorter(true);
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        setModel(datos);
        
	}

}