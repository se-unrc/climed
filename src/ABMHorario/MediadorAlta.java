/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ABMHorario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Dominio.Horario;

/**
 *
 * @author mane
 */
public class MediadorAlta implements ActionListener{
 
    private GUIAltaModificacion gui;
    private ControlHorario control = new ControlHorario();
    
    public MediadorAlta(){
        gui = new GUIAltaModificacion();
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
        try {
        	Integer.parseInt(gui.getjTextFieldPiso().getText().trim());
        } catch (Exception e){
        	JOptionPane.showMessageDialog(new JFrame(),"Por favor verifique que el piso sea un valor entero o no este vacio.","Error", JOptionPane.ERROR_MESSAGE);
            gui.getjTextFieldPiso().setText("");
            gui.getjTextFieldPiso().requestFocus();
            return false;
        }
    	return true;
    }
    
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == gui.getjButtonOK()){
            insertar();
            gui.dispose();
        } else if (event.getSource() == gui.getjButtonCancelar()){
            gui.dispose();
        }
     }
    
    public void insertar(){
    	if (datosValidos()){
    		String a = gui.getJComboBoxHorario().getSelectedItem().toString();
    		int b = Integer.parseInt(gui.getjTextFieldIdEnfermera().getText().trim());
    		int c = Integer.parseInt(gui.getjTextFieldPiso().getText().trim());
    		Horario h = new Horario(a,b,c);
    		control.alta(h);
    	}
    }
    


}