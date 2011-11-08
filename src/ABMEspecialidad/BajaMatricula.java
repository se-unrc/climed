package ABMEspecialidad;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class BajaMatricula extends JFrame{
	public Conexion.ControlConexion conexion = new Conexion.ControlConexion();private JTextField Matricula, ingMatricula;
	private javax.swing.JButton jButton1, jButton2;
    private ListaMatricula lista;
    private Container contenedor;
    // configurar GUI
    public BajaMatricula(){
		super( "Baja matricula" );
        contenedor = getContentPane();
        contenedor.setLayout( new FlowLayout() );
        // Crear una lista con toda la informacion
        lista = new ListaMatricula(contenedor);
        jButton2 = new javax.swing.JButton();
        jButton2.setText("Actualizar");
        jButton2.setName("Actualizar");
        contenedor.add(jButton2);
        // crear campo de texto con tamaño predeterminado
        Matricula = new JTextField( "Ingrese el ID de la Matricula que va a quitar                                        " );
        contenedor.add( Matricula );
        Matricula.setEditable( false );
        ingMatricula = new JTextField(40);
        contenedor.add( ingMatricula );
        //boton Volver
        jButton1 = new javax.swing.JButton();
        jButton1.setText("Volver");
        jButton1.setName("Volver");
        contenedor.add(jButton1);
        // registrar manejadores de eventos
        ManejadorCampoTexto manejador = new ManejadorCampoTexto();
        Matricula.addActionListener(manejador);
        ingMatricula.addActionListener(manejador);
        setSize( 500, 620 );
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
        // procesar eventos de campo de texto
        public void actionPerformed( ActionEvent evento ) {
        	boolean found = false; 
            String numero_matricula = "";
            // el usuario oprimió Intro en objeto JTextField ingMatricula
            if ( evento.getSource() == ingMatricula ){
                numero_matricula = evento.getActionCommand();
                if (numero_matricula.equals("")){
                	JOptionPane.showMessageDialog(rootPane,"Ingrese un valor valido.");
                } else {
	                try{
	                	found = existeComp(numero_matricula,"SELECT numero_matricula FROM climed.matricula");
	    			}catch (Exception ex){
	    		           ex.printStackTrace();
	    		    }
                }
                if (found) {
					conexion.eliminarValor("climed.matricula", "numero_matricula",numero_matricula);
					JOptionPane.showMessageDialog(rootPane,"La matricula Numero: '"+numero_matricula+"' fue eliminada");
					ingMatricula.setText("");
				}else{
					JOptionPane.showMessageDialog(rootPane,"Advertencia No existe una matricula con ese identificador");
				}
            }
        } // fin del método actionPerformed
    } // fin de la clase interna privada ManejadorCampoTexto
    
    private class ManejadorBoton implements ActionListener {
        // manejar evento de botón
        public void actionPerformed( ActionEvent evento ) {
            if (evento.getActionCommand() == "Volver") {
                dispose();
            }
            if (evento.getActionCommand() == "Actualizar") {
				refrescarLista(contenedor, lista);
			}
        }
    } // fin de la clase interna privada ManejadorBoton
}