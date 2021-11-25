package Population;

import Country.Settlement;
import Location.Point;
import Virus.IVirus;

public class Convalescent extends Person{
	/**
	   * This method is constructor for class Convalescent.
	   * @param p the person we want to create as a Convalescent
	   * @param virus the virus this person healed from 
	   * 
	*/
	static final double SickProbability = 0.2;
	private IVirus virus;
	
	public Convalescent(Person a,IVirus virus) {
		super(a);
		this.virus = virus;
	}
	public Convalescent(int age, Point location, Settlement settlement, IVirus virus) {
		super(age, location, settlement);
		this.virus = virus;
	}
	@Override
	public double contagionProbability() {
		return Convalescent.SickProbability;
	}
	public IVirus getVirus() {
		return virus;
	}
	public void setVirus(IVirus virus) {
		this.virus = virus;
	}
	@Override
    public String toString() {
        return String.format(super.toString()+ ";healed from = " + virus);
    }
}
