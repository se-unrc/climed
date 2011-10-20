import java.awt.event.ActionEvent;

import javax.swing.JButton;


public class Mediador_Secundario {

	//private Principal gui = new Principal();

	
	public Mediador_Secundario(){
		//gui.addActionListener(this);
		//gui.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton)
		  ListenerEvent(((JButton) obj).getName());
	}
	
	
	private void ListenerEvent(String event){
		if(event.compareTo("Ingresar")==0){
			System.out.println("Holaaaaa");
		}
	}
}
