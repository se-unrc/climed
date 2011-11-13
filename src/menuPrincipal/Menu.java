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
	private javax.swing.JButton botonABMDieta = new javax.swing.JButton();;
	private javax.swing.JButton botonABMEspecialidad = new javax.swing.JButton();;
	private javax.swing.JButton botonABMHabitacion = new javax.swing.JButton();;
	private javax.swing.JButton botonABMHorario = new javax.swing.JButton();;
	private javax.swing.JButton botonABMMedicamento = new javax.swing.JButton();;
	private javax.swing.JButton botonABMPersona = new javax.swing.JButton();;

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
        
        botonABMDieta.setText("Administrar Dietas");
        botonABMDieta.setName("ABMDieta");
        
        botonABMEspecialidad.setText("Administrar Especialidades");
        botonABMEspecialidad.setName("ABMEspecialidad");
        
        botonABMHabitacion.setText("Administrar Habtiaciones");
        botonABMHabitacion.setName("ABMHabitacion");
        
        botonABMHorario.setText("Administrar Horarios");
        botonABMHorario.setName("ABMHorario");
        
        botonABMMedicamento.setText("Administrar Medicamentos");
        botonABMMedicamento.setName("ABMMedicamento");
        
        botonABMObraSocial.setText("Administrar Obras sociales");
        botonABMObraSocial.setName("ABMObraSocial");
        
        botonABMPersona.setText("Administrar Personas");
        botonABMPersona.setName("ABMPersona");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(labelTitle)
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createParallelGroup()
                //.addContainerGap(149, Short.MAX_VALUE)
                .addComponent(botonABMConvenio, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(botonABMConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(botonABMDieta, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(botonABMEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(botonABMHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(botonABMHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(botonABMMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(botonABMObraSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(botonABMPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(botonABMDieta)
                .addComponent(botonABMEspecialidad)
                .addComponent(botonABMHabitacion)
                .addComponent(botonABMHorario)
                .addComponent(botonABMMedicamento)
                .addComponent(botonABMObraSocial)
                .addComponent(botonABMPersona)
                .addComponent(botonSalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
    
	public void addActionListener(ActionListener lis){
		botonABMConvenio.addActionListener(lis);
		botonABMConsulta.addActionListener(lis);
		botonABMDieta.addActionListener(lis);
		botonABMEspecialidad.addActionListener(lis);
		botonABMHabitacion.addActionListener(lis);
		botonABMHorario.addActionListener(lis);
		botonABMMedicamento.addActionListener(lis);
		botonABMObraSocial.addActionListener(lis);
		botonABMPersona.addActionListener(lis);
	}

}