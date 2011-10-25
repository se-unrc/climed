import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ABMComidasPlugin extends JFrame{
         
    private JTextArea areaTexto1;     
    private JTextField quickFindField, q2, q3;	
    private Container contenedor;
    private JButton altaC;
    private JButton bajaC;
    private JButton modificarC;

 
	public ABMComidasPlugin(){
		super("Comidas");
		try{
			
			jbInit();
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
	

	public void jbInit()	{
		Box caja = Box.createVerticalBox();
       
		Box cuadro3 = Box.createHorizontalBox();
		Box cuadro = Box.createHorizontalBox();
 		Box cuadro2 = Box.createHorizontalBox();
		Box cuadro4 = Box.createHorizontalBox();
 		
		cuadro.add(new JLabel("nombre de la comida:"));
		quickFindField = new JTextField(30);
		cuadro.add(quickFindField);

		cuadro4.add(new JLabel("descripcion de la comida:"));
		q3 = new JTextField(30);
		cuadro4.add(q3);

	 	cuadro3.add(new JLabel("nuevo nombre:"));
		q2 = new JTextField(30);
		cuadro3.add(q2);


		altaC = new JButton( "Alta >>>" );
		cuadro2.add( altaC );
		bajaC = new JButton( "Baja >>>" );
		cuadro2.add( bajaC );
		modificarC = new JButton( "Modificar >>>" );
		cuadro2.add( modificarC );
		//maneja la acccion del boton Export
		altaC.addActionListener(new ActionListener() { 
			public void actionPerformed( ActionEvent evento ){
				String mb = null;
				String desc = null;
				try{
					mb = quickFindField.getText();
					desc = q3.getText();
					Comidas com = new Comidas(mb,desc);
					ABMComidas.alta(com);
				}catch(Exception e){
					JOptionPane.showMessageDialog( null, "no es valido","Error", JOptionPane.ERROR_MESSAGE );	
				}
				if (mb != null){
					JOptionPane.showMessageDialog( null, "se ha realizado el alta correctamente","contruccion ok", JOptionPane.PLAIN_MESSAGE );
				}
		     }
 
		}
		);
		bajaC.addActionListener(new ActionListener() { 
			public void actionPerformed( ActionEvent evento ){
				String mb = null;
				String desc = null;
				try{
					mb = quickFindField.getText();
					desc = q3.getText();
					Comidas com = new Comidas(mb,desc);
					ABMComidas.baja(com);
				}catch(Exception e){
					JOptionPane.showMessageDialog( null, "no es valido","Error", JOptionPane.ERROR_MESSAGE );	
				}
				if (mb != null){
					JOptionPane.showMessageDialog( null, "se ha realizado la baja correctamente","contruccion ok", JOptionPane.PLAIN_MESSAGE );
				}
		     }
 
		}
		);
		modificarC.addActionListener(new ActionListener() { 
			public void actionPerformed( ActionEvent evento ){
				String mb = null;
				String mb1 = null;
				String desc = null;
				try{
					mb = quickFindField.getText();
					mb1 = q2.getText();
					desc = q3.getText();
					Comidas com = new Comidas(mb,desc);
					ABMComidas.modificar(com, "nombre", mb1);
				}catch(Exception e){
					JOptionPane.showMessageDialog( null, "no es valido","Error", JOptionPane.ERROR_MESSAGE );	
				}
				if (mb != null){
					JOptionPane.showMessageDialog( null, "se ha realizado la modificacion correctamente","contruccion ok", JOptionPane.PLAIN_MESSAGE );
				}
		     }
 
		}
  
		);
		caja.add(cuadro);
		caja.add(cuadro4);
		caja.add(cuadro3);
		caja.add(cuadro2);
		Container contenedor = getContentPane();
		contenedor.add( caja );
		this.pack();
		this.setVisible(true);
		this.addWindowListener( new WindowAdapter() { 
			public void windowClosing( WindowEvent evt ) { 
				System.exit( 0 ); 
			} 
		} );
	}
	public static void main(String []args){
		ABMComidasPlugin com = new ABMComidasPlugin();
	}
}
