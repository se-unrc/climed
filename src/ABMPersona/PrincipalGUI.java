package ABMPersona;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PrincipalGUI extends JFrame {

	private PersonaGUI panel;

	public PrincipalGUI() {
		super();
		initComponents();
	}
	
	private void initComponents() {
		
		// Setup the window		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("ABMPersonas");

		// Create the panel
		panel = new PersonaGUI();
		

		// Set the menu bar and the content pane
		getContentPane().add(panel, BorderLayout.CENTER);
		
		// Display the window
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void addActionListener(ActionListener lis){
		panel.addActionListener(lis);
	}
	
	public PersonaGUI getPanel(){
		return panel;
	}
}
