package Population;

import Simulation.Clock;
import Country.Settlement;
import Location.Point;

public class Vaccinated extends Person {
	
	private long vaccinationTime;
	/**
	   * This method is constructor for class Vaccinated.
	   * @param age the age of this person 
	   * @param Location the location of this person in the given settlement 
	   * @param settlement the settlement this person belong to
	   * @param vaccinationTime the time this person got the vaccine 
	   * 
	*/
	public Vaccinated(int age, Point locantion, Settlement settlement, long vaccinationTime) {
		super(age, locantion, settlement);
		this.vaccinationTime = vaccinationTime;
		
	}
	/**
	   * This method is constructor for class Vaccinated.
	   * @param p the person we want to create as a Vaccinated
	   * @param vaccinationTime the time this person got the vaccine 
	   * 
	*/
	public Vaccinated(Person p, long vaccinationTime) {
		super(p);
		this.vaccinationTime = vaccinationTime;
	}
	/**
	   * This method calculate the virus-independent probability of infection
	   * @return the virus-independent probability of infection 
	   * 
	*/
	@Override
	public double contagionProbability() {
		double probabilty;
		long timeElapsed = Clock.now() - this.vaccinationTime; 
		if (timeElapsed < 21)
		{
			probabilty = Math.min(1, 0.56 + 0.15 * Math.sqrt(21-timeElapsed));
		} else {
			probabilty = Math.max(0, 1.05/ (timeElapsed - 14));
		}
		return probabilty;
	}
	/**
	   * Get the person's vaccination Time
	   * @return  person's vaccinationTime
	   * 
	*/
	public long GetVaccinationTime() {
		return vaccinationTime;
	}
	/**
	   * Set the person's vaccination Time
	   * @param vaccinationTime:person's vaccinationTime
	   * 
	*/
	public void SetVaccinationTime(long vaccinationTime) { // for next dose
		this.vaccinationTime = vaccinationTime;
	}
	@Override
    public String toString() {
        return String.format(super.toString()+ ";vaccinationTime = " + vaccinationTime);
    }

}
