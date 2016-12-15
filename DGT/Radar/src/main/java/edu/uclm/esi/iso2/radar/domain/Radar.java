package edu.uclm.esi.iso2.radar.domain;

import edu.uclm.esi.iso2.multas.domain.Manager;
import edu.uclm.esi.iso2.multas.domain.Sanction;

public class Radar {
	private boolean funcionando;
	private Manager manager = Manager.get();
	private int velocidad, velocidad_max, indiceCiudad;
	private String [] ciudad = {"Madrid", "Barcelona", "Bilbao", "A Coruña", "Ciudad Real"};
	private String ciudadExp, licencia;
	
	public Radar(){
		funcionando=true;
	}
	
	public void crearExpediente(){
		//Rango de multa de 130 a 200 km/h
		velocidad = (int) (Math.random()*(200-130+1)+130);
		//Rango de velocidad max de 80 a 120
		velocidad_max = (int) (Math.random()*(120-80+1)+80);
		//Rango de matricula 
		licencia = Double.toString(Math.random()*(3217-1+1)+1);
		//Rango ciudades
		indiceCiudad = (int) (Math.random()*(ciudad.length-1));
		ciudadExp = ciudad[indiceCiudad];
		System.out.println(licencia+" "+velocidad+" "+velocidad_max+" "+ciudadExp);
		
		int idExpediente=manager.openInquiry(licencia, velocidad, ciudadExp, velocidad_max);
	}

	public boolean isFuncionando() {
		return funcionando;
	}

	public void setFuncionando(boolean funcionando) {
		this.funcionando = funcionando;
	}
}
