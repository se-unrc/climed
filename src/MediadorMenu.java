import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ABMConsultaMedica.MediadorConsulta;
import ABMConvenio.MediadorABMConvenio;


public class MediadorMenu  implements ActionListener {

	private Menu gui = new Menu();

	public MediadorMenu(Principal guiP){
		gui.addActionListener(this);
		gui.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton)
			try {
				ListenerEvent(((JButton) obj).getName());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}

	private void ListenerEvent(String event) throws Exception{
		if(event.compareTo("ABMConvenio")==0){
			new MediadorABMConvenio(gui);
		} else {
			if (event.compareTo("ABMConsulta")==0){
				new MediadorConsulta();
			}
		}
	}
}
