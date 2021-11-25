package Population;

import Simulation.Clock;
import Country.Settlement;
import Location.Point;

public class Vaccinated extends Person {
	
	private long vaccinationTime;
	public Vaccinated(int age, Point locantion, Settlement settlement, long vaccinationTime) {
		super(age, locantion, settlement);
		this.vaccinationTime = vaccinationTime;
	}
	public Vaccinated(Person p, long vaccinationTime) {
		super(p);
		this.vaccinationTime = vaccinationTime;
	}
	@Override
	public double contagionProbability() {
		double probabilty;
		long timeElapsed = Clock.now() - this.vaccinationTime; 
		if (timeElapsed < 21){
			probabilty = Math.min(1, 0.56 + 0.15 * Math.sqrt(21-timeElapsed));
		} else {
			probabilty = Math.max(0, 1.05/ (timeElapsed - 14));
		}
		return probabilty;
	}
	public long GetVaccinationTime() {
		return vaccinationTime;
	}
	public void SetVaccinationTime(long vaccinationTime) { // for next dose
		this.vaccinationTime = vaccinationTime;
	}
	@Override
    public String toString() {
        return String.format(super.toString()+ ";vaccinationTime = " + vaccinationTime);
    }
}
