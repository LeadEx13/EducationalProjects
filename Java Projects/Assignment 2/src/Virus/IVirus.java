package Virus;

import Population.Person;
import Population.Sick;


public interface IVirus {
	
	public double contagionProbability(Person p);
	public boolean tryToContagion(Person a, Person b);
	public boolean tryToKill(Sick s);
	static double tryToContagionEquation(double distance){
		double temp = Math.pow(Math.E, 2 - (0.25 * distance));
        return Math.min(1, 0.14 * temp);
    }
	static double tryToKillEquation(double p, long time ){
		double temp = Math.pow(time - 15, 2);
        return Math.max(0, p - (0.01 * p * temp));
    }
}
