package ABMEspecialidad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MediadorMatricula implements ActionListener{


	private PrincipalABM interfaz = new PrincipalABM("Matricula");

	public MediadorMatricula(){
		interfaz.addActionListener(this);
		interfaz.setVisible(true);
		interfaz.setResizable(false);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton)
		  ListenerEvent(((JButton) obj).getName());
	}

	private void ListenerEvent(String event){
		if(event.compareTo("Alta")==0){
			AltaMatricula aplicacion = new AltaMatricula();
		}
        if(event.compareTo("Baja")==0){
			BajaMatricula aplicacion = new BajaMatricula();
		}
        if(event.compareTo("Modificacion")==0){
            ModifMatricula aplicacion = new ModifMatricula();
		}
	}
}
