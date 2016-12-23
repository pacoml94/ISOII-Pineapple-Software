package edu.uclm.esi.iso2.multas.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import edu.uclm.esi.iso2.multas.dao.DriverDao;
import edu.uclm.esi.iso2.multas.dao.GeneralDao;
import edu.uclm.esi.iso2.multas.dao.OwnerDao;

@Entity
@Table
public class Inquiry {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable=false, updatable=false)
	private Date dateOfIssue;
	@Column(nullable=false, updatable=false)
	private String location;
	@ManyToOne(targetEntity=Owner.class)
	private Owner owner;
	@Column(nullable=false, updatable=false)
	private double speed;
	@Column(nullable=false, updatable=false)
	private double maxSpeed;
	@OneToOne(fetch = FetchType.LAZY, targetEntity=Sanction.class, cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Sanction sanction;
	
	public Inquiry() {
		
	}
	
	public Inquiry(String license, double speed, String location, double maxSpeed) {
		this();
		this.dateOfIssue=new Date(System.currentTimeMillis());
		this.speed=speed;
		this.maxSpeed=maxSpeed;
		this.location=location;
		this.owner=findOwner(license);
	}

	private Owner findOwner(String license) {
		OwnerDao dao=new OwnerDao();
		return dao.findByLicense(license);
	}

	public Sanction createSanctionFor(String dni) {
		int points=calcularAmountAndPoints(maxSpeed, speed);
		int amount=calculateAmount();
		Sanction sanction=new Sanction();
		/**************************************/
		//Estamos creando una sanción pero no con el objeto sanction de la clase por
		//lo que la sancuón nunca va a existir
		this.sanction = sanction;
		/*************************************/
		DriverDao dao=new DriverDao();
		Driver driver=dao.findByDni(dni);
		sanction.setSanctionHolder(driver);
		sanction.setPoints(points);
		sanction.setAmount(amount);
		GeneralDao<Sanction> daoSanction=new GeneralDao<>();
		daoSanction.insert(sanction);
		return this.sanction;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	private int calculatePoints() {
		if (maxSpeed==30) {
			if (speed>=31 && speed<=50) 
				return 0;
			else if (speed>=51 && speed<=60)
				return 2;
			else if (speed>=61 && speed<=70)
				return 4;
			else if (speed>=71)
				return 6;
		} else if (maxSpeed==40) {
			if (speed>=41 && speed<=60) 
				return 0;
			else if (speed>=61 && speed<=70)
				return 2;
			else if (speed>=71 && speed<=80)
				return 4;
			else if (speed>=81)
				return 6;
		/**
		 * Se ha añadido la comprobación de la velocidad 50
		 */
		} else if (maxSpeed==50) {
			if (speed>=51 && speed<=70) 
				return 0;
			else if (speed>=71 && speed<=80)
				return 2;
			else if (speed>=81 && speed<=90)
				return 4;
			else if (speed>=91)
				return 6;
		/************************************************************/
		} else if (maxSpeed==60) {
			if (speed>=61 && speed<=90) 
				return 0;
			else if (speed>=91 && speed<=110)
				return 2;
			else if (speed>=111 && speed<=120)
				return 4;
			else if (speed>=121)
				return 6;
		} else if (maxSpeed==70) {
			if (speed>=71 && speed<=100) 
				return 0;
			else if (speed>=101 && speed<=120)
				return 2;
			else if (speed>=121 && speed<=130)
				return 4;
			else if (speed>=131)
				return 6;
		} else if (maxSpeed==80) {
			if (speed>=81 && speed<=110) 
				return 0;
			else if (speed>=111 && speed<=130)
				return 2;
			else if (speed>=131 && speed<=140)
				return 4;
			else if (speed>=141)
				return 6;
		} else if (maxSpeed==90) {
			if (speed>=91 && speed<=120) 
				return 0;
			else if (speed>=121 && speed<=140)
				return 2;
			else if (speed>=141 && speed<=150)
				return 4;
			else if (speed>=151)
				return 6;
		} else if (maxSpeed==100) {
			if (speed>=101 && speed<=130) 
				return 0;
			else if (speed>=131 && speed<=150)
				return 2;
			else if (speed>=151 && speed<=160)
				return 4;
			else if (speed>=161)
				return 6;
		} else if (maxSpeed==110) {
			if (speed>=111 && speed<=140) 
				return 0;
			else if (speed>=141 && speed<=160)
				return 2;
			else if (speed>=161 && speed<=170)
				return 4;
			else if (speed>=171)
				return 6;
		} else if (maxSpeed==120) {
			if (speed>=121 && speed<=150) 
				return 0;
			else if (speed>=151 && speed<=170)
				return 2;
			else if (speed>=171 && speed<=180)
				return 4;
			else if (speed>=181)
				return 6;
		}
		return 0;
	}
	
	private int calculateAmount() {
		if (maxSpeed==30) {
			if (speed>=31 && speed<=50) 
				return 100;
			else if (speed>=51 && speed<=60)
				return 300;
			else if (speed>=61 && speed<=70)
				return 400;
			else if (speed>=71 && speed<=80)
				return 500;
			else
				return 6;
		} else if (maxSpeed==40) {
			if (speed>=41 && speed<=60) 
				return 100;
			else if (speed>=61 && speed<=70)
				return 300;
			else if (speed>=71 && speed<=80)
				return 400;
			else if (speed>=81 && speed<=90)
				return 500;
			else
				return 600;
		} else if (maxSpeed==60) {
			if (speed>=61 && speed<=90) 
				return 100;
			else if (speed>=91 && speed<=110)
				return 300;
			else if (speed>=111 && speed<=120)
				return 400;
			else if (speed>=121 && speed<=130)
				return 500;
			else 
				return 600;
		} else if (maxSpeed==70) {
			if (speed>=71 && speed<=100) 
				return 100;
			else if (speed>=101 && speed<=120)
				return 300;
			else if (speed>=121 && speed<=130)
				return 400;
			else if (speed>=131 && speed<=140)
				return 500;
			else
				return 600;
		} else if (maxSpeed==80) {
			if (speed>=81 && speed<=110) 
				return 100;
			else if (speed>=111 && speed<=130)
				return 300;
			else if (speed>=131 && speed<=140)
				return 400;
			else if (speed>=141 && speed<=150)
				return 500;
			else
				return 600;
		} else if (maxSpeed==90) {
			if (speed>=91 && speed<=120) 
				return 100;
			else if (speed>=121 && speed<=140)
				return 300;
			else if (speed>=141 && speed<=150)
				return 400;
			else if (speed>=151 && speed<=160)
				return 500;
			else
				return 600;
		} else if (maxSpeed==100) {
			if (speed>=101 && speed<=130) 
				return 100;
			else if (speed>=131 && speed<=150)
				return 300;
			else if (speed>=151 && speed<=160)
				return 400;
			else if (speed>=161 && speed<=170)
				return 500;
			else
				return 600;
		} else if (maxSpeed==110) {
			if (speed>=111 && speed<=140) 
				return 100;
			else if (speed>=141 && speed<=160)
				return 300;
			else if (speed>=161 && speed<=170)
				return 400;
			else if (speed>=171 && speed<=180)
				return 500;
			else
				return 600;
		} else if (maxSpeed==120) {
			if (speed>=121 && speed<=150) 
				return 100;
			else if (speed>=151 && speed<=170)
				return 300;
			else if (speed>=171 && speed<=180)
				return 400;
			else if (speed>=181 && speed<=190)
				return 500;
			else return 600;
		}
		return 0;
	}
	
	private int calcularAmountAndPoints (double maxSpeed2, double speed2) {
		List<Intervalo> intervalos = new ArrayList<>();
		intervalos = construirIntervalo(speed2, maxSpeed2);
		
		for (int i = 0; i < intervalos.size(); i++) {

			if (intervalos.get(i).getSpeed()<=speed2 /*&& intervalos.get(1).getSpeed()>=speed2*/) {
				int devuelto = intervalos.get(i).getPoints();
				return devuelto;
			}
		}
		return 0;
	}
	
	private List <Intervalo> construirIntervalo(double speed2, double maxSpeed2) {
		
		List<Intervalo> intervalos = new ArrayList<>();
		
		switch ((int) maxSpeed2) {
		case 30:
			if (speed2 >= 31 && speed2 <= 50) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 0, 100));
			} else if (speed2 >= 51 && speed2 <= 60) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 2, 300));
			} else if (speed2 >= 61 && speed2 <= 70) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 4, 400));
			} else if (speed2 >= 71 && speed2 <= 80) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 6, 500));
			} else if (speed2 > 80) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 6, 600));				
			}
			break;
		case 40:
			if (speed2 >= 41 && speed2 <= 60) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 0, 100));
			} else if (speed2 >= 61 && speed2 <= 70) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 2, 300));
			} else if (speed2 >= 81 && speed2 <= 90) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 4, 400));
			} else if (speed2 >= 91 && speed2 <= 100) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 6, 500));
			} else if (speed2 > 100) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 6, 600));				
			}
			break;
		case 50:
			if (speed2 >= 51 && speed2 <= 70) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 0, 100));
			} else if (speed2 >= 71 && speed2 <= 80) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 2, 300));
			} else if (speed2 >= 81 && speed2 <= 90) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 4, 400));
			} else if (speed2 >= 91 && speed2 <= 100) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 6, 500));
			} else if (speed2 > 101) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 6, 600));				
			}
			break;
		case 60:
			if (speed2 >= 61 && speed2 <= 90) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 0, 100));
			} else if (speed2 >= 91 && speed2 <= 110) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 2, 300));
			} else if (speed2 >= 111 && speed2 <= 120) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 4, 400));
			} else if (speed2 >= 121 && speed2 <= 130) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 6, 500));
			} else if (speed2 > 131) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 6, 600));				
			}
			break;
		case 70:
			if (speed2 >= 71 && speed2 <= 100) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 0, 100));
			} else if (speed2 >= 101 && speed2 <= 120) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 2, 300));
			} else if (speed2 >= 121 && speed2 <= 130) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 4, 400));
			} else if (speed2 >= 131 && speed2 <= 140) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 6, 500));
			} else if (speed2 > 140) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 6, 600));				
			}
			break;
		case 80:
			if (speed2 >= 81 && speed2 <= 110) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 0, 100));
			} else if (speed2 >= 111 && speed2 <= 130) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 2, 300));
			} else if (speed2 >= 131 && speed2 <= 140) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 4, 400));
			} else if (speed2 >= 141 && speed2 <= 150) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 6, 500));
			} else if (speed2 > 150) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 6, 600));				
			}
			break;
		case 90:
			if (speed2 >= 91 && speed2 <= 120) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 0, 100));
			} else if (speed2 >= 121 && speed2 <= 140) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 2, 300));
			} else if (speed2 >= 141 && speed2 <= 150) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 4, 400));
			} else if (speed2 >= 151 && speed2 <= 160) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 6, 500));
			} else if (speed2 > 160) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 6, 600));				
			}
			break;
		case 100:
			if (speed2 >= 101 && speed2 <= 130) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 0, 100));
			} else if (speed2 >= 131 && speed2 <= 150) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 2, 300));
			} else if (speed2 >= 151 && speed2 <= 160) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 4, 400));
			} else if (speed2 >= 161 && speed2 <= 170) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 6, 500));
			} else if (speed2 > 170) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 6, 600));				
			}
			break;
		case 110:
			if (speed2 >= 111 && speed2 <= 140) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 0, 100));
			} else if (speed2 >= 141 && speed2 <= 160) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 2, 300));
			} else if (speed2 >= 161 && speed2 <= 170) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 4, 400));
			} else if (speed2 >= 171 && speed2 <= 180) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 6, 500));
			} else if (speed2 > 180) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 6, 600));				
			}
			break;
		case 120:
			if (speed2 >= 121 && speed2 <= 150) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 0, 100));
			} else if (speed2 >= 151 && speed2 <= 170) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 2, 300));
			} else if (speed2 >= 171 && speed2 <= 180) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 4, 400));
			} else if (speed2 >= 181 && speed2 <= 190) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 6, 500));
			} else if (speed2 > 190) {
				intervalos.add(new Intervalo(speed2, maxSpeed2, 6, 600));				
			}
			break;
		}
		
		return intervalos;
	}
}
