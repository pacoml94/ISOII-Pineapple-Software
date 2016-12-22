package edu.uclm.esi.iso2.multas.tests;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.css.ElementCSSInlineStyle;

import edu.uclm.esi.iso2.multas.dao.DriverDao;
import edu.uclm.esi.iso2.multas.dao.GeneralDao;
import edu.uclm.esi.iso2.multas.dao.OwnerDao;
import edu.uclm.esi.iso2.multas.dao.VehicleDao;
import edu.uclm.esi.iso2.multas.domain.Driver;
import edu.uclm.esi.iso2.multas.domain.Inquiry;
import edu.uclm.esi.iso2.multas.domain.Manager;
import edu.uclm.esi.iso2.multas.domain.Owner;
import edu.uclm.esi.iso2.multas.domain.Sanction;
import edu.uclm.esi.iso2.multas.domain.SanctionHolder;
import edu.uclm.esi.iso2.multas.domain.Vehicle;

public class TestManager {
    
    private Configuration cfg;
    private SessionFactory factory;
    private Session session;
    private Manager manager = Manager.get();
    private String [] lugares = {"Puertollano","Bilbao","Madrid","Toledo","Cadiz","Barcelona","Ciudad Real","Santiago de Compostela","Oviedo","Gijon","Valladolid","Zaragoza","Valencia","Sevilla","Albacete"};
    
    /**@Before
    public void setUp() throws IOException {
        cfg=new Configuration();
        cfg.configure("hibernate.cfg.xml");
        factory=cfg.buildSessionFactory();
        session=factory.openSession();

        Transaction t=session.beginTransaction();
        Query<Inquiry> query=session.createQuery("delete from Inquiry");
        query.executeUpdate();
        query=session.createQuery("delete from Sanction");
        query.executeUpdate();
        t.commit();
    }**/
    /*
   @Test
    public void test() {
        Manager m = Manager.get();
        int idInquiry = m.openInquiry("0000", 200, "Ciudad Real", 120);
        GeneralDao <Inquiry>dao = new GeneralDao<>();
        Inquiry i=dao.findById(Inquiry.class, idInquiry);
        assertNotNull(i);
        Sanction s= m.identifyDriver(idInquiry, "5000000");
        DriverDao dDao = new DriverDao();
        Driver driver = dDao.findByDni("5000000");
        m.pay(s.getId());
        assertFalse(driver.getPoints()==12);
    }*/
    /*
	@Test
	public void test140_120() {
		Manager m=Manager.get();
		int idExpediente=m.openInquiry("0001", 140, "La Ronda", 120);
		Sanction multa=m.identifyDriver(idExpediente, "5000002");
		multa.pay();
		m.pay(multa.getId());
		assertNotNull(multa.getDateOfPayment());
		assertTrue(multa.getAmount()==100);
		assertTrue(multa.getPoints()==0);
	}

	@Test
	public void test160_120() {
		Manager m=Manager.get();
		int idExpediente=m.openInquiry("0001", 160, "La Ronda", 120);
		Sanction multa=m.identifyDriver(idExpediente, "5000002");
		multa.pay();
		m.pay(multa.getId());
		assertNotNull(multa.getDateOfPayment());
		assertTrue(multa.getAmount()==300);
		assertTrue(multa.getPoints()==2);
	}*/

	@Test
	public void testmaxSpeed30() {
		int ciudadAleatoria;
		for(int i = 31; i < 82; i++) {
			ciudadAleatoria = (int) ((Math.random()*(lugares.length-1)));
			int idExpediente = manager.openInquiry("0002", i, lugares[ciudadAleatoria], 30);
			Sanction multa = manager.identifyDriver(idExpediente, "5000002");
			manager.pay(multa.getId());
			assertNotNull(multa.getDateOfReception());
			
			if (i>30 && i<51){
				assertTrue(multa.getPoints()==0);
			} else if(i>50 && i<61){
				assertTrue(multa.getPoints()==2);
			} else if(i>60 && i<71){
				assertTrue(multa.getPoints()==4);
			} else if(i>70 && i<81){
				assertTrue(multa.getPoints()==6);			
			} else {
				System.out.println(multa.getPoints());
				assertTrue(multa.getPoints()==6);
			}
		}
	}
		
