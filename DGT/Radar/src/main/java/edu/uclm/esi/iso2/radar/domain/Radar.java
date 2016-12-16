package edu.uclm.esi.iso2.radar.domain;

import edu.uclm.esi.iso2.multas.domain.Manager;
import edu.uclm.esi.iso2.multas.domain.Sanction;

public class Radar {
	private Manager manager = Manager.get();
	private int velocidad, velocidad_max;
	private String ciudadExp, licencia;
	private String [] ciudad = {"Madrid", "Barcelona", "Bilbao", "A Coruña", "Ciudad Real"};
	private int [] vel_max = {50, 60, 70, 80, 90, 100, 110, 120};
	
	public Radar(){
		
	}
	
	public void crearExpediente(){
		//Rango de multa de 130 a 200 km/h
		velocidad = (int) (Math.random()*(200-130+1)+130);
		//Rango de velocidad max de 80 a 120
		velocidad_max = vel_max[(int) (Math.random()*(vel_max.length)-1)];
		//Rango de matricula 
		licencia= calcularLicencia();
		//Rango ciudades
		ciudadExp = ciudad[(int) (Math.random()*(ciudad.length-1))];
		System.out.println(licencia+" "+velocidad+" "+velocidad_max+" "+ciudadExp);
		
		//int idExpediente=manager.openInquiry(licencia, velocidad, ciudadExp, velocidad_max);
	}
	
	public String calcularLicencia(){
		//String num = Integer.toString((int) (Math.random()*(3217-1+1)+1));
		String l = null;
		int azar = (int) (Math.random()*(3217-1+1)+1);
		
		if (azar < 10) {
			l = "000"+String.valueOf(azar);
		} else if (azar < 100) {
			l = "00"+String.valueOf(azar);
		} else if (azar < 1000) {
			l = "0"+String.valueOf(azar);
		} else {
			l = String.valueOf(azar);
		}
		return l;
	}

}
