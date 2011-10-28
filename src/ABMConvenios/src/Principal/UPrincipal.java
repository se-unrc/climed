package Principal;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.sql.Connection;

import javax.swing.JFrame;

import Conexion.Conexion;
import Mediadores.MediadorABMConvenio;

public class UPrincipal {

	public static JFrame guiPrincipal;
	
	public static void main(String args []){
		try{			
			Connection conexion = Conexion.getInstancia();
			MediadorABMConvenio mc = new MediadorABMConvenio(guiPrincipal,conexion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
