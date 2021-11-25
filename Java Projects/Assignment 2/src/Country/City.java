package Country;

import Location.Location;

public class City extends Settlement {
	public City(String name, Location location,int population,int capacity) {
		super(name, location,population,capacity);
	}
	@Override
	public RamzorColor calculateRamzorGrade() {
		double p = this.contagiousPercent();
		double temp = p * 1.25;
		double color = 0.2 * Math.pow(4, temp);
		if(color<=0.4)
			return RamzorColor.GREEN;
		if (color> 0.4 && color<=0.6)
			return  RamzorColor.YELLOW;
		if (color>0.6 && color<=0.8)
			return  RamzorColor.ORANGE;
		else
			return RamzorColor.RED;
    }
	@Override
    public String toString() {
        return String.format("Settlement's type = City;" + super.toString());
    }
}
