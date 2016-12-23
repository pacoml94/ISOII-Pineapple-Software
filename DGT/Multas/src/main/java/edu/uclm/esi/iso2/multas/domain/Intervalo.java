package edu.uclm.esi.iso2.multas.domain;

class Intervalo {

	private double speed, speedMax;
	private int points;
	private double ammount;
	
	public Intervalo(){}
	
	public Intervalo(double speed2, double maxSpeed2, int points, double ammount) {
		super();
		this.speed = speed2;
		this.speedMax = maxSpeed2;
		this.points = points;
		this.ammount = ammount;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public double getSpeedMax() {
		return speedMax;
	}

	public void setSpeedMax(int speedMax) {
		this.speedMax = speedMax;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public double getAmmount() {
		return ammount;
	}

	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}
}