	@Test
	public void testmaxSpeed40() {
		int ciudadAleatoria;
		for(int i = 41; i < 92; i++) {
			ciudadAleatoria = (int) ((Math.random()*(lugares.length-1)));
			int idExpediente = manager.openInquiry("0002", i, lugares[ciudadAleatoria], 40);
			Sanction multa = manager.identifyDriver(idExpediente, "5000002");			
			manager.pay(multa);
			
			if (i>= 61 && i<=70) {
				assertTrue(multa.getPoints()==2);
			} else if (i >= 71 && i <= 80) {
				assertTrue(multa.getPoints()==4);				
			} else if (i >= 81 && i <= 90) {
				assertTrue(multa.getPoints()==6);
			} else if (i >= 91) {
				assertTrue(multa.getPoints()==6);
			}
			assertNotNull(multa.getDateOfReception());
		}
	}

	@Test
	public void testmaxSpeed50() {
		int ciudadAleatoria;
		for(int i = 51; i < 102; i++) {
			ciudadAleatoria = (int) ((Math.random()*(lugares.length-1)));
			int idExpediente = manager.openInquiry("0002", i, lugares[ciudadAleatoria], 50);
			Sanction multa = manager.identifyDriver(idExpediente, "5000002");
			manager.pay(multa);
			
			if (i>=51 && i<=70){
				System.out.println("entra "+i);
				assertTrue(multa.getPoints()==0);
			} else if(i>=71 && i<=80){
				System.out.println("entra "+i);
				assertTrue(multa.getPoints()==2); 
				//No resta los puntos porque la variable
				//puntos de la clase sanction siempre esta a 0
			} else if(i>=81 && i<=90){
				System.out.println("entra "+i);
				assertTrue(multa.getPoints()==4);
			}else if(i>=91 && i<=100){
				System.out.println("entra "+i);
				assertTrue(multa.getPoints()==6);			
			} else {
				System.out.println("entra "+i);
				assertTrue(multa.getPoints()==6);
			}
			//No resta los puntos porque la variable
			//puntos de la clase sanction siempre esta a 0

			assertNotNull(multa.getDateOfReception());
		}
	}
	
	@Test
	public void testFalloAlRestarPuntos() {
		int ciudadAleatoria = (int) ((Math.random()*(lugares.length-1)));
		int idExpediente = manager.openInquiry("0002", 100, lugares[ciudadAleatoria], 60);
		Sanction multa = manager.identifyDriver(idExpediente, "5000002");
		manager.pay(multa.getId());
		DriverDao driverDao = new DriverDao();
		/*2 p y 300 €*/
		Driver driver = driverDao.findByDni("5000002");
		assertNotNull(multa.getDateOfReception());
		
		try {
			assertTrue(driver.getPoints()==10);
			assertTrue(multa.getAmount()==300);
		} catch (Exception e) {
			fail("Deberian haberse restado los puntos. Puntos del conductor: "+driver.getPoints());
		}
	}
	
	@Test
	public void testmaxSpeed60() {
		int ciudadAleatoria;
		for(int i = 61; i < 132; i++) {
			ciudadAleatoria = (int) ((Math.random()*(lugares.length-1)));
			int idExpediente = manager.openInquiry("0002", i, lugares[ciudadAleatoria], 60);
			Sanction multa = manager.identifyDriver(idExpediente, "5000002");
			manager.pay(multa.getId());
			
			if (i>60 && i<91){
				assertTrue(multa.getPoints()==0);
			} else if(i>90 && i<111){
				assertTrue(multa.getPoints()==2);
			} else if(i>110 && i<121){
				assertTrue(multa.getPoints()==4);
			} else if(i>120 && i<131){
				assertTrue(multa.getPoints()==6);			
			} else {
				assertTrue(multa.getPoints()==6);
			}
			
			
			assertNotNull(multa.getDateOfReception());
		}
	}
	
	@Test
	public void testmaxSpeed70() {
		int ciudadAleatoria;
		for(int i = 71; i < 142; i++) {
			ciudadAleatoria = (int) ((Math.random()*(lugares.length-1)));
			int idExpediente = manager.openInquiry("0002", i, lugares[ciudadAleatoria], 70);
			Sanction multa = manager.identifyDriver(idExpediente, "5000002");
			manager.pay(multa.getId());
			
			if (i>70 && i<101){
				assertTrue(multa.getPoints()==0);
			} else if(i>100 && i<121){
				assertTrue(multa.getPoints()==2);
			} else if(i>120 && i<131){
				assertTrue(multa.getPoints()==4);
			} else if(i>130 && i<141){
				assertTrue(multa.getPoints()==6);			
			} else {
				assertTrue(multa.getPoints()==6);
			}
			
			assertNotNull(multa.getDateOfReception());
		}
	}
	
