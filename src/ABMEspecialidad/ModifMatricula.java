package ABMEspecialidad;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.sql.*;
import java.util.Calendar;


public class ModifMatricula extends JFrame{

	public Conexion.ControlConexion conexion = new Conexion.ControlConexion();
    private JTextField Matricula, nuevaMat, nuevaEsp, nuevoDoc, nuevaFecha, ingMatricula, ingNuevaMat, ingNuevoDoc, ingNuevaEsp, ingDia, ingMes, ingAnio;;
    private javax.swing.JButton jButton1, jButton2, botonFecha;
    private ListaMatricula lista;
    private com.toedter.calendar.JCalendar cal;
    private Container contenedor;
//    protected Component[] componentes;
  
	public ModifMatricula(){
		super( "Modificacion Matricula" );
        contenedor = getContentPane();
        contenedor.setLayout( new FlowLayout() );
        // Crear una lista con toda la informacion
     	lista = new ListaMatricula(contenedor);
        jButton2 = new javax.swing.JButton();
        jButton2.setText("Actualizar");
        jButton2.setName("Actualizar");
        contenedor.add(jButton2);
        // crear campo de texto con texto predeterminado
        Matricula = new JTextField( "Ingrese el numero de Matricula a modificar                     " );
        contenedor.add( Matricula );
        Matricula.setEditable( false );
        // crear campo de texto con espacio en blanco predeterminado
        ingMatricula = new JTextField(20);
        contenedor.add( ingMatricula );        
        // crear campo de texto con texto predeterminado
        nuevaMat = new JTextField( "Ingrese el nuevo numero de matricula                            " );
        contenedor.add( nuevaMat );
        nuevaMat.setEditable( false );
        // crear campo de texto con espacio en blanco predeterminado
        ingNuevaMat = new JTextField(20);
        contenedor.add( ingNuevaMat );
        ingNuevaMat.setEditable( false );        
        // crear campo de texto con texto predeterminado
        nuevaEsp = new JTextField( "Ingrese aqui el nuevo id de la especialidad                     " );
        contenedor.add(nuevaEsp);
        nuevaEsp.setEditable( false );
        // crear campo de texto con tamaño predeterminado
        ingNuevaEsp = new JTextField(20);
        contenedor.add( ingNuevaEsp );
        ingNuevaEsp.setEditable(false);
        // crear campo de texto con texto predeterminado
        nuevoDoc = new JTextField( "Ingrese el nuevo id del doctor                                         " );
        contenedor.add(nuevoDoc);
		nuevoDoc.setEditable(false);
        // crear campo de texto con tamaño predeterminado
        ingNuevoDoc = new JTextField(20);
        contenedor.add( ingNuevoDoc );
        ingNuevoDoc.setEditable(false);
        // crear campo de texto con texto predeterminado
        nuevaFecha = new JTextField( "Seleccione la Actualizar Fecha de obtencion de la matricula                             " );
        contenedor.add(nuevaFecha);
        nuevaFecha.setEditable( false );
        // Almanaque
     	cal = new com.toedter.calendar.JCalendar();
     	contenedor.add(cal);
     	@SuppressWarnings("deprecation")
		Date maxFecha = new Date(2012, 1, 1);
		cal.setMaxSelectableDate(maxFecha);
     	//boton Fecha
     	botonFecha = new javax.swing.JButton();
     	botonFecha.setText("Actualizar Fecha");
     	botonFecha.setName("Actualizar Fecha");
     	contenedor.add(botonFecha);
		botonFecha.setEnabled(false);
        //boton Volver
        jButton1 = new javax.swing.JButton();
        jButton1.setText("Volver");
        jButton1.setName("Volver");
        contenedor.add(jButton1);
        // registrar manejadores de eventos
        ManejadorCampoTexto manejador = new ManejadorCampoTexto();
        Matricula.addActionListener(manejador);
        ingMatricula.addActionListener(manejador);
        nuevaMat.addActionListener(manejador);
        ingNuevaMat.addActionListener(manejador);        
        nuevaEsp.addActionListener(manejador);
        ingNuevaEsp.addActionListener(manejador);
        nuevoDoc.addActionListener(manejador);
        ingNuevoDoc.addActionListener(manejador);
        nuevaFecha.addActionListener(manejador);
        botonFecha.addActionListener(manejador);
        setSize( 620, 720 );
        setVisible( true );
        setResizable(false);
        ManejadorBoton manejadorDeBoton = new ManejadorBoton();
        jButton1.addActionListener( manejadorDeBoton );
        jButton2.addActionListener( manejadorDeBoton );
	} // fin del constructor de PruebaCampoTexto

