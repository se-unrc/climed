package ABMEspecialidad;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class ModifEspecialidad extends JFrame{
	public Conexion.ControlConexion conexion = new Conexion.ControlConexion();
	private JTextField especialidad, nombre, ingEspecialidad, ingNombre, idEspecialidad, idEsp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private ListaEspecialidad lista;
    private Container contenedor;
    // configurar GUI
    public ModifEspecialidad() {
		super( "Modificacion especialidad" );
        contenedor = getContentPane();
        contenedor.setLayout( new FlowLayout() );
        // Crear una lista con toda la informacion
     	lista = new ListaEspecialidad(contenedor);
     	jButton2 = new javax.swing.JButton();
        jButton2.setText("Actualizar");
        jButton2.setName("Actualizar");
        contenedor.add(jButton2);
        // crear campo de texto con tamaño predeterminado
        especialidad = new JTextField( "Ingrese el ID de la Especialidad a modificar     " );
        contenedor.add( especialidad );
        especialidad.setEditable( false );
        ingEspecialidad = new JTextField(20);
        contenedor.add( ingEspecialidad );
        // crear campo de texto con texto predeterminado
        nombre = new JTextField( "Ingrese el nuevo nombre, si quiere modificarlo" );
        contenedor.add( nombre );
        nombre.setEditable( false );
        // crear campo de texto con texto predeterminado
        ingNombre = new JTextField(20);
        contenedor.add( ingNombre );
        ingNombre.setEditable( false );
        // crear campo de texto con texto predeterminado
        idEspecialidad = new JTextField( "Ingrese el nuevo ID, si quiere modificarlo         " );
        contenedor.add( idEspecialidad );
        idEspecialidad.setEditable( false );
        // crear campo de texto con texto predeterminado        
        idEsp = new JTextField(20);
        contenedor.add( idEsp );
        idEsp.setEditable( false ); 
        //boton Volver
        jButton1 = new javax.swing.JButton();
        jButton1.setText("Volver");
        jButton1.setName("Volver");
        contenedor.add(jButton1);
        // registrar manejadores de eventos
		ManejadorCampoTexto manejador = new ManejadorCampoTexto();
		especialidad.addActionListener(manejador);
		ingEspecialidad.addActionListener(manejador);
		nombre.addActionListener(manejador);
		ingNombre.addActionListener(manejador);
		idEspecialidad.addActionListener(manejador);
		idEsp.addActionListener(manejador);
		setSize( 610, 600 );
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
	
	public String campoVacio(JTextField campo,String campoDeInteres,String nomVar,String idEsp,ActionEvent evento){
		int respuesta = 1;
		campoDeInteres = evento.getActionCommand();
        if (campoDeInteres.equals("")) {
			JOptionPane.showMessageDialog(rootPane,"Ingrese un valor valido.");
		} else {
            respuesta = (JOptionPane.showConfirmDialog(rootPane, "Ingreso: " + campoDeInteres + "     ¿Esta Seguro?", "Confirmacion", 0));
            if (respuesta == 0) {                  
				campo.setEditable(false);
				String query = "UPDATE climed.especialidad SET "+nomVar+"='"+campoDeInteres+"' WHERE id_especialidad='"+idEsp+"'";
				conexion.editar(query);
            }
		}
        return campoDeInteres;			
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
        String id_especialidad = "";
		String nombreEspecialidadNuevo = "";
		String id_especialidadNueva = "";
		boolean found = false;
		boolean found2 = false;
		// procesar eventos de campo de texto
		public void actionPerformed( ActionEvent evento ) {			
			int respuesta = 1;
			// el usuario oprimió Intro en objeto JTextField campoTexto1
			if ( evento.getSource() == ingEspecialidad ){
				id_especialidad = evento.getActionCommand();
				if (id_especialidad.equals("")) {
					JOptionPane.showMessageDialog(rootPane,"Ingrese un valor valido.");
				} else {
					respuesta = (JOptionPane.showConfirmDialog(rootPane,"Ingreso: "+id_especialidad+". Esta Seguro?","Modificar Especialidad",0));
					if (respuesta == 0) {
						try{
		                	found = existeComp(id_especialidad,"SELECT ID_ESPECIALIDAD FROM climed.especialidad");
		    			}catch (Exception ex){
		    		           ex.printStackTrace();
		    		    }
						if (found) {
							ingNombre.setEditable(true);
							idEsp.setEditable(true);
							ingEspecialidad.setEditable(false);
						}else{
							JOptionPane.showMessageDialog(rootPane,"Advertencia No existe una especialidad con ese identificador");
						}
					}
				}
			}			
			// el usuario oprimió Intro en objeto JTextField campoTexto2
			if(evento.getSource() == ingNombre) {
				nombreEspecialidadNuevo = campoVacio(ingNombre,nombreEspecialidadNuevo,"nombre",id_especialidad,evento);
			}
			else if(evento.getSource() == idEsp) {
				id_especialidadNueva = campoVacio(idEsp,id_especialidadNueva,"id_especialidad",id_especialidad,evento);
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
				ingNombre.setText("");
				idEsp.setText("");
				ingEspecialidad.setText("");
				ingNombre.setEditable(false);
				idEsp.setEditable(false);
				ingEspecialidad.setEditable(true);
			}
		}
	}
}