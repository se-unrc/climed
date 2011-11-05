/**
 * 
 */
package principal;

import gui.VentanaPrincipalGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * @author vientosdepoder
 *
 */
public class MediadorVentanaPrincipal  implements ActionListener{

	private VentanaPrincipalGUI gui = new VentanaPrincipalGUI();

	public MediadorVentanaPrincipal(){
		gui.addActionListener(this);
		gui.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton)
			ListenerEvent(((JButton) obj).getName());
	}


	private void ListenerEvent(String event){
		if(event.compareTo("Ingresar")==0){
			System.out.println("Holaaaaa");
			//System.exit(0);
		}
	}

}
