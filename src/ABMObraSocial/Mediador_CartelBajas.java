import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Mediador_CartelBajas implements ActionListener{

	private CartelBajas gui = new CartelBajas();


	public Mediador_CartelBajas(){
		gui.addActionListener(this);
		gui.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton)
		  ListenerEvent(((JButton) obj).getName());
	}


	private void ListenerEvent(String event){
		if(event.compareTo("Borrar")==0){
			System.out.println("Borrandooooo " + gui.getComboBoxValueSelected());
			gui.dispose();
		} else if(event.compareTo("Cancelar")==0){
			System.out.println("Cerrandooooo Bajas");
			gui.dispose();
		}
	}	
}
