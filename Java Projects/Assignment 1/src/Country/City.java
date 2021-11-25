package Country;

import java.util.List;

import Location.Location;
import Population.Person;

public class City extends Settlement {
	/**
	* @see Settlement
	*/
	public City(String name, Location location, List<Person> people, RamzorColor ramzorColor) {
		super(name, location, people, ramzorColor);
	}
	/**
	* @see Settlement
	*/
	@Override
	public RamzorColor calculateRamzorGrade() {
		double p = this.contagiousPercent();
		double temp = p * 1.25;
		double color = 0.2 * Math.pow(4, temp);
		this.ramzorColor = RamzorColor.byValue(color);
        return RamzorColor.byValue(color);
    }
	@Override
    public String toString() {
        return String.format("Settlement's type = City;" + super.toString());
    }
	

}
