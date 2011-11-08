package ABMEspecialidad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MediadorEspecialidad implements ActionListener{

	private PrincipalABM interfaz = new PrincipalABM("Especialidad");

	public MediadorEspecialidad(){
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
			AltaEspecialidad aplicacion = new AltaEspecialidad();
		}
        if(event.compareTo("Baja")==0){
			BajaEspecialidad aplicacion = new BajaEspecialidad();
		}
        if(event.compareTo("Modificacion")==0){
			ModifEspecialidad aplicacion = new ModifEspecialidad();
		}
	}
}
