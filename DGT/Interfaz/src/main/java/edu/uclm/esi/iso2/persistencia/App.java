package edu.uclm.esi.iso2.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
		Scanner leer= new Scanner(System.in);
		boolean repetir = false;
		
    	do {
    		try {
    			MySQLBD bd = new MySQLBD();
    			bd.conectar();
    			System.out.println("Pulse 1 para consultar base de datos: ");
    			int opcion=leer.nextInt();
    			if(opcion == 1) {
    				repetir = true;
    				consulta(bd);
    			}
    		} catch (SQLException e) {
    			System.err.println("Error al conectar con la base de datos");
    			e.printStackTrace();
    			leer.nextLine();
    			repetir = false;
    		} catch (InputMismatchException e) {
    			System.err.println("Error al introducir la consulta");
    			e.printStackTrace();
    			leer.nextLine();
    			repetir = false;
    		} catch (Exception e) {
    			System.err.println("Error general");
    			e.printStackTrace();
    			leer.nextLine();
    		}
		} while (repetir == false);
    }
    
    public static void consulta(MySQLBD bd) throws SQLException{
    	ResultSet rs;
    	String sql = "SELECT * FROM hibernate_sequences";
    	rs = bd.consulta(sql);
    	
    	while(rs.next()){
    		int points = rs.getInt("next_val");
    		String id = rs.getString("sequence_name");
    		System.out.println("id: "+id+", puntos: "+points);
    		String s=rs.getString("sequence_name");
    	}
    	rs.close();
    }
}