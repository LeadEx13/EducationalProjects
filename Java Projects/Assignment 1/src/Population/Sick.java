package Population;

import Country.Settlement;
import Location.Point;
import Virus.IVirus;
import Simulation.Clock;

public class Sick extends Person{
	private long contagiousTime; // clock
	private IVirus virus;
	/**
	   * This method is constructor for class Sick.
	   * @param age the age of this person 
	   * @param Location the location of this person in the given settlement 
	   * @param settlement the settlement this person belong to
	   * @param virus the virus variant this person infected from 
	   * 
	*/
	public Sick(int age, Point locantion, Settlement settlement,IVirus virus) {
		super(age, locantion, settlement);
		this.virus = virus;
		this.contagiousTime = Clock.now();
	}
	
	/**
	   * This method is constructor for class Healthy.
	   * @param p the person we want to create as a Sick
	   * @param virus the virus variant this person infected from 
	   * 
	*/	
	public Sick(Person person, IVirus virus) throws Exception{
		super(person);
		if(person instanceof Sick) {
			throw new Exception("person is already sick");
		}
		this.virus = virus;
		this.contagiousTime = Clock.now();
	}
	/**
	   * Get the person's contagious time
	   * @return  person's contagiousTime
	   * 
	*/
	public long getContagiousTime()
	{
		return this.contagiousTime;
	}
	
	/**
	   * Set the person's contagious time
	   * @param contagiousTime: person's contagious Time
	   * 
	*/
	public void SetContagiousTime(long contagiousTime) {
		this.contagiousTime = contagiousTime;
	}
	/**
	   * This method calculate the virus-independent probability of infection
	   * @return 1 
	   * 
	*/
	@Override
	public double contagionProbability() {
		return 1;
	}
	
	/**
	   * This method change this person to convalescent and update the Database according it
	   * @return new object of this person's after change him to Convalescent  
	   * 
	*/
	public Person recover() {
		Person p = new Convalescent(this, this.virus);
		this.settlement.removePerson(this);
		this.settlement.addPerson(p);
		return p;
	}
	/**
	   * this method calculate the probability if this person will die from the virus
	   * if the probability is higher then random number the method will return true 
	   * @return true if this person died other wise false
	*/
	public boolean tryToDie() {
			return this.virus.tryToKill(this);
	}
	/**
	   * Get the virus this person infected from
	   * @return virus attribute
	   * 
	*/
	public IVirus getVirus() {
		return virus;
	}
	/**
	   * Set the virus this person infected from
	   * @param virus: the virus this person infected from
	   * 
	*/
	public void setVirus(IVirus virus) {
		this.virus = virus;
	}
	@Override
    public String toString() {
        return String.format(super.toString()+ ";contagiousTime = " + contagiousTime +";virus = " + virus);
    }
}
	