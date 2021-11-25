package Virus;

import Population.Person;
import Population.Sick;
import Simulation.Clock;

public class BritishVariant implements IVirus{
	private static boolean british_m=true;
	private static boolean chinese_m=false;
	private static boolean southafrican_m=false;
	private final static double probKillUnder18 = 0.01;
	private final static double probKillAbove18 = 0.1;
	private final static double probContag = 0.7;
	@Override
	public double contagionProbability(Person p) {
		return BritishVariant.probContag * p.contagionProbability();
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
			prob = BritishVariant.probKillUnder18;
		}
		else {
			prob = BritishVariant.probKillAbove18;
		}
		double maxVal = IVirus.tryToKillEquation(prob, timeElapsed);
		
		rand = Math.random();
		if(rand < maxVal) {
			return true;
		}
		return false;
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
	@Override
    public String toString() {
        return String.format("COVID19-British Variant");
    }
}
