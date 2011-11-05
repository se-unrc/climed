package ABMPersona;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;

import Conexion.ControlConexion;

public class BajaGUI extends JDialog {
	
	private JLabel etiqueta;
	private JTextField campo;
	private JButton aceptar;
	private JButton cancelar;
	private ControlConexion con;
	private String tabla;

	public BajaGUI() {
		super();
		setName("jDialogBajaPersona");
		
        setLayout(null);
		setSize(210, 100);
		setPreferredSize(new Dimension(210, 100));
		setAlwaysOnTop(true);
		
		tabla = new String();
		con = new ControlConexion();
		
        initComponents();
        
        setResizable(false);
		pack();
		setLocationRelativeTo(null);
		
        setVisible(true);
	}
	
	public void initComponents() {
		etiqueta = new JLabel();
		etiqueta.setText("ID a borrar");
		etiqueta.setBounds(10, 10, 75, 25);
		add(etiqueta);
		
		campo = new JTextField();
		campo.setText("");
		campo.setBounds(100, 10, 100, 25);
		add(campo);
		
		aceptar = new JButton();
		aceptar.setText("Borrar");
		aceptar.setName("Borrar");
		aceptar.setBounds(15, 45, 75, 25);
		add(aceptar);
		
		cancelar = new JButton();
		cancelar.setText("Cancelar");
		cancelar.setName("Cancelar");
		cancelar.setBounds(120, 45, 75, 25);
		add(cancelar);
	}
	
	public String obtenerId() {
		return campo.getText();
	}
	
	public void addActionListener(ActionListener lis){
		aceptar.addActionListener(lis);
		cancelar.addActionListener(lis);
	}
	
	public void cerrar() {
        setVisible(false);
        dispose();
    }
	
}
