package Virus;



import Population.Person;
import Population.Sick;
import Simulation.Clock;

public class ChineseVariant implements IVirus{
	private final static double probKillUnder18 = 0.001;
	private final static double probKillBetween18To55 = 0.05;
	private final static double probKillAbove55 = 0.1;
	private final static double probContagUnder18 = 0.2;
	private final static double probContagBetween18To55 = 0.5;
	private final static double probContagAbove55 = 0.7;
	/**
     * this method calculate the probability the given person will be infected from this virus
     * @param p: the person that we want to calculate his probability to get sick 
     * @return the probability the given person will be infected from this virus
     */
	public double contagionProbability(Person p) {
		int age = p.GetAge();
		if (age < 18) {
			return  ChineseVariant.probContagUnder18 * p.contagionProbability();
		}
		else if (age >= 18 && age <= 55) {
			return ChineseVariant.probContagBetween18To55 * p.contagionProbability();
		} 
		else {
			return ChineseVariant.probContagAbove55 * p.contagionProbability();
		}
		
	}
	/**
     * this method check if the first person (param a) can contagion the second person (param b) (if not already sick) 
     * by calculate  tryToContagionEquation
     * @param a the Sick person who try to contagion the second person
     * @param b the person who might be contagion
     * @return True if the person b contracted the virus 
     */
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
			prob = ChineseVariant.probKillUnder18;
		}
		else if (age >= 18 && age <= 55) {
			prob = ChineseVariant.probContagBetween18To55;
		} 
		else {
			prob = ChineseVariant.probKillAbove55;
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
        return String.format("COVID19-Chinese Variant");
    }
}
