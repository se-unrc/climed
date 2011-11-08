package ABMEspecialidad;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class AltaEspecialidad extends JFrame{
	public Conexion.ControlConexion conexion = new Conexion.ControlConexion();
	private JTextField especialidad, idEspecialidad, ingNombre, ingidEspecialidad;
	private javax.swing.JButton botonVolver,actualizarLista,botonInsertar;	
	private ListaEspecialidad lista;
	private Container contenedor;

	public AltaEspecialidad() {
		super( "Alta especialidad" );
		contenedor = getContentPane();
		contenedor.setLayout( new FlowLayout() );
		// Crear una lista con toda la informacion
     	lista = new ListaEspecialidad(contenedor);
        actualizarLista = new javax.swing.JButton();
        actualizarLista.setText("Actualizar");
        actualizarLista.setName("Actualizar");
        contenedor.add(actualizarLista);
		// crear campo de texto con texto predeterminado
		especialidad = new JTextField( "Ingrese aqui el nombre de la especialidad        " );
		contenedor.add( especialidad );
		especialidad.setEditable( false );
		// crear campo de texto con espacio en blanco predeterminado
		ingNombre = new JTextField(20);
		contenedor.add( ingNombre );
		// crear campo de texto con texto predeterminado
		idEspecialidad = new JTextField( "Ingrese aqui el Identificador de la especialidad" );
		contenedor.add( idEspecialidad );
		idEspecialidad.setEditable( false );
		// crear campo de texto con espacio en blanco predeterminado
		ingidEspecialidad = new JTextField(20);
		contenedor.add( ingidEspecialidad );
		ingidEspecialidad.setEditable( true ); //este modifique
		//Boton Volver
		botonVolver = new javax.swing.JButton();
		botonVolver.setText("Volver");
		botonVolver.setName("Volver");
		contenedor.add(botonVolver);
		botonInsertar = new javax.swing.JButton();
		botonInsertar.setText("Insertar");
		botonInsertar.setName("Insertar");
		contenedor.add(botonInsertar);
		// registrar manejadores de eventos		
		ManejadorCampoTexto manejador = new ManejadorCampoTexto();
		especialidad.addActionListener(manejador);
		ingNombre.addActionListener(manejador);
		idEspecialidad.addActionListener(manejador);
		ingidEspecialidad.addActionListener(manejador);      
		setSize( 600, 550 );
		setVisible( true );
		setResizable(false);
		ManejadorBoton manejadorDeBoton = new ManejadorBoton();
		ManejadorCampoTexto manejadorCampoTexto = new ManejadorCampoTexto();
		actualizarLista.addActionListener( manejadorDeBoton );
		botonVolver.addActionListener( manejadorDeBoton );
		botonInsertar.addActionListener( manejadorCampoTexto );
	} // fin del constructor de AltaEspecialidad

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
	
	public void refrescarLista (Container contenedor, ListaEspecialidad lista){
    	Component[] componentes = new Component[contenedor.countComponents()];
    	contenedor.remove(0);
		componentes = contenedor.getComponents();
		int cantComp = contenedor.countComponents();
		contenedor.removeAll();
		setVisible(false);
		lista = new ListaEspecialidad(contenedor);
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
		String nombreEspecialidad = "";
		String id_especialidad = "";
		// metodo para procesar eventos de campo de texto      
		public void actionPerformed( ActionEvent evento ) {
			boolean found=true;
			boolean found2=true;	
			if(evento.getActionCommand() == "Insertar") {
				nombreEspecialidad = ingNombre.getText();
				id_especialidad = ingidEspecialidad.getText();
				if(!(esCampoVacio(nombreEspecialidad,ingNombre))){
					try{
						found = existeComp(nombreEspecialidad,"SELECT NOMBRE FROM climed.especialidad");
					}catch (Exception ex){
						ex.printStackTrace();
					}
					if (found) {
						JOptionPane.showMessageDialog(rootPane,"Ya existe una especialidad con ese nombre");
						ingNombre.setEditable(true);
					}
				}
				if (!(esCampoVacio(id_especialidad,ingidEspecialidad))){
					try{
						found2 = existeComp(id_especialidad,"SELECT ID_ESPECIALIDAD FROM climed.especialidad");
					}catch (Exception ex){
						ex.printStackTrace();
					}	
					if (found2) {
						JOptionPane.showMessageDialog(rootPane,"Ya existe una especialidad con ese identificador");
						ingidEspecialidad.setEditable(true);
					}
				}
				if (!found && !found2) {
					int respuesta = (JOptionPane.showConfirmDialog(rootPane, "Esta Seguro De Realizar La Insercion", "Confirmacion", 0));
					if (respuesta == 0) {
						String[] columnas = {"nombre", "id_especialidad"};
						PreparedStatement insertar = conexion.prepararParaInsertar("climed.especialidad", columnas);
						try {
							insertar.setString(1, nombreEspecialidad);
							insertar.setString(2, id_especialidad);
							conexion.finalizarInsercion(insertar);
							ingidEspecialidad.setText("");
							ingNombre.setText("");
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