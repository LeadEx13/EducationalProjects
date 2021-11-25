package Country;

import java.util.List;

import Location.Location;
import Population.Person;

public class Moshav extends Settlement {
	
	/**
	* @see Settlement
	*/
	public Moshav(String name, Location location, List<Person> people, RamzorColor ramzorColor) {
		super(name, location, people, ramzorColor);
	}
	/**
	* @see Settlement
	*/
	@Override
	public RamzorColor calculateRamzorGrade() {
		double p = this.contagiousPercent();
		double temp = Math.pow(1.2,ramzorColor.getColor());
		temp = temp * (p - 0.35);
		temp =  Math.pow(temp, 5);
		double color = 0.3+ 3 * temp;
		this.ramzorColor = RamzorColor.byValue(color);
        return RamzorColor.byValue(color);
    }	
	@Override
    public String toString() {
        return String.format("Settlement's type = Moshav;" + super.toString());
    }

}
