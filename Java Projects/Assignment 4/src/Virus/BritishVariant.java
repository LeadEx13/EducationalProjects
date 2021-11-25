package Virus;

import Population.Person;
import Population.Sick;
import Simulation.Clock;

public class BritishVariant implements IVirus{
	
	private final static double probKillUnder18 = 0.01;
	private final static double probKillAbove18 = 0.1;
	private final static double probContag = 0.7;
	@Override
	public double contagionProbability(Person p) {
		/**
	     * this method calculate the probability the given person will be infected from this virus
	     * @param p: the person that we want to calculate his probability to get sick 
	     * @return the probability the given person will be infected from this virus
	     */
		return BritishVariant.probContag * p.contagionProbability();
	}
	@Override
	public boolean tryToContagion(Person a, Person b) {
		/**
	     * this method check if the first person (param a) can contagion the second person (param b) (if not already sick) 
	     * by calculate  tryToContagionEquation
	     * @param a the Sick person who try to contagion the second person
	     * @param b the person who might be contagion
	     * @return True if the person b contracted the virus 
	     */
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
	@Override
	public boolean tryToKill(Sick s) {
		/**
	     * this method calculate the probability if the given person will die  
	     * by calculate  see tryToKillEquation  
	     * @param s the Sick person who might die
	     * @return True if the person died other wise false 
	     */
		double rand;
		double prob;
		int age = s.GetAge();
		long time=Math.abs(s.getContagiousTime()-Clock.now());
		if (age < 18) {
			prob = BritishVariant.probKillUnder18;
		}
		else {
			prob = BritishVariant.probKillAbove18;
		}
		double maxVal = IVirus.tryToKillEquation(prob, time);
		
		rand = Math.random();
		if(rand < maxVal) {
			return true;
		}
		return false;
	}

	@Override
    public String toString() {
        return String.format("COVID19-British Variant");
    }
}
