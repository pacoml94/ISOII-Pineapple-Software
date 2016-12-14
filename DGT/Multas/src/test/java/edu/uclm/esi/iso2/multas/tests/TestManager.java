package edu.uclm.esi.iso2.multas.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.uclm.esi.iso2.multas.domain.Manager;
import edu.uclm.esi.iso2.multas.domain.Sanction;

public class TestManager {

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
		//assertTrue();
	}
}
