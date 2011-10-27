import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Mediador implements ActionListener{

	private Cartel gui = new Cartel();
	private MediadorCartelAltas med_altas;
	private MediadorCartelBajas med_bajas;
	private MediadorCartelModif med_modif;

	public Mediador(){
		gui.addActionListener(this);
		gui.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton)
		  ListenerEvent(((JButton) obj).getName());
	}


	private void ListenerEvent(String event){
		if(event.compareTo("BAJA")==0){
			med_bajas = new MediadorCartelBajas();
		} else if(event.compareTo("ALTA")==0){
			med_altas = new MediadorCartelAltas();
		} else if(event.compareTo("MODIFICACION")==0){
			med_modif = new MediadorCartelModif();
		} else if(event.compareTo("Salir")==0){
			System.exit(0);
		}
	}	



}
