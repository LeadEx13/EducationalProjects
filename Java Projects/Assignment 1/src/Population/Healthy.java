package Population;

import Country.Settlement;
import Location.Point;
import Simulation.Clock;

public class Healthy extends Person {
	static final double SickProbability = 1;
	
	/**
	   * This method is constructor for class Healthy.
	   * @param p the person we want to create as a Healthy
	   * 
	*/
	public Healthy(Person a) {
		super(a);
	}
	/**
	   * This method is constructor for class Healthy.
	   * @param age the age of this person 
	   * @param Location the location of this person in the given settlement 
	   * @param settlement the settlement this person belong to
	   * 
	*/
	public Healthy(int age, Point location, Settlement settlement) {
		super(age, location, settlement);
		
	}
	/**
	   * This method calculate the virus-independent probability of infection
	   * @return the virus-independent probability of infection 
	   * 
	*/
	@Override
	public double contagionProbability() {
		return Healthy.SickProbability;
	}
	/**
	   * This method change this healthy person to be Vaccinated and update the DB accordingly 
	   * @return the Vaccinated healthy person 
	   * 
	*/
	public Person vaccinate() {
		Person p = new Vaccinated(this, Clock.now());
		this.settlement.removePerson(this);
		this.settlement.addPerson(p);
		return p;
	}
}
