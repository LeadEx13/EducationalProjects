package Population;

import Virus.VirusManager;
import Country.Settlement;
import Location.Point;
import Virus.IVirus;
import Population.Person;

public abstract class Person {
	/**
	   * This method is constructor for class Person.
	   * @param age the age of this person 
	   * @param Location the location of this person in the given settlement 
	   * @param settlement the settlement this person belong to
	   * 
	*/
	private int age;
	private  Point location;
	protected  Settlement settlement;

	public Person(int age,Point location , Settlement settlement) {
		this.age = age;
		this.location = location;
		this.settlement = settlement;
	}
	public Person(Person p) {
		this.age=p.age;
		this.location=p.location;
		this.settlement=p.settlement;
	}
	public abstract double contagionProbability();
	
	public Person contagion(IVirus iv) {
		
		Sick s;
		s = new Sick(this.GetAge(), this.GetLocation(),this.GetSettlement(), VirusManager.contagion(iv));
		return s;
	}
	public int GetAge() {
		return this.age	;
	}
	public Point GetLocation() {
		return this.location;
	}
	public Settlement GetSettlement() {
		return this.settlement;
	}
	public void SetSettlement(Settlement s) {
		this.settlement = s;
	}
	public void SetAge(int age) {
		this.age = age;
	}
	public void SetPoint(Point l) {
		this.location = l;
	}
	public double calculateDistance(Person p){
		int deltaX = this.location.getX() - p.GetLocation().getX();
		int deltaY = this.location.getY() - p.GetLocation().getY();
		double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
		return distance;
	}
	@Override
	public boolean equals(Object o) {	  
		if (o == this) {
			return true;
		}
		if (!(o instanceof Person)) {
			return false;
		}        
		Person p = (Person) o; 
		return this.age == p.age && this.location.equals(p.location);
	}
	@Override
    public String toString() {
        return String.format("age = " + age + ";settlement name = " + settlement.getName()+";location = " + location);
    }
}
