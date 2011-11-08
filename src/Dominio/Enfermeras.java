package Dominio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Conexion.ControlConexion;

import java.awt.*;

public class Enfermeras extends JTable {
	
	private final String SENT = "SELECT identificador, nombre, apellido, dni, direccion, telefono, categoria FROM persona WHERE ocupacion = 'enfermera';";
	private final String[] TITULOS = {"Identificador", "Nombre", "Apellido", "DNI", "Direccion", "Telefono", "Categoria"};
	
	private ControlConexion con = new ControlConexion();
	
	DefaultTableModel datos = con.ejecutarSentencia(SENT, TITULOS);
	
	public Enfermeras() {
		super();
		
		setAutoCreateRowSorter(true);
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        setModel(datos);
	}

}