public class Main {

	public static void main(String args[]) {
		System.out.println("hola mundo");
		//@SuppressWarnings("unused")
		//Mediador_Principal med = new Mediador_Principal();
		//MediadorGuiPersona a = new MediadorGuiPersona();
		try{
			ABMPersona.ABM("Delete FROM hora_extra WHERE (numero=9)AND (horas=9) and (fecha='1')");
			ABMPersona.ABM("INSERT INTO hora_extra VALUES (9,9,'1')");
			ABMPersona.ABM("UPDATE hora_extra SET numero=18 WHERE (numero=9)AND (horas=9) and (fecha='1')");
			ABMPersona.ABM("INSERT INTO hora_extra VALUES (9,9,'1')");
		
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("se re rompio");
		}
	}

}