package medicamentos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author grupo1
 */
public class MedAMMedicamentos implements ActionListener{

    private Medicamento actual;
    private GUIAMMedicamentos gui;
    private ControlMedicamentos control = new ControlMedicamentos();
    private MedMedicamentos padre = null;
    private boolean insertar = true;
    private SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");

    public MedAMMedicamentos(MedMedicamentos p){
        this.padre = p;
        padre.getGui().setEnabled(false);
        gui = new GUIAMMedicamentos();
        gui.setActionListeners(this);
        gui.setLocationRelativeTo(null);
        gui.setEnabled(true);
        gui.setVisible(true);
        gui.show();
        this.insertar = true;
//        Calendar calendario = GregorianCalendar.getInstance();
//        Date fecha = calendario.getTime();
//        System.out.println(fecha);
    }

    public MedAMMedicamentos(MedMedicamentos medMed, Medicamento med, boolean vista){
        this.padre = medMed;
        padre.getGui().setEnabled(false);
        gui = new GUIAMMedicamentos();
        gui.setActionListeners(this);
        gui.setLocationRelativeTo(null);
        this.actual = med;
        this.insertar = false;
        if (vista){ // es una vista
            gui.getjButtonGuardar().setVisible(false);
            gui.getjButtonCancelar().setText("Cerrar");
            gui.getjTextFieldNombre().setEditable(false);
            gui.getjTextFieldUnidad().setEditable(false);
            gui.getjSpinnerStock().setEnabled(false);
            gui.getjSpinnerStockMin().setEnabled(false);
            gui.getjFormattedTextFieldVencimiento().setEditable(false);
        }
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

    private void insertar(){

        Medicamento m = new Medicamento();       
        try {
        	if (datosValidos()){
        		m.setCodigo(Integer.parseInt(gui.getjTextFieldCodigo().getText().trim()));
                m.setNombre(gui.getjTextFieldNombre().getText().trim());
                m.setStock(Double.parseDouble(gui.getjSpinnerStock().getValue().toString()));
                m.setStock_min(Double.parseDouble(gui.getjSpinnerStockMin().getValue().toString()));
                m.setUnidad(gui.getjTextFieldUnidad().getText().trim());
                Calendar calendario = GregorianCalendar.getInstance();
                Date fecha = calendario.getTime();
            	fecha = formatoDeFecha.parse(gui.getjFormattedTextFieldVencimiento().getText().trim());
                m.setVencimiento(fecha);
                if (control.exist(m)){
                	JOptionPane.showMessageDialog(new JFrame(),"Por favor verifique el código del medicamento ingresado, el mismo ya existe.","Error", JOptionPane.ERROR_MESSAGE);
                } else {
                	control.insertar(m);
                    if (padre != null) {
                        padre.cargarTabla();
                        padre.getGui().setEnabled(true);
                    }
                    gui.dispose();
                }
        	}
        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(),"Por favor verifique el formato de la fecha de vencimiento\nEl mismo debe ser dd/mm/aaaa. Por ejemplo 31/01/2000.","Error", JOptionPane.ERROR_MESSAGE);
        }       
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
            //e.printStackTrace();
            JOptionPane.showMessageDialog(new JFrame(),"Por favor verifique el formato de la fecha de vencimiento\nEl mismo debe ser dd/mm/aaaa. Por ejemplo 31/01/2000.","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == gui.getjButtonCancelar()){
            if (padre != null) padre.getGui().setEnabled(true);
            gui.dispose();
        } else if (event.getSource() == gui.getjButtonGuardar()) {
            // ver si es insersion o modificacion
            if (insertar) insertar();
            else actualizar();
        }
    }
}
