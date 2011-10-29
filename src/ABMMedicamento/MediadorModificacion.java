/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ABMMedicamento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Dominio.Medicamento;

/**
 *
 * @author OctiCoCo
 */
public class MediadorModificacion implements ActionListener{

    private Medicamento actual;
    private GUIAMMedicamentos gui;
    private ControlMedicamentos control = new ControlMedicamentos();
    private MediadorMedicamentos padre = null;
    private SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");

    public MediadorModificacion(MediadorMedicamentos medMed, Medicamento med){
        this.padre = medMed;
        padre.getGui().setEnabled(false);
        gui = new GUIAMMedicamentos();
        gui.setActionListeners(this);
        gui.setLocationRelativeTo(null);
        this.actual = med;
        cargarCampos();
        gui.setEnabled(true);
        gui.setVisible(true);
        gui.show();
    }

    private void cargarCampos(){
        gui.getjTextFieldCodigo().setText(actual.getCodigo()+"");
        gui.getjTextFieldCodigo().setEditable(false);
        gui.getjTextFieldNombre().setText(actual.getNombre());
        gui.getjTextFieldUnidad().setText(actual.getUnidad());
        gui.getjSpinnerStock().setValue(actual.getStock());
        gui.getjSpinnerStockMin().setValue(actual.getStock_min());
        //System.out.println(actual.getVencimiento().toLocaleString());
        String dia = (actual.getVencimiento().getDate()<10)?"0"+actual.getVencimiento().getDate():actual.getVencimiento().getDate()+"";
        String mes = ((actual.getVencimiento().getMonth()+1)<10)?"0"+(actual.getVencimiento().getMonth()+1):(actual.getVencimiento().getMonth()+1)+"";
        String fecha = dia+"/"+mes+"/"+(actual.getVencimiento().getYear()+1900);
        gui.getjFormattedTextFieldVencimiento().setText(fecha);
    }
    
    private boolean datosValidos(){
    	try {
            Integer.parseInt(gui.getjTextFieldCodigo().getText().trim());
    	} catch (Exception ex){
            JOptionPane.showMessageDialog(new JFrame(),"Por favor verifique el código del medicamento ingresado, debe ser un valor numerico entero.","Error", JOptionPane.ERROR_MESSAGE);
            gui.getjTextFieldCodigo().setText("");
            gui.getjTextFieldCodigo().requestFocus();
            return false;
    	}
        try {
            formatoDeFecha.parse(gui.getjFormattedTextFieldVencimiento().getText().trim());
        } catch (Exception e){
            JOptionPane.showMessageDialog(new JFrame(),"Por favor verifique el formato de la fecha de vencimiento\nEl mismo debe ser dd/mm/aaaa. Por ejemplo 31/01/2000.","Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    	if (gui.getjTextFieldNombre().getText().trim().equals("")){
            JOptionPane.showMessageDialog(new JFrame(),"Por favor verifique el nombre del medicamento ingresado, el mismo no puede quedar vacio.","Error", JOptionPane.ERROR_MESSAGE);
            gui.getjTextFieldNombre().requestFocus();
            return false;
    	} else if (gui.getjTextFieldUnidad().getText().trim().equals("")){
            JOptionPane.showMessageDialog(new JFrame(),"Por favor verifique la unidad del medicamento ingresado, la misma no puede quedar vacia.","Error", JOptionPane.ERROR_MESSAGE);
            gui.getjTextFieldUnidad().requestFocus();
            return false;
    	}
    	return true;
    }

    private void actualizar(){
        //actual.setCodigo(Integer.parseInt(gui.getjTextFieldCodigo().getText().trim()));
        if (datosValidos()){
            actual.setNombre(gui.getjTextFieldNombre().getText().trim());
            actual.setStock(Double.parseDouble(gui.getjSpinnerStock().getValue().toString()));
            actual.setStock_min(Double.parseDouble(gui.getjSpinnerStockMin().getValue().toString()));
            actual.setUnidad(gui.getjTextFieldUnidad().getText().trim());
            Calendar calendario = GregorianCalendar.getInstance();
            SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = calendario.getTime();
            try {
                fecha = formatoDeFecha.parse(gui.getjFormattedTextFieldVencimiento().getText().trim());
                actual.setVencimiento(fecha);
                control.actualizar(actual);
                if (padre != null) {
                    padre.cargarTabla();
                    padre.getGui().setEnabled(true);
                }
                gui.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == gui.getjButtonCancelar()){
            if (padre != null) padre.getGui().setEnabled(true);
            gui.dispose();
        } else if (event.getSource() == gui.getjButtonGuardar()) {
            actualizar();
        }
    }
}
