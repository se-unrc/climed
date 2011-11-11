package ABMEspecialidad;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.sql.*;

public class BajaEspecialidad extends JFrame{
	public Conexion.ControlConexion conexion = new Conexion.ControlConexion();
	private JTextField especialidad, ingEspecialidad;
    private javax.swing.JButton jButton1, jButton2;
    private ListaEspecialidad lista;
    private Container contenedor;
	
	// configurar GUI
	public BajaEspecialidad() {
		super( "Baja especialidad" );
        contenedor = getContentPane();
		contenedor.setLayout( new FlowLayout() );
		// Crear una lista con toda la informacion
		lista = new ListaEspecialidad(contenedor);
     	jButton2 = new javax.swing.JButton();
        jButton2.setText("Actualizar");
        jButton2.setName("Actualizar");
        contenedor.add(jButton2);
		// crear campo de texto con tamaño predeterminado
		especialidad = new JTextField( "Ingrese el ID de la especialidad que va a eliminar                           " );
		contenedor.add( especialidad );
		especialidad.setEditable( false );
		ingEspecialidad = new JTextField(38);
		contenedor.add( ingEspecialidad );
		//boton Volver
		jButton1 = new javax.swing.JButton();
		jButton1.setText("Volver");
		jButton1.setName("Volver");
		contenedor.add(jButton1);
		// registrar manejadores de eventos
		ManejadorCampoTexto manejador = new ManejadorCampoTexto();
		especialidad.addActionListener(manejador);
		ingEspecialidad.addActionListener(manejador);
		setSize( 500, 600);
		setVisible( true );
		setResizable(false);
		ManejadorBoton manejadorDeBoton = new ManejadorBoton();
		jButton1.addActionListener( manejadorDeBoton );
        jButton2.addActionListener( manejadorDeBoton );    
	}
	
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
	
	// clase interna privada para el manejo de eventos
	private class ManejadorCampoTexto implements ActionListener {        
		// procesar eventos de campo de texto
		public void actionPerformed( ActionEvent evento ) {
			String id_especialidad = "";
			boolean found = false;
			if ( evento.getSource() == ingEspecialidad ){
				id_especialidad = evento.getActionCommand();				
				if (id_especialidad.equals("")) {
					JOptionPane.showMessageDialog(rootPane,"Ingrese un valor valido.");
				} else {
	                try{
	                	found = existeComp(id_especialidad,"SELECT ID_ESPECIALIDAD FROM climed.especialidad");
	    			}catch (Exception ex){
	    		           ex.printStackTrace();
	    		    }		                
	                if (found) {
						conexion.eliminarValor("climed.especialidad", "id_especialidad",id_especialidad);
						JOptionPane.showMessageDialog(rootPane,"La especialidad Numero: '"+id_especialidad+"' fue eliminada");
						ingEspecialidad.setText("");
					}else{
						JOptionPane.showMessageDialog(rootPane,"Advertencia No existe una especialidad con ese identificador");
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