	@Test
	public void testmaxSpeed80() {
		int ciudadAleatoria;
		for(int i = 81; i < 152; i++) {
			ciudadAleatoria = (int) ((Math.random()*(lugares.length-1)));
			int idExpediente = manager.openInquiry("0002", i, lugares[ciudadAleatoria], 80);
			Sanction multa = manager.identifyDriver(idExpediente, "5000002");
			manager.pay(multa.getId());
			
			if (i>80 && i<111){
				assertTrue(multa.getPoints()==0);
			} else if(i>110 && i<131){
				assertTrue(multa.getPoints()==2);
			} else if(i>130 && i<141){
				assertTrue(multa.getPoints()==4);
			} else if(i>140 && i<151){
				assertTrue(multa.getPoints()==6);			
			} else {
				assertTrue(multa.getPoints()==6);
			}
			
			assertNotNull(multa.getDateOfReception());
		}
	}
	
	@Test
	public void testmaxSpeed90() {
		int ciudadAleatoria;
		for(int i = 91; i < 162; i++) {
			ciudadAleatoria = (int) ((Math.random()*(lugares.length-1)));
			int idExpediente = manager.openInquiry("0002", i, lugares[ciudadAleatoria], 90);
			Sanction multa = manager.identifyDriver(idExpediente, "5000002");
			manager.pay(multa);
			
			if (i>90 && i<121){
				assertTrue(multa.getPoints()==0);
			} else if(i>120 && i<141){
				assertTrue(multa.getPoints()==2);
			} else if(i>140 && i<151){
				assertTrue(multa.getPoints()==4);
			} else if(i>150 && i<161){
				assertTrue(multa.getPoints()==6);			
			} else {
				assertTrue(multa.getPoints()==6);
			}
			
			assertNotNull(multa.getDateOfReception());
		}
	}
	
	@Test
	public void testmaxSpeed100() {
		int ciudadAleatoria;
		for(int i = 101; i < 172; i++) {
			ciudadAleatoria = (int) ((Math.random()*(lugares.length-1)));
			int idExpediente = manager.openInquiry("0002", i, lugares[ciudadAleatoria], 100);
			Sanction multa = manager.identifyDriver(idExpediente, "5000002");
			manager.pay(multa.getId());
			
			if (i>100 && i<131){
				assertTrue(multa.getPoints()==0);
			} else if(i>130 && i<151){
				assertTrue(multa.getPoints()==2);
			} else if(i>150 && i<161){
				assertTrue(multa.getPoints()==4);
			} else if(i>160 && i<171){
				assertTrue(multa.getPoints()==6);			
			} else {
				assertTrue(multa.getPoints()==6);
			}
			
			assertNotNull(multa.getDateOfReception());
		}
	}
	
	@Test
	public void testmaxSpeed110() {
		int ciudadAleatoria;
		for(int i = 111; i < 182; i++) {
			ciudadAleatoria = (int) ((Math.random()*(lugares.length-1)));
			int idExpediente = manager.openInquiry("0002", i, lugares[ciudadAleatoria], 110);
			Sanction multa = manager.identifyDriver(idExpediente, "5000002");
			manager.pay(multa.getId());
			
			if (i>110 && i<141){
				assertTrue(multa.getPoints()==0);
			} else if(i>140 && i<161){
				assertTrue(multa.getPoints()==2);
			} else if(i>160 && i<171){
				assertTrue(multa.getPoints()==4);
			} else if(i>170 && i<181){
				assertTrue(multa.getPoints()==6);			
			} else {
				assertTrue(multa.getPoints()==6);
			}
			
			assertNotNull(multa.getDateOfReception());
		}
	}
	
	@Test
	public void testmaxSpeed120() {
		int ciudadAleatoria;
		for(int i = 121; i < 192; i++) {
			ciudadAleatoria = (int) ((Math.random()*(lugares.length-1)));
			int idExpediente = manager.openInquiry("0002", i, lugares[ciudadAleatoria], 120);
			Sanction multa = manager.identifyDriver(idExpediente, "5000002");
			manager.pay(multa.getId());
			
			if (i>120 && i<151){
				assertTrue(multa.getPoints()==0);
			} else if(i>150 && i<171){
				assertTrue(multa.getPoints()==2);
			} else if(i>170 && i<181){
				assertTrue(multa.getPoints()==4);
			} else if(i>180 && i<191){
				assertTrue(multa.getPoints()==6);			
			} else {
				assertTrue(multa.getPoints()==6);
			}
			
			assertNotNull(multa.getDateOfReception());
		}
	}
	
	@Test
	public void test_CambiarPropietario() {
		String dni = "5000002";
		String license = "0006";
		Manager m=Manager.get();
		m.changeOwner(license, dni);
		
		VehicleDao vd = new VehicleDao();
		Vehicle v = vd.findByLicense(license);
		OwnerDao od = new OwnerDao();
		Owner o = od.findByDni(dni);
		
		assertTrue(v.getOwner().getId()==o.getId());
	}
	
