import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Mediador_CartelModif implements ActionListener{

	private CartelModif gui = new CartelModif();


	public Mediador_CartelModif(){
		gui.addActionListener(this);
		gui.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton)
		  ListenerEvent(((JButton) obj).getName());
	}


	private void ListenerEvent(String event){
		if(event.compareTo("Modificar")==0){
			System.out.println("Modifcandooooo");
		} else if(event.compareTo("Cancelar")==0){
			System.out.println("Cerrandooooo Modificaciones");
			gui.dispose();
		}
	}	
}