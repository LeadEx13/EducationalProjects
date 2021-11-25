package Population;

import Country.Settlement;
import Location.Point;
import Virus.IVirus;

public class Convalescent extends Person{
	static final double SickProbability = 0.2;
	private IVirus virus;
	
	/**
	   * This method is constructor for class Convalescent.
	   * @param p the person we want to create as a Convalescent
	   * @param virus the virus this person healed from 
	   * 
	*/
	public Convalescent(Person a,IVirus virus) {
		super(a);
		this.virus = virus;
	}
	/**
	   * This method is constructor for class Convalescent.
	   * @param age the age of this person 
	   * @param Location the location of this person in the given settlement 
	   * @param settlement the settlement this person belong to
	   * @param virus the virus this person healed from 
	   * 
	*/
	public Convalescent(int age, Point location, Settlement settlement, IVirus virus) {
		super(age, location, settlement);
		this.virus = virus;
		
	}
	@Override
	public double contagionProbability() {
		return Convalescent.SickProbability;
	}
	/**
	   * Get the virus this person healed from
	   * @return virus attribute
	   * 
	*/
	public IVirus getVirus() {
		return virus;
	}
	/**
	   * Set the virus this person healed from
	   * @param virus: the virus this person healed from
	   * 
	*/
	public void setVirus(IVirus virus) {
		this.virus = virus;
	}
	@Override
    public String toString() {
        return String.format(super.toString()+ ";healed from = " + virus);
    }

}
