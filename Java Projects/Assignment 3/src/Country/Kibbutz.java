package Country;


import Location.Location;

public class Kibbutz extends Settlement {
	/**
	* @see Settlement
	*/
	public Kibbutz(String name, Location location,int population,int capacity) {
		super(name, location, population,capacity);
	}
	@Override
	public RamzorColor calculateRamzorGrade() {
		double p = this.contagiousPercent();
		double temp = Math.pow(1.5,ramzorColor.getVal());
		temp = temp * (p - 0.4);
		temp =  Math.pow(temp, 3);
		double color = 0.45 + temp;
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
        return String.format("Settlement's type = Kibbutz;" + super.toString());
    }

}
