package Population;

import Country.Settlement;
import Location.Point;
import Simulation.Clock;

public class Healthy extends Person {
	/**
	   * This method is constructor for class Healthy.
	   * @param p the person we want to create as a Healthy
	   * 
	*/
	static final double SickProbability = 1;
	public Healthy(Person a) {
		super(a);
	}
	public Healthy(int age, Point location, Settlement settlement) {
		super(age, location, settlement);	
	}
	@Override
	public double contagionProbability() {
		return Healthy.SickProbability;
	}
	public Person vaccinate() {
		Person p = new Vaccinated(this.GetAge(),this.GetLocation(),this.GetSettlement(),Clock.now());
		return p;
	}
}
