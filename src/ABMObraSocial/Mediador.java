import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Mediador implements ActionListener{

	private Cartel gui = new Cartel();
	private Mediador_CartelAltas med_altas;
	private Mediador_CartelBajas med_bajas;
	private Mediador_CartelModif med_modif;

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
			System.out.println("Bajaaaaa");
			med_bajas = new Mediador_CartelBajas();
		} else if(event.compareTo("ALTA")==0){
			System.out.println("Altaaaaa");
			med_altas = new Mediador_CartelAltas();
		} else if(event.compareTo("MODIFICACION")==0){
			System.out.println("Modificaciooooon");
			med_modif = new Mediador_CartelModif();
		} else if(event.compareTo("Salir")==0){
			System.out.println("Cerrandooooo");
			System.exit(0);
		}
	}	



}
