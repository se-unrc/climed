/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ABMDieta;

/**
 *
 * @author Giuliodori Eugenia
 * @author Sung Pei Jung
 * @author Torello Elina
 */

public class MediadorMenuPrincipal {
    
    GuiMenuPrincipal guiMenuPrincipal = new GuiMenuPrincipal();
    
    public MediadorMenuPrincipal(){
    	mostrarGuiMenuPrincipal();
    }
    
    public void mostrarGuiMenuPrincipal() {
        guiMenuPrincipal.setVisible(true);
    }

    public void cerrarGuiMenuPrincipal() {
        guiMenuPrincipal.dispose();
    }

public void insertarDieta() {
        GuiAgregarDieta guiAgregarDieta = new GuiAgregarDieta(new javax.swing.JFrame(), true);
        MediadorInsertarDieta mediadorInsertarDieta = new MediadorInsertarDieta();
        mediadorInsertarDieta.mostrarGUIInsertarDieta(guiAgregarDieta);
        cerrarGuiMenuPrincipal();
        mostrarGuiMenuPrincipal();
}

public void eliminarDieta() {
    GuiEliminarDieta guiEliminarDieta = new GuiEliminarDieta(new javax.swing.JFrame(), true);
    MediadorEliminarDieta mediadorEliminarDieta=new MediadorEliminarDieta();
    mediadorEliminarDieta.mostrarGUIEliminarDieta(guiEliminarDieta);
    cerrarGuiMenuPrincipal();
    mostrarGuiMenuPrincipal();
}

public void modificarDieta() {
    GuiModificarDieta guiModificarDieta = new GuiModificarDieta(new javax.swing.JFrame(), true);
    MediadorModificarDieta mediadorModificarDieta=new MediadorModificarDieta();
    mediadorModificarDieta.mostrarGUIModificarDieta(guiModificarDieta);
    cerrarGuiMenuPrincipal();
    mostrarGuiMenuPrincipal();
}




}
