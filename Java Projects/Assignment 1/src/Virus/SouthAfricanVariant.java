package Virus;

import Population.Person;
import Population.Sick;
import Simulation.Clock;

public class SouthAfricanVariant implements IVirus{
	private final static double probKillUnder18 = 0.05;
	private final static double probKillAbove18 = 0.08;
	private final static double probContagUnder18 = 0.6;
	private final static double probContagAbove18 = 0.5;
	/**
     * this method calculate the probability the given person will be infected from this virus
     * @param p: the person that we want to calculate his probability to get sick 
     * @return the probability the given person will be infected from this virus
     */
	@Override
	public double contagionProbability(Person p) {
		int age = p.GetAge();
		if (age < 18) {
			return  SouthAfricanVariant.probContagUnder18 * p.contagionProbability();
		}
		else {
			return SouthAfricanVariant.probContagAbove18 * p.contagionProbability();
		}

	}
	/**
     * this method check if the first person (param a) can contagion the second person (param b) (if not already sick) 
     * by calculate  tryToContagionEquation
     * @param a the Sick person who try to contagion the second person
     * @param b the person who might be contagion
     * @return True if the person b contracted the virus 
     */
	@Override
	public boolean tryToContagion(Person a, Person b) {
		if(b instanceof Sick) {
			return false;
		}
		double prob = this.contagionProbability(b);		
		double distance = a.calculateDistance(b);
		double minVal = IVirus.tryToContagionEquation(distance);	
		if((prob * minVal) > Math.random()) {
			return true;			
		}
		
		return false;
	}
	/**
     * this method calculate the probability if the given person will die  
     * by calculate  see tryToKillEquation  
     * @param s the Sick person who might die
     * @return True if the person died other wise false 
     */
	@Override
	public boolean tryToKill(Sick s) {
		
		double rand;
		double prob;
		int age = s.GetAge();
		long timeElapsed = Clock.now() - s.getContagiousTime();
		if (age < 18) {
			prob = SouthAfricanVariant.probKillUnder18;
		}
		else {
			prob = SouthAfricanVariant.probKillAbove18;
		}
		double maxVal = IVirus.tryToKillEquation(prob, timeElapsed);
		
		rand = Math.random();
		if(rand < maxVal) {
			return true;
		}
		return false;
	}
	@Override
    public String toString() {
        return String.format("COVID19-South African Variant");
    }
}
