package edu.uclm.esi.iso2.multas.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.uclm.esi.iso2.multas.dao.GeneralDao;
import edu.uclm.esi.iso2.multas.dao.VehicleDao;
import edu.uclm.esi.iso2.multas.domain.Sanction;
import edu.uclm.esi.iso2.multas.domain.SanctionHolder;
import edu.uclm.esi.iso2.multas.domain.Vehicle;

public class Test1 {

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
