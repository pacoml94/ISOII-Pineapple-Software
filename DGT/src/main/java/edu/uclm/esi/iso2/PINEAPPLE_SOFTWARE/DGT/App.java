package edu.uclm.esi.iso2.PINEAPPLE_SOFTWARE.DGT;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws SQLException{
    	Scanner leer= new Scanner(System.in);
    	MySQLBD bd = new MySQLBD();
    	bd.conectar();
    	System.out.println("Pulse 1 para consultar base de datos: ");
    	int opcion=leer.nextInt();
    	if(opcion == 1)
    		consulta(bd);
    }
    
    public static void consulta(MySQLBD bd) throws SQLException{
    	ResultSet rs;
    	String sql = "SELECT * FROM hibernate_sequences";
    	rs = bd.consulta(sql);
    	
    	while(rs.next()){
    		int points = rs.getInt("points");
    		int id = rs.getInt("id");
    		System.out.println("id: "+id+", puntos: "+points);
    		String s=rs.getString("sequence_name");
    	}
    	rs.close();
    }
}
