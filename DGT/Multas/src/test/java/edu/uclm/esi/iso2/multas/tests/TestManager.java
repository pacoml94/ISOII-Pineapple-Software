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

   /* @Test
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
    
	@Test
	public void test140_120() {
		Manager m=Manager.get();
		int idExpediente=m.openInquiry("0001", 140, "La Ronda", 120);
		Sanction multa=m.identifyDriver(idExpediente, "5000002");
		
		m.pay(multa);
		assertNotNull(multa.getDateOfPayment());
		assertTrue(multa.getAmount()==100);
		assertTrue(multa.getPoints()==0);
	}

	@Test
	public void test160_120() {
		Manager m=Manager.get();
		int idExpediente=m.openInquiry("0001", 160, "La Ronda", 120);
		Sanction multa=m.identifyDriver(idExpediente, "5000002");
		
		m.pay(multa);
		assertNotNull(multa.getDateOfPayment());
		assertTrue(multa.getAmount()==300);
		assertTrue(multa.getPoints()==2);
	}
	
	@Test
	public void test150_80() {
		Manager m=Manager.get();
		int idExpediente=m.openInquiry("0005", 150, "Bilbao", 80);
		Sanction multa=m.identifyDriver(idExpediente, "5000002");
		
		m.pay(multa);
		assertNotNull(multa.getDateOfPayment());
		assertTrue(multa.getAmount()==500);
		assertTrue(multa.getPoints()==6);
	}
	
	@Test
	public void test95_60() {
		Manager m=Manager.get();
		int idExpediente=m.openInquiry("0010", 95, "Madrid", 60);
		Sanction multa=m.identifyDriver(idExpediente, "5000000");
		
		m.pay(multa);
		assertNotNull(multa.getDateOfPayment());
		assertTrue(multa.getAmount()==300);
		assertTrue(multa.getPoints()==2);
	}
	
	@Test
	public void test105_70() {
		Manager m=Manager.get();
		int idExpediente=m.openInquiry("0006", 105, "Sevilla", 70);
		Sanction multa=m.identifyDriver(idExpediente, "5000002");
		
		m.pay(multa);
		assertNotNull(multa.getDateOfPayment());
		assertTrue(multa.getAmount()==300);
		assertTrue(multa.getPoints()==2);
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