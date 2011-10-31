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
public class MediadorModificarDieta {
    
    public void mostrarGUIModificarDieta(GuiModificarDieta guiModificarDieta) {
        guiModificarDieta.setVisible(true);
    }

/*Modifica una dieta (invoca a ABMDieta)*/
public void modificar(GuiModificarDieta guiModificarDieta) throws SQLException {//seria el aceptar
    ControladorDieta abmDieta = new ControladorDieta();
    String codigo = (guiModificarDieta.getJTextField1()).getText();
    String nomDesayuno = (guiModificarDieta.getJTextField2()).getText();
    String nomAlmuerzo = (guiModificarDieta.getJTextField3()).getText();
    String nomMerienda = (guiModificarDieta.getJTextField4()).getText();
    String nomCena = (guiModificarDieta.getJTextField5()).getText();
    int nroCodigo = Integer.parseInt(codigo);
    abmDieta.modificarDieta(nroCodigo,nomDesayuno,nomAlmuerzo,nomMerienda,nomCena);
      
}


}











