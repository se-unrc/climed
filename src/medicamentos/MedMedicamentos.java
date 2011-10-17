package medicamentos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author grupo1
 */
public class MedMedicamentos implements ActionListener, KeyListener {

    private GUIMedicamentos gui;
    private DefaultTableModel original;
    private JTable tabla;
    private ControlMedicamentos control = new ControlMedicamentos();

    public MedMedicamentos(){
        gui = new GUIMedicamentos();
        gui.setActionListeners(this);
        gui.setKeyListeners(this);
        tabla = gui.getjTable();
        cargarTabla();
        gui.setLocationRelativeTo(null);
        gui.setEnabled(true);
        gui.show();
    }

    public void cargarTabla(){
        String[] headers = {"Código","Nombre","Stock","Stock Minimo"};
        Object[][] data = {};
        original = new DefaultTableModel(data, headers) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }
        };
        tabla.setModel(original);
        Vector<Medicamento> medicamentos = control.getAll();
        for (int i = 0; i < medicamentos.size(); i++) {
            Object[] rowContent = {((Medicamento)medicamentos.get(i)).getCodigo(),((Medicamento)medicamentos.get(i)).getNombre(),((Medicamento)medicamentos.get(i)).getStock(),((Medicamento)medicamentos.get(i)).getStock_min()};
            original.addRow(rowContent);
        }
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == gui.getjButtonCerrar()){
            gui.dispose();
            //System.exit(0);
        } else if (event.getSource() == gui.getjButtonRestaurar()){
            this.cargarTabla();
            gui.getjTextFieldCodigo().setText("");
            gui.getjTextFieldNombre().setText("");
        } else if (event.getSource() == gui.getjButtonVer()){
            if (gui.getjTable().getSelectedRow() == -1){
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(),"Por favor seleccione el medicamento a visualizar","Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            } else {
                Medicamento med = new Medicamento();
                med = control.getByCode(Integer.parseInt(gui.getjTable().getValueAt(gui.getjTable().getSelectedRow(), 0).toString()));
                new MedAMMedicamentos(this,med,true);
            }
        } else if (event.getSource() == gui.getjButtonAgregar()){
            new MedAMMedicamentos(this);
        } else if (event.getSource() == gui.getjButtonModificar()){
            if (gui.getjTable().getSelectedRow() == -1){
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(),"Por favor seleccione el medicamento a modificar","Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            } else {
                Medicamento m = new Medicamento();
                m = control.getByCode(Integer.parseInt(gui.getjTable().getValueAt(gui.getjTable().getSelectedRow(), 0).toString()));
                new MedAMMedicamentos(this,m,false);
            }
        } else if (event.getSource() == gui.getjButtonEliminar()){
            if (gui.getjTable().getSelectedRow() == -1){
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(),"Por favor seleccione el medicamento a eliminar","Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            } else {
                int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el medicamento?", "Eliminar medicamento", JOptionPane.YES_NO_OPTION);
                if (resp == 0){
                    Medicamento m = new Medicamento();
                    m = control.getByCode(Integer.parseInt(gui.getjTable().getValueAt(gui.getjTable().getSelectedRow(), 0).toString()));
                    control.deleteItem(m);
                    this.cargarTabla();
                }
            }
        }
    }
    
    public void keyPressed(KeyEvent e) { 
        
    }

    public void keyReleased(KeyEvent e) { 
        if (e.getSource() == gui.getjTextFieldCodigo()){
            String contenido = gui.getjTextFieldCodigo().getText().trim().toUpperCase();
            this.cargarTabla();
            //if (contenido != ""){
                int i = 0;
                while (i < this.original.getRowCount()){
                    String codActual = original.getValueAt(i, 0).toString().toUpperCase();
                    if (!codActual.contains(contenido.toUpperCase())){
                        original.removeRow(i);
                    } else {
                        i++;
                    }
                }
            //}
        } else if (e.getSource() == gui.getjTextFieldNombre()){
            String contenido = gui.getjTextFieldNombre().getText().trim().toUpperCase();
            this.cargarTabla();
            int i = 0;
            while (i < this.original.getRowCount()){
                String codActual = original.getValueAt(i, 1).toString().toUpperCase();
                if (!codActual.contains(contenido.toUpperCase())){
                    original.removeRow(i);
                } else {
                    i++;
                }
            }
        }
    }

    public void keyTyped(KeyEvent e) { 
        if (e.getSource() == gui.getjTextFieldCodigo()){ //si se ingresan datos en el campo codigo
            if (!Character.isDigit(e.getKeyChar())){ //si no es numero se ignora
                e.consume();
            }
        }
    }

    public GUIMedicamentos getGui(){
        return gui;
    }
}
