package Country;

import java.util.List;

import Location.Location;
import Population.Person;

public class Kibbutz extends Settlement {
	
	/**
	* @see Settlement
	*/
	public Kibbutz(String name, Location location, List<Person> people, RamzorColor ramzorColor) {
		super(name, location, people, ramzorColor);
	}
	
	/**
	* @see Settlement
	*/
	@Override
	public RamzorColor calculateRamzorGrade() {
		double p = this.contagiousPercent();
		double temp = Math.pow(1.5,ramzorColor.getColor());
		temp = temp * (p - 0.4);
		temp =  Math.pow(temp, 3);
		double color = 0.45 + temp;
		this.ramzorColor = RamzorColor.byValue(color);
        return RamzorColor.byValue(color);
    }
	@Override
    public String toString() {
        return String.format("Settlement's type = Kibbutz;" + super.toString());
    }

}
