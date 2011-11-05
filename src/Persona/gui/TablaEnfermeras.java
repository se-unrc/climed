package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Conexion.ControlConexion;

import java.awt.*;

public class TablaEnfermeras extends JTable {
	
	private final String SENT = "SELECT identificador, nombre, apellido, dni, direccion, telefono, categoria FROM persona WHERE ocupacion = 'enfermera';";
	private final String[] TITULOS = {"Identificador", "Nombre", "Apellido", "DNI", "Direccion", "Telefono", "Categoria"};
	
	private ControlConexion con = new ControlConexion();
	
	DefaultTableModel datos = con.ejecutarSentencia(SENT, TITULOS);
	
	public TablaEnfermeras() {
		super();
		
		setAutoCreateRowSorter(true);
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        setModel(datos);
	}

}