import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JComponent;


public class Menu extends javax.swing.JFrame {
 
	private static final long serialVersionUID = 1L;

    private javax.swing.JLabel labelTitle = new javax.swing.JLabel();;
	private javax.swing.JButton botonABMConvenio = new javax.swing.JButton();;
	private javax.swing.JButton botonABMConsulta = new javax.swing.JButton();;
	private javax.swing.JButton botonSalir = new javax.swing.JButton();;
	private javax.swing.JButton botonABMObraSocial = new javax.swing.JButton();;

	public Menu() {
        initComponents();
    }

    private void initComponents() {        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelTitle.setFont(new java.awt.Font("Ubuntu", 0, 32)); // NOI18N
        labelTitle.setText("Seleccionar una opción");

        botonSalir.setText("Salir");
        botonSalir.setName("Salir");
        botonABMConvenio.setText("Administrar Convenios");
        botonABMConvenio.setName("ABMConvenio");
        botonABMConsulta.setText("Administrar Consultas");
        botonABMConsulta.setName("ABMConsulta");
        
        botonABMObraSocial.setText("Administrar Obras sociales");
        botonABMObraSocial.setName("ABMObraSocial");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(labelTitle)
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                .addContainerGap(149, Short.MAX_VALUE)
                .addComponent(botonABMConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(botonABMConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(botonABMObraSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonABMConvenio)
                .addComponent(botonABMConsulta)
                .addComponent(botonABMObraSocial)
                .addComponent(botonSalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
    
	public void addActionListener(ActionListener lis){
		botonABMConvenio.addActionListener(lis);
		botonABMConsulta.addActionListener(lis);
		botonABMObraSocial.addActionListener(lis);
	}

}