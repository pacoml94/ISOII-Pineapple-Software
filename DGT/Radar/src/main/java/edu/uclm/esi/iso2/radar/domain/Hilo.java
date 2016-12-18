package edu.uclm.esi.iso2.radar.domain;

public class Hilo extends Thread{
	private boolean funcionando=true;
	Radar radar = new Radar();
	
	public void detener(){
		funcionando = false;
	}
	
	public void run(){
		int cont = 1;
		while(funcionando){
			radar.crearExpediente();
			try{
				Thread.sleep(2000);
			}catch(InterruptedException ex){
				Thread.currentThread().interrupt();
			}
			cont++;
		}
	}
}
