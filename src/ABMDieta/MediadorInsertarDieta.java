/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ABMDieta;

import java.sql.SQLException;

/**
 *
 * @author Giuliodori Eugenia
 * @author Sung Pei Jung
 * @author Torello Elina
 */
public class MediadorInsertarDieta {
    
    /*Muestra la ventana del GUIAgregarDieta*/
    public void mostrarGUIInsertarDieta(GuiAgregarDieta guiAgregarDieta) {
        guiAgregarDieta.setVisible(true);
    }

/*Agrega una dieta (invoca a ABMDieta)*/
public void agregar(GuiAgregarDieta guiAgregarDieta) throws SQLException {
    String codigo = (guiAgregarDieta.getJTextField1()).getText();
    String nomDesayuno = (guiAgregarDieta.getJTextField2()).getText();
    String nomAlmuerzo = (guiAgregarDieta.getJTextField3()).getText();
    String nomMerienda = (guiAgregarDieta.getJTextField4()).getText();
    String nomCena = (guiAgregarDieta.getJTextField5()).getText();
    int nroCodigo = Integer.parseInt(codigo);
    ControladorDieta abmDieta = new ControladorDieta();
    abmDieta.altaDieta(nroCodigo,nomDesayuno,nomAlmuerzo,nomMerienda,nomCena);
      
}


}







