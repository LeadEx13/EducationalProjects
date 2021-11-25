package Virus;

import Population.Person;
import Population.Sick;
import Simulation.Clock;

public class SouthAfricanVariant implements IVirus{
	private final static double probKillUnder18 = 0.05;
	private final static double probKillAbove18 = 0.08;
	private final static double probContagUnder18 = 0.6;
	private final static double probContagAbove18 = 0.5;
	private static boolean british_m=false;
	private static boolean chinese_m=false;
	private static boolean southafrican_m=true;
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
	public static boolean get_british_m(){
		return british_m;
	}
	public static boolean get_chinese_m(){
		return chinese_m;
	}
	public static boolean get_southafrican_m(){
		return southafrican_m;
	}
	public static void set_british_m(boolean b){
		british_m=b;
	}
	public static void set_chinese_m(boolean b){
		chinese_m=b;
	}
	public static void set_southafrican_m(boolean b){
		southafrican_m=b;
	}
}
