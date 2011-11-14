package ABMEspecialidad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MediadorPrincipalEspecialidad implements ActionListener{

	private Principal interfaz = new Principal();

	public MediadorPrincipalEspecialidad(){
		interfaz.addActionListener(this);
		interfaz.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton)
		  ListenerEvent(((JButton) obj).getName());
	}

	private void ListenerEvent(String event){
		if(event.compareTo("Matricula")==0){
			MediadorMatricula aplicacion1 = new MediadorMatricula();
		}
        if(event.compareTo("Especialidad")==0){
            MediadorEspecialidad aplicacion2 = new MediadorEspecialidad();
		}
	}
}
