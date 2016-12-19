package edu.uclm.esi.iso2.multas.tests;
import static org.junit.Assert.*;

import java.io.IOException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

import edu.uclm.esi.iso2.multas.dao.DriverDao;
import edu.uclm.esi.iso2.multas.dao.GeneralDao;
import edu.uclm.esi.iso2.multas.dao.OwnerDao;
import edu.uclm.esi.iso2.multas.dao.VehicleDao;
import edu.uclm.esi.iso2.multas.domain.Driver;
import edu.uclm.esi.iso2.multas.domain.Inquiry;
import edu.uclm.esi.iso2.multas.domain.Manager;
import edu.uclm.esi.iso2.multas.domain.Owner;
import edu.uclm.esi.iso2.multas.domain.Sanction;
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
		}
	}
	
	@Test
	public void testmaxSpeed40() {
		int ciudadAleatoria;
		for(int i = 41; i < 92; i++) {
			ciudadAleatoria = (int) ((Math.random()*(lugares.length-1)));
			int idExpediente = manager.openInquiry("0002", i, lugares[ciudadAleatoria], 40);
			Sanction multa = manager.identifyDriver(idExpediente, "5000002");
			manager.pay(multa.getId());
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
			manager.pay(multa.getId());
			assertNotNull(multa.getDateOfReception());
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
			manager.pay(multa.getId());
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

}