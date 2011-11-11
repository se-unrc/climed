
package ABMEspecialidad;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Dominio.Matricula;
import java.sql.*;
import java.util.Calendar;

public class AltaMatricula extends JFrame{
	public Conexion.ControlConexion conexion = new Conexion.ControlConexion();
	private JTextField Matricula, idDoctor, idEspecialidad, fecha, ingMatricula, ingIdDoctor, ingIdEspecialidad;
	private javax.swing.JButton jButton1,actualizarLista,botonInsertar;
	private com.toedter.calendar.JCalendar cal;
	private ListaMatricula lista;
	private Container contenedor;
	private Matricula objMatricula;
	
	public AltaMatricula() {
		super( "Alta Matricula" );
		contenedor = getContentPane();
		contenedor.setLayout( new FlowLayout() );
		// Crear una lista con toda la informacion
     	lista = new ListaMatricula(contenedor);
        actualizarLista = new javax.swing.JButton();
        actualizarLista.setText("Actualizar");
        actualizarLista.setName("Actualizar");
        contenedor.add(actualizarLista);
		// crear campo de texto con texto predeterminado
		Matricula = new JTextField( "Ingrese aqui el id de la Matricula           " );
		contenedor.add( Matricula );
		Matricula.setEditable( false );
		// crear campo de texto con tamaño predeterminado
		ingMatricula = new JTextField(20);
		contenedor.add( ingMatricula );
		// crear campo de texto con texto predeterminado
		idDoctor = new JTextField( "Ingrese aqui el Identificador del Doctor " );
		contenedor.add( idDoctor );
		idDoctor.setEditable( false );
		// crear campo de texto con tamaño predeterminado
		ingIdDoctor = new JTextField(20);
		contenedor.add( ingIdDoctor );
		// crear campo de texto con texto predeterminado
		idEspecialidad = new JTextField( "Ingrese aqui el id de la especialidad    " );
		contenedor.add( idEspecialidad ); 
		idEspecialidad.setEditable( false );
		// crear campo de texto con tamaño predeterminado
		ingIdEspecialidad = new JTextField(20);
		contenedor.add( ingIdEspecialidad );
		// crear campo de texto con texto predeterminad
		fecha = new JTextField( "Ingrese la fecha de obtencion de la matricula                                                " );
		contenedor.add( fecha );
		fecha.setEditable( false );	
		// Almanaque
		cal = new com.toedter.calendar.JCalendar();
		contenedor.add(cal);
		@SuppressWarnings("deprecation")
		Date maxFecha = new Date(2012,1,1);
		cal.setMaxSelectableDate(maxFecha);
		//boton Volver
		jButton1 = new javax.swing.JButton();
		jButton1.setText("Volver");
		contenedor.add(jButton1);
		botonInsertar = new javax.swing.JButton();
		botonInsertar.setText("Insertar");
		botonInsertar.setName("Insertar");
		contenedor.add(botonInsertar);
		// registrar manejadores de eventos
		ManejadorCampoTexto manejador = new ManejadorCampoTexto();
		Matricula.addActionListener(manejador);
		ingMatricula.addActionListener(manejador);
		idDoctor.addActionListener(manejador);
		ingIdDoctor.addActionListener(manejador); 
        idEspecialidad.addActionListener(manejador);
		ingIdEspecialidad.addActionListener(manejador);
		fecha.addActionListener(manejador);
		setSize( 600, 750 );
		setVisible( true );
        setResizable(false);
		ManejadorBoton manejadorDeBoton = new ManejadorBoton();
		actualizarLista.addActionListener( manejadorDeBoton );		
		jButton1.addActionListener( manejadorDeBoton );
		ManejadorCampoTexto manejadorCampoTexto = new ManejadorCampoTexto();
		botonInsertar.addActionListener( manejadorCampoTexto );
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
	
	public boolean esCampoVacio(String texto,JTextField campo){
		boolean vacio=false;
		if (texto.equals("")) {
			JOptionPane.showMessageDialog(rootPane,"Ingrese un valor valido.");
			campo.setEditable(true);
			vacio = true;
		}
		return vacio;
	}
	
    // clase interna privada para el manejo de eventos
	private class ManejadorCampoTexto implements ActionListener {		
		//String numero_matricula = "";
		//String id_medico = "";
		//String id_especialidad = "";
		String fechaMatricula = "";
		//Date fecha = new java.sql.Date(11,8,4);
		// procesar eventos de campo de texto
		public void actionPerformed( ActionEvent evento ) {	    
			boolean found = false;
			boolean existeEspecialidad = false;
			if(evento.getActionCommand() == "Insertar") {
				fechaMatricula+=cal.getCalendar().get(Calendar.DAY_OF_MONTH)+
						"-"+(1+cal.getCalendar().get(Calendar.MONTH))+
						"-"+cal.getCalendar().get(Calendar.YEAR);
				
				objMatricula = new Matricula(ingMatricula.getText(),ingIdDoctor.getText(),ingIdEspecialidad.getText(),fechaMatricula);				
				if(!(esCampoVacio(objMatricula.getNumero(),ingMatricula))){
					try{
						found = existeComp(objMatricula.getNumero(),"SELECT numero_matricula FROM climed.Matricula");
					}catch (Exception ex){
						ex.printStackTrace();
					}
					if (found) {
						JOptionPane.showMessageDialog(rootPane,"Ya existe una especialidad con ese nombre");
						ingMatricula.setEditable(true);
					}					
				}
				if(!(esCampoVacio(objMatricula.getIdEspecialidad(),ingIdEspecialidad))){
					try{
						existeEspecialidad = existeComp (objMatricula.getIdEspecialidad(),"SELECT id_especialidad FROM climed.especialidad");
					}catch (Exception ex){
						ex.printStackTrace();
					}
					if (!existeEspecialidad) {
						JOptionPane.showMessageDialog(rootPane,"No existe una especialidad con ese numero");
						ingIdEspecialidad.setEditable(true);
					}	
				}
				if (!found && !esCampoVacio(objMatricula.getIdMedico(),ingIdDoctor) && !esCampoVacio(objMatricula.getIdEspecialidad(),ingIdEspecialidad) && existeEspecialidad) {
					int respuesta = (JOptionPane.showConfirmDialog(rootPane, "Esta Seguro De Realizar La Insercion", "Confirmacion", 0));
					if (respuesta == 0) {						
						String[] columnas = {"numero_matricula", "id_medico", "id_especialidad", "fecha_de_obtencion"};
				        PreparedStatement insertar = conexion.prepararParaInsertar("climed.matricula", columnas);
				        try {
				        	insertar.setString(1, objMatricula.getNumero());
				        	insertar.setString(2, objMatricula.getIdMedico());
				        	insertar.setString(3, objMatricula.getIdEspecialidad());
				        	insertar.setString(4, objMatricula.getFechaObtencion());
				        	conexion.finalizarInsercion(insertar);
				        	ingMatricula.setText("");
							ingIdDoctor.setText("");
							ingIdEspecialidad.setText("");
				        }catch(Exception ex){
				                System.err.println("ERROR EN prepararParaInsertar");
				                ex.printStackTrace();		        	
				        }
				        
					}
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
			}
		}
	} // fin de la clase interna privada ManejadorBoton
}
