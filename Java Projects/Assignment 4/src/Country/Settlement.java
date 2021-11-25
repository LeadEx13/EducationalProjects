package Country;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;

import IO.statisticsFile;

import java.util.List;
import java.util.Random;

import Location.Location;
import Location.Point;
import Population.Person;
import Population.Sick;
import Virus.BritishVariant;
import Virus.ChineseVariant;
import Virus.IVirus;
import Virus.SouthAfricanVariant;
import Population.Convalescent;
import Population.Healthy;
import Population.Vaccinated;
import Simulation.Clock;
import Country.Settlement;


public abstract class Settlement implements Runnable {	
	private Map map;
	private String name;
	private Location location;
	private List<Person> healthy_people;
	private List<Person> sick_people;
	private List<Person> people;
	private int capacity;
	private int dead = 0;
	private int vaccine_doses = 0;
	private List<Settlement> neighbors;
	protected RamzorColor ramzorColor;
	private static final double initialcontagion = 0.02;
	private static final double sample_sickPeople = 0.2;
	private static final int num_of_trys_to_contagion = 3;
	private static double num_of_dead_percent = 0.05;


	public Settlement(String name, Location location, int population, int capacity) {
		/**
		   * This method is constructor for class Settlement.
		   * @param name the name of this settlement 
		   * @param Location the borders of this settlement 
		   * @param Population of the settlement
		   * @param Capacity of the settlement
		   * 
		*/
		this.map=null;
		this.name = name;
		this.location = new Location(location.getPosition(), location.getSize());
		this.ramzorColor = RamzorColor.GREEN;
		this.healthy_people = new ArrayList<Person>(population);
		this.sick_people = new ArrayList<Person>();
		this.neighbors = new ArrayList<Settlement>();
		this.capacity = capacity;
	}	
	public void setMapPointer(Map world){
		this.map=world;
	}
	public void addDead() {
		/**
		 * add dead
		 */
		this.dead++;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.InitialSimulation();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}	
		while (!map.isStop()) {
			synchronized (map) {
				while (map.isPause()) {
					try {
						map.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
			try {
				this.Simulation(map);
				if (this.getdead() >= this.getPopulation() * num_of_dead_percent&&statisticsFile.path != null) {
					statisticsFile.writeLog(this);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			try {
				map.getcycle().await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}

	public void InitialSimulation() throws Exception {
		Random rand = new Random();
		IVirus virus = null;
		int x1 = rand.nextInt();
		if (x1 % 3 == 0)
			virus = new BritishVariant();
		else if (x1 % 3 == 1)
			virus = new ChineseVariant();
		else
			virus = new SouthAfricanVariant();
		double numContagion = 0;// array for number of people that contagion in step 2
		numContagion = this.getPopulation() * initialcontagion;
		for (int j = 0; j < numContagion; j++) {
			if (this.gethealthy_people().size() != 0)
				if (this.gethealthy_people().get(0).contagion(virus) instanceof Sick) {
					this.getsick_people().add(this.gethealthy_people().get(0).contagion(virus));
					this.gethealthy_people().remove(0);
				}
		}
	}
	private synchronized  void tryTotransfer() {
		
		Random rand = new Random();
		Person p = null;
		Object o1, o2;
		int hash1 = System.identityHashCode(this);

		for (int transfer = 0; transfer < this.getPopulation() * 0.03; transfer++) {
			p = this.getPerson();
			if (p != null) {
				int settl = rand.nextInt(this.getneighbors().size());
				int hash2 = System.identityHashCode(this.getneighbors().get(settl));
				if (Math.max(hash1, hash2) == hash1) {
					o1 = this;
					o2 = this.getneighbors().get(settl);
				} else {
					o2 = this;
					o1 = this.getneighbors().get(settl);
				}
				synchronized (o1) {
					synchronized (o2) {
						this.transferPerson(p, this.getneighbors().get(settl));
					}
				}
			}
		}
	}
	public synchronized  void tryToRecover(){ 	
		for (int k = 0; k < this.getsick_people().size(); k++) {
			Sick s = (Sick) this.getsick_people().get(k);
			if (s!=null){
				if (Clock.CalcDays(s.getContagiousTime()) > 25) {
					this.gethealthy_people().add(s.recover());
					this.getsick_people().remove(k);
				}
			}
		}
	}
	
	public synchronized void tryTokill(){
		/**
		 * try to kill
		 */
		if( this.getsick_people().size() != 0){
			for (int k = 0; k < this.getsick_people().size(); k++) {
				Sick s = (Sick) this.getsick_people().get(k);
				if(s != null){
					if (s.tryToDie()) {
						this.getsick_people().remove(s);
						this.addDead();
					}
				}
			}
		}
		if (this.getdead() >= this.getPopulation() * 0.01 && statisticsFile.path != null) {
			statisticsFile.writeLog(this);
		}
	
	}
	public synchronized void tryToVacinate(){
		int count_doses = 0;
		for (int vaccine_doses = 0; vaccine_doses < this.getVaccine_doses(); vaccine_doses++) {
			for (int healthy = 0; healthy < this.gethealthy_people().size(); healthy++) {
				if (this.gethealthy_people().get(healthy) instanceof Healthy) {
					Healthy h = (Healthy) this.gethealthy_people().get(healthy);
					this.gethealthy_people().set(healthy, h.vaccinate());
					count_doses++;
					break;
				}
			}
		}
		this.reduce_vaccine_doses(count_doses);
	}
	public void Simulation(Map world) throws Exception {
		this.simulationContagion();
		this.tryTokill();
		this.tryToRecover();
		this.tryTotransfer();
		this.tryToVacinate();
		this.setRamzorColor(this.calculateRamzorGrade());

	}
public synchronized void addSick(IVirus virus, int x) {
		this.getsick_people().add(this.gethealthy_people().get(x).contagion(virus));
		this.gethealthy_people().remove(x);
	}
	public abstract RamzorColor calculateRamzorGrade() ;
	public double contagiousPercent() {
		int sick_people = this.getsick_people().size();
		int population = this.getPopulation();
		double percent = (double) sick_people / population;
		return percent;
	}
	public synchronized  void simulationContagion() throws Exception {
		double numSick = this.getsick_people().size() * sample_sickPeople;
		Random rand = new Random();
		for (int i = 0; i < numSick; i++) {
			boolean flag = false;
			if (this.getsick_people().size() > 1) {
				int x = rand.nextInt(this.getsick_people().size() - 1);
				Sick s = (Sick) this.getsick_people().get(x);
				if(s!=null){
					IVirus virus = s.getVirus();
					for (int j = 0; j < num_of_trys_to_contagion; j++) {
						if (this.gethealthy_people().size() != 0) {
							int y = 0;
							if (this.gethealthy_people().size() > 0) {
								y = rand.nextInt(this.gethealthy_people().size());
								if (this.gethealthy_people().get(y) != null) {
									flag = virus.tryToContagion(s, this.gethealthy_people().get(y));
								}
							}
							if (flag) {
								if (this.gethealthy_people().get(y).contagion(virus) instanceof Sick) {
									this.getsick_people().add(this.gethealthy_people().get(y).contagion(virus));
									this.gethealthy_people().remove(y);
								}
							}
						}
					}
				}
			}
		}
	}
	public Point randomLocation() {
		Random random = new Random();
        int x= random.nextInt(this.location.getSize().getWidth());
        int y= random.nextInt(this.location.getSize().getHeight());
        return new Point(this.location.getPosition().getX() + x,this.location.getPosition().getY() - y);
	}
	public boolean removePerson(Person p) {
		return this.people.remove(p);		 
	}
	public boolean AddPerson(Person p) {
		if (this.getPopulation() < this.getCapacity()) {
			if (p instanceof Healthy || p instanceof Vaccinated || p instanceof Convalescent) {
				this.healthy_people.add(p);
			} else {
				this.sick_people.add(p);
			}
			return true;
		}
		return false;
	}
	private boolean transferPropabillity(double p1, double p2) {
		double chance = p1 * p2;
		return chance >= Math.random();
	}
    public boolean transferPerson(Person p,Settlement s) {
    	double p1 = this.getRamzorColor().getVal();
		double p2 = s.getRamzorColor().getVal();

		if (transferPropabillity(p1, p2)) {
			if (s.AddPerson(p)) {
				if (p instanceof Sick) {
					this.sick_people.remove(p);
				} else {
					this.healthy_people.remove(p);
				}
				return true;
			}
		}
		return false;
    }	
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location l) {
		this.location = l;
	}
	public List<Person> getPeople() {
		return this.people;
	}
	public void setPeople(List<Person> people) {
		this.people = people;
	}
	private synchronized Person getPerson() {
		Random rand = new Random();
		if (this.getneighbors().size() == 0)
			return null;
		List<Person> population = new ArrayList<Person>(this.getPopulation());
		population.addAll(this.gethealthy_people());
		population.addAll(this.getsick_people());
		int people = rand.nextInt(population.size() - 1);
		return population.get(people);
	}
	public List<Person> gethealthy_people() {
		return this.healthy_people;
	}
	public List<Person> getsick_people() {
		return this.sick_people;
	}
	public List<Settlement> getneighbors() {
		return this.neighbors;
	}
	public int getdead() {
		return this.dead;
	}
	public int getCapacity() {
		return capacity;
	}
	public RamzorColor getRamzorColor() {
		return this.ramzorColor;
	}
	public void setRamzorColor(RamzorColor new_color) {
		this.ramzorColor = new_color;
	}
	public int getVaccine_doses() {
		return vaccine_doses;
	}
	public synchronized void add_vaccine_doses(int douses) {
		this.vaccine_doses += douses;
	}
	public void reduce_vaccine_doses(int douses) {
		this.vaccine_doses -= douses;
	}
	public int getPopulation() {
		int population = this.healthy_people.size() + this.sick_people.size();
		return population;
	}
	@Override
    public String toString() {
        return String.format("name = " + name + ";location=(" + location+");Citizen= " + people.size() +";RamzorColor=" +ramzorColor +"; Sick precentage = " +this.contagiousPercent()*100);
    }
}
