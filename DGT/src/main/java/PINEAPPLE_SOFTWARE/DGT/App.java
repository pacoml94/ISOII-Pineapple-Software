package PINEAPPLE_SOFTWARE.DGT;

import edu.uclm.esi.iso2.interfaz.IU.IURadar;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        IURadar iuradar= new IURadar();
        iuradar.getRadar().setVisible(true);;
        System.out.println("Inicio");
    }
}
