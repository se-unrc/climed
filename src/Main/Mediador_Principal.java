package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import MenuPrincipal.MediadorMenu;

import javax.swing.JButton;

public class Mediador_Principal implements ActionListener{
	private Principal gui = new Principal();

	public Mediador_Principal(){
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
			new MediadorMenu(gui);
		}
	}
}