	@Test
	public void testDriver() {
		DriverDao driverDao = new DriverDao();
		Driver d = driverDao.findByDni("5000002");
		d.setPoints(10);
		d.setDni("5768932");
		
		assertTrue(d.getPoints()==10);
		assertTrue(d.getDni()=="5768932");
	}

	@Test
	public void testObtenerExpedientes() {
		List<Inquiry> list = manager.obtenerInquiry();
		assertNotNull(list);
	}
	
	@Test
	public void testObtenerSanciones() {
		List<Sanction> list = manager.obtenerSanction();
		assertNotNull(list);
	}
	
	@Test
	public void testObtenerID() {
		int idConductor = manager.obtenerId("5000002");
		int id = manager.obtenerId("5000008");
		assertNotNull(idConductor);
		assertNotNull(id);
	}
	
	@Test
	public void testEquals() {
		GeneralDao<Sanction> daoS = new GeneralDao<>();
		GeneralDao<SanctionHolder> daoSH = new GeneralDao<>();
		VehicleDao daoV = new VehicleDao();
		
		Sanction s = daoS.findById(Sanction.class, 59);
		Sanction t = daoS.findById(Sanction.class, 59);
		
		assertTrue(s.equals((Object)t));
		
		SanctionHolder sh1 = daoSH.findById(SanctionHolder.class, 1);
		SanctionHolder sh2 = daoSH.findById(SanctionHolder.class, 1);
		
		assertTrue(sh1.equals(sh2));
		
		Vehicle v1 = daoV.findByLicense("0001");
		Vehicle v2 = daoV.findByLicense("0001");		
		
		assertTrue(v1.equals(v2));
	}
	
	@Test
	public void testhasCode() {
		GeneralDao<Sanction> daoS = new GeneralDao<>();
		GeneralDao<SanctionHolder> daoSH = new GeneralDao<>();
		VehicleDao daoV = new VehicleDao();
		
		Sanction s = daoS.findById(Sanction.class, 59);
		assertNotNull(s.hashCode());
		
		SanctionHolder sh = daoSH.findById(SanctionHolder.class, 1);
		assertNotNull(sh.hashCode());
		
		Vehicle v = daoV.findByLicense("0001");
		assertNotNull(v.hashCode());
		
	}
	
	@Test
	public void testEqualsMismosObjetos() {
		GeneralDao<Sanction> daoS = new GeneralDao<>();
		GeneralDao<SanctionHolder> daoSH = new GeneralDao<>();
		VehicleDao daoV = new VehicleDao();
		
		Sanction s = daoS.findById(Sanction.class, 59);
		assertTrue(s.equals(s));
		
		SanctionHolder sh = daoSH.findById(SanctionHolder.class, 1);
		assertTrue(sh.equals(sh));
		
		Vehicle v = daoV.findByLicense("0001");
		assertTrue(v.equals(v));
	}
	
	@Test
	public void testEqualsDistintosObjetos() {
		GeneralDao<Sanction> daoS = new GeneralDao<>();
		GeneralDao<SanctionHolder> daoSH = new GeneralDao<>();
		VehicleDao daoV = new VehicleDao();
		
		Sanction s1 = daoS.findById(Sanction.class, 59);
		Sanction s2 = daoS.findById(Sanction.class, 66);
		assertFalse(s1.equals(s2));
		
		SanctionHolder sh1 = daoSH.findById(SanctionHolder.class, 1);
		SanctionHolder sh2 = daoSH.findById(SanctionHolder.class, 2);
		assertFalse(sh1.equals(sh2));
		
		Vehicle v1 = daoV.findByLicense("0001");
		Vehicle v2 = daoV.findByLicense("0002");		
		assertFalse(v1.equals(v2));
	}
	
	@Test
	public void testObjetosInexistentes() {
		GeneralDao<Sanction> daoS = new GeneralDao<>();
		GeneralDao<SanctionHolder> daoSH = new GeneralDao<>();
		VehicleDao daoV = new VehicleDao();
		
		Sanction s1 = daoS.findById(Sanction.class, 59);
		Sanction s2 = null;
		assertFalse(s1.equals(s2));
		
		SanctionHolder sh1 = daoSH.findById(SanctionHolder.class, 1);
		SanctionHolder sh2 = null;
		assertFalse(sh1.equals(sh2));
		
		Vehicle v1 = daoV.findByLicense("0001");
		Vehicle v2 = null;
		assertFalse(v1.equals(v2));
		Vehicle v3 = new Vehicle(null);		
		assertFalse(v1.equals(v3));
		assertFalse(v3.equals(v1));
		assertFalse(v3.equals(sh1));
	}
	
}