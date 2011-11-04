/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Propio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author mane
 */
public class MediadorPrincipal implements ActionListener{
    
    GUI gui;
    ControlHorario control = new ControlHorario();
    javax.swing.JTable tabla;
    DefaultTableModel dtm ;
    
    public MediadorPrincipal(){
        gui = new GUI();
        gui.setActionListeners(this);
        tabla = gui.getJTable();
        gui.setEnabled(true);
        gui.setVisible(true);
        gui.show();
        mostrarRegistros();
    }
    
    public void mostrarRegistros(){
    	Collection<Horario> c = control.obtenerHorarios();
    	String[] headers = {"Turno","Enfermera","Piso"};
        Object[][] data = {};
        dtm = new DefaultTableModel(data, headers);
    	tabla.setModel(dtm);
    	for(Horario h : c){
    		Object[] rowContent = {h.getHorario(),h.getId_enfermera(),h.getPiso()};
    		dtm.addRow(rowContent);
    	}
    }
    
    
    
     public void actionPerformed(ActionEvent event) {
        if (event.getSource() == gui.getjButtonAlta()){
            MediadorAlta alta = new MediadorAlta();
        } else if (event.getSource() == gui.getjButtonBaja()){
            MediadorBaja baja = new MediadorBaja();
        } else if (event.getSource() == gui.getjButtonModificacion()){
            MediadorModificacion modifica = new MediadorModificacion();
        }
     }
}
