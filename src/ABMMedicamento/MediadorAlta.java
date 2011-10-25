package ABMMedicamento;

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
public class MediadorAlta implements ActionListener{

    private Medicamento actual;
    private GUIAMMedicamentos gui;
    private ControlMedicamentos control = new ControlMedicamentos();
    private MediadorMedicamentos padre = null;
    private SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");

    public MediadorAlta(MediadorMedicamentos p){
        this.padre = p;
        padre.getGui().setEnabled(false);
        gui = new GUIAMMedicamentos();
        gui.setActionListeners(this);
        gui.setLocationRelativeTo(null);
        gui.setEnabled(true);
        gui.setVisible(true);
        gui.show();
//        Calendar calendario = GregorianCalendar.getInstance();
//        Date fecha = calendario.getTime();
//        System.out.println(fecha);
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

    private void insertar(){
        if (datosValidos()){
            Medicamento m = new Medicamento();
            m.setCodigo(Integer.parseInt(gui.getjTextFieldCodigo().getText().trim()));
            m.setNombre(gui.getjTextFieldNombre().getText().trim());
            m.setStock(Double.parseDouble(gui.getjSpinnerStock().getValue().toString()));
            m.setStock_min(Double.parseDouble(gui.getjSpinnerStockMin().getValue().toString()));
            m.setUnidad(gui.getjTextFieldUnidad().getText().trim());
            Calendar calendario = GregorianCalendar.getInstance();
            Date fecha = calendario.getTime();
            try {
                fecha = formatoDeFecha.parse(gui.getjFormattedTextFieldVencimiento().getText().trim());
                m.setVencimiento(fecha);
                control.insertar(m);
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
            insertar();
        }
    }
}
