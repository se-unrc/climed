/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Propio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author mane
 */
public class MediadorBaja implements ActionListener{
    
     private GUIBaja gui;
     private ControlHorario control = new ControlHorario();
    
    public MediadorBaja(){
        gui = new GUIBaja();
        gui.setActionListeners(this);
        gui.setEnabled(true);
        gui.setVisible(true);
        gui.show();
    }
    
    private boolean datosValidos(){
    	try {
            Integer.parseInt(gui.getjTextFieldIdEnfermera().getText().trim());
    	} catch (Exception ex){
            JOptionPane.showMessageDialog(new JFrame(),"Por favor verifique que el ID de la enfermera sea un valor entero o no este vacio.","Error", JOptionPane.ERROR_MESSAGE);
            gui.getjTextFieldIdEnfermera().setText("");
            gui.getjTextFieldIdEnfermera().requestFocus();
            return false;
    	}
    	return true;
    }
    
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == gui.getjButtonOK()){
            eliminar();
            gui.dispose();
        } else if (event.getSource() == gui.getjButtonCancelar()){
            gui.dispose();
        }
     }
    
    public void eliminar(){
    	if (datosValidos()){
    		String a = "";//Se utiliza para crear el objeto Horario
    		int b = Integer.parseInt(gui.getjTextFieldIdEnfermera().getText().trim());
    		int c = 1;//Se utiliza para crear el objeto Horario
    		Horario h = new Horario(a,b,c);
    		control.baja(h);
    	}
    }
    
}
