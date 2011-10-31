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
public class MediadorEliminarDieta {

    /*Muestra la ventana del GUIEliminarDieta*/
    public void mostrarGUIEliminarDieta(GuiEliminarDieta guiEliminarDieta) {
        guiEliminarDieta.setVisible(true);
    }

/*Elimina una dieta segun el codigo ingresado (invoca a ABMDieta)*/
public void eliminar(GuiEliminarDieta guiEliminarDieta) throws SQLException {
    ControladorDieta abmDieta = new ControladorDieta();
    String codigo = (guiEliminarDieta.getJTextField1()).getText();
    int nroCodigo = Integer.parseInt(codigo);
    abmDieta.bajaDieta(nroCodigo);
}


}