	public boolean existeComp(String listaIngreso,String query2) throws SQLException {
		int maximoTamanioArreglo= 50;
		String[] arreglo = new String[maximoTamanioArreglo];
		int contador = 0;
		ResultSet resultSet = conexion.ejecutarSentencia(query2);
		while(resultSet.next()) {
			arreglo[contador] = (resultSet.getString(1)).toString();
			contador++;
		}		
		boolean found = false;
		for (int index = 0;!found && index<arreglo.length;index++) {
			if (listaIngreso.equals(arreglo[index])) {
				found = true;
			}
		}		
		return found;
	}

	public void refrescarLista (Container contenedor, ListaMatricula lista){
    	Component[] componentes = new Component[contenedor.countComponents()];
    	contenedor.remove(0);
		componentes = contenedor.getComponents();
		int cantComp = contenedor.countComponents();
		contenedor.removeAll();
		setVisible(false);
		lista = new ListaMatricula(contenedor);
		for (int i = 0; i<cantComp;i++) {
			contenedor.add(componentes[i]);
		}				
		setVisible(true);
    }
	
	// clase interna privada para el manejo de eventos
	private class ManejadorCampoTexto implements ActionListener {
        String numero_matricula = "";
        String numero_matriculaNueva = "";
        String id_medicoNueva = "";
        String id_especialidadNueva = "";
        String fechaMatricula = "";
        boolean found = false;
        boolean existeEspecialidad = false;
		// procesar eventos de campo de texto
        public void actionPerformed( ActionEvent evento ) {
			int respuesta = 1;
			// el usuario oprimió Intro en objeto JTextField campoTexto1
			if ( evento.getSource() == ingMatricula ){
				numero_matricula = evento.getActionCommand();
				if (numero_matricula.equals("")) {
					JOptionPane.showMessageDialog(rootPane,"Ingrese un valor valido.");
				} else {
				respuesta = (JOptionPane.showConfirmDialog(rootPane,"Ingreso: "+numero_matricula+", Esta Seguro?","Confirmacion",0));
					if (respuesta == 0) {
						try{
		                	found = existeComp(numero_matricula,"SELECT numero_matricula FROM climed.matricula");
		    			}catch (Exception ex){
		    		           ex.printStackTrace();
		    		    }							
						if (found) {
							ingNuevaMat.setEditable(true);
							ingNuevaEsp.setEditable(true);
							ingNuevoDoc.setEditable(true);
							botonFecha.setEnabled(true);
							ingMatricula.setEditable(false);							
						}else{
							JOptionPane.showMessageDialog(rootPane,"Advertencia. No existe una matricula con ese identificador");
						}
					}
				}
			}
			if(evento.getSource() == ingNuevaMat) {
				numero_matriculaNueva = evento.getActionCommand();
				if (numero_matriculaNueva.equals("")) {
					JOptionPane.showMessageDialog(rootPane,"Ingrese un valor valido.");
				} else {
					respuesta = (JOptionPane.showConfirmDialog(rootPane, "Ingreso: " +numero_matriculaNueva+ "     ¿Quiere confirmar el cambio?", "Confirmacion", 0));
					if (respuesta == 0) {                   
						try{
							found = existeComp(numero_matriculaNueva,"SELECT numero_matricula FROM climed.Matricula");
						}catch (Exception ex){
					           ex.printStackTrace();
					    }		
						if (found) {
							JOptionPane.showMessageDialog(rootPane,"Ya existe una matricula con ese numero");
							ingNuevaMat.setEditable(true);
						}
						else {
							String query = "UPDATE climed.matricula SET numero_matricula='"+numero_matriculaNueva+"' WHERE numero_matricula='"+numero_matricula+"'";
							conexion.editar(query);
							ingNuevaMat.setEditable(false);
							numero_matricula=numero_matriculaNueva;
						}
					}
				}
			}
			if(evento.getSource() == ingNuevaEsp) {
				id_especialidadNueva = evento.getActionCommand();
				boolean noEsCampoVacio = !(id_especialidadNueva.equals(""));
				if(noEsCampoVacio){
					try{
						existeEspecialidad = existeComp (id_especialidadNueva,"SELECT id_especialidad FROM climed.especialidad");
					}catch (Exception ex){
						ex.printStackTrace();
					}
					if (!existeEspecialidad) {
						JOptionPane.showMessageDialog(rootPane,"No existe una especialidad con ese numero");
						ingNuevaEsp.setEditable(true);
					}	
				}
				boolean vacio = id_especialidadNueva.equals("");
				if (vacio) {
					JOptionPane.showMessageDialog(rootPane,"Ingrese un valor valido.");				
				}
				if (!vacio && noEsCampoVacio && existeEspecialidad){
					respuesta = (JOptionPane.showConfirmDialog(rootPane, "Ingreso: " +id_especialidadNueva+ "     ¿Quiere confirmar el cambio?", "Confirmacion", 0));
					if (respuesta == 0) {                   
						ingNuevaEsp.setEditable(false);                    
						String query = "UPDATE climed.matricula SET id_especialidad='"+id_especialidadNueva+"' WHERE numero_matricula='"+numero_matricula+"'";
						conexion.editar(query);	
					}
				}
			}
			if ( evento.getSource() == ingNuevoDoc ){
				id_medicoNueva = evento.getActionCommand();
				if (id_medicoNueva.equals("")) {
					JOptionPane.showMessageDialog(rootPane,"Ingrese un valor valido.");
				} else {
					respuesta = (JOptionPane.showConfirmDialog(rootPane,"Ingreso: "+id_medicoNueva+", ingrese el nuevo nuevaMat","Esta Seguro?",0));
					if (respuesta == 0) {
						ingNuevoDoc.setEditable(false);
						String query = "UPDATE climed.matricula SET id_medico='"+id_medicoNueva+"' WHERE numero_matricula='"+numero_matricula+"'";
						conexion.editar(query);
					}
				}
			}
			if(evento.getActionCommand() == "Actualizar Fecha") {
				fechaMatricula+=cal.getCalendar().get(Calendar.DAY_OF_MONTH)+
				"-"+(1+cal.getCalendar().get(Calendar.MONTH))+
				"-"+cal.getCalendar().get(Calendar.YEAR);				
				respuesta = (JOptionPane.showConfirmDialog(rootPane, "Ingreso: " + fechaMatricula + "     ¿Esta Seguro?", "Confirmacion", 0));
				if (respuesta == 0) {  						
					String query = "UPDATE climed.matricula SET fecha_de_obtencion='"+fechaMatricula+"' WHERE numero_matricula='"+numero_matricula+"'";
	                conexion.editar(query);
	                fechaMatricula = "";
	            }	
            }			
		} // fin del método actionPerformed
	} // fin de la clase interna privada ManejadorCampoTexto
   
	private class ManejadorBoton implements ActionListener {		
		// manejar evento de botón
		public void actionPerformed( ActionEvent evento ) {
			if(evento.getActionCommand() == "Volver") {
				dispose();
            }
			if (evento.getActionCommand() == "Actualizar") {
				refrescarLista(contenedor, lista);
				ingNuevaMat.setText("");
				ingNuevaEsp.setText("");
				ingNuevoDoc.setText("");
				ingMatricula.setText("");
				ingNuevaMat.setEditable(false);
				ingNuevaEsp.setEditable(false);
				ingNuevoDoc.setEditable(false);
				ingMatricula.setEditable(true);
				
			}
		}
    }
}
