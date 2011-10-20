import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Mediador_CartelAltas implements ActionListener{

	private CartelAltas gui = new CartelAltas();


	public Mediador_CartelAltas(){
		gui.addActionListener(this);
		gui.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton)
		  ListenerEvent(((JButton) obj).getName());
	}


	private void ListenerEvent(String event){
		if(event.compareTo("Agregar")==0){
			System.out.println("Agregadooooo");
		} else if(event.compareTo("Cancelar")==0){
			System.out.println("Cerrandooooo Altas");
			gui.dispose();
		}
	}	
}