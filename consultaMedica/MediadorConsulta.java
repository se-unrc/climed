/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package consultaMedica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;

/**
 *
 * @author Grupo 6
 */
public class MediadorConsulta implements ActionListener {

	private GuiAbmCM abmCM;

	public MediadorConsulta() {
		abmCM = new GuiAbmCM();
		abmCM.setVisible(true);
		abmCM.setActionListeners(this); 
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj instanceof JButton) {
			ListenerEvent(((JButton) obj).getName());
		}
	}

	private void ListenerEvent(String event) {
		if (event.compareTo("Alta")==0) {
			try {
				MediadorAltaCM alta = new MediadorAltaCM(abmCM);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		else {
			try {
				MediadorBMB mBMB = new MediadorBMB(abmCM);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
