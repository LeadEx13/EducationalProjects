package Virus;

import Population.Person;
import Population.Sick;


public interface IVirus {
	
	public double contagionProbability(Person p);
	/**
     * this method calculate the probability the given person will be infected from this virus
     * @param p: the person that we want to calculate his probability to get sick 
     * @return the probability the given person will be infected from this virus
     */
	
	public boolean tryToContagion(Person a, Person b);
	/**
     * this method check if the first person (param a) can contagion the second person (param b) (if not already sick) 
     * by calculate  tryToContagionEquation
     * @param a the Sick person who try to contagion the second person
     * @param b the person who might be contagion
     * @return True if the person b contracted the virus 
     */
	
	public boolean tryToKill(Sick s);
	/**
     * this method calculate the probability if the given person will die  
     * by calculate  see tryToKillEquation  
     * @param s the Sick person who might die
     * @return True if the person died other wise false 
     */
	
	static double tryToContagionEquation(double distance){
		double temp = Math.pow(Math.E, 2 - (0.25 * distance));
        return Math.min(1, 0.14 * temp);
    }
	static double tryToKillEquation(double p, long time ){
		double temp = Math.pow(time - 15, 2);
        return Math.max(0, p - (0.01 * p * temp));
    }
}
