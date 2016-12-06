package edu.uclm.esi.iso2.Radar;

class Radar {
	
	private boolean arrancar;
	
	public Radar(boolean btnArrancar) {
		this.arrancar = btnArrancar;
	}

	public boolean isArrancar() {
		return arrancar;
	}

	public void setArrancar(boolean arrancar) {
		this.arrancar = arrancar;
	}

}
