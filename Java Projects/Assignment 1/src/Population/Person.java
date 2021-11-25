package Population;

import Country.Settlement;
import Location.Point;
import Virus.IVirus;

public abstract class Person {
	
	private int age;
	private  Point location;
	protected  Settlement settlement;
	
	/**
	   * This method is constructor for class Person.
	   * @param age the age of this person 
	   * @param Location the location of this person in the given settlement 
	   * @param settlement the settlement this person belong to
	   * 
	*/
	public Person(int age,Point location , Settlement settlement) {
		this.age = age;
		this.location = location;
		this.settlement = settlement;
	}
	/**
	   * This method is copy constructor for class Person.
	   * @param p the person we want to clone 
	   * 
	*/
	public Person(Person p) {
		this.age=p.age;
		this.location=p.location;
		this.settlement=p.settlement;
	}

	/**
	   * This method calculate the virus-independent probability of infection
	   * @return the virus-independent probability of infection 
	   * 
	*/
	public abstract double contagionProbability();
	
	/**
	   * This method change this person to sick and update the Database according it
	   * @param virus: the virus that the person is infected with 
	   * @return new object of this person's after change him to Sick  
	   * 
	*/
	public Person contagion(IVirus virus) {
		Person p;
		try {
			p = new Sick(this, virus);
			this.settlement.removePerson(this);
			this.settlement.addPerson(p);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	/**
	   * Get the person's age
	   * @return  person's age
	   * 
	*/
	public int GetAge() {
		return this.age	;
	}
	/**
	   * Get the person's location in his settlement
	   * @return  person's location in his settlement
	   * 
	*/
	public Point GetLocation() {
		return this.location;
	}
	/**
	   * Get the person's settlement
	   * @return  person's settlement
	   * 
	*/
	public Settlement GetSettlement() {
		return this.settlement;
	}
	/**
	   * Set the person's settlement
	   * @param  person's settlement
	   * 
	*/
	public void SetSettlement(Settlement s) {
		this.settlement = s;
	}
	/**
	   * Set the person's age
	   * @param age: The person's age
	   * 
	*/
	public void SetAge(int age) {
		this.age = age;
	}
	/**
	   * Set the person's location in his settlement
	   * @param location  person's location in his settlement
	   * 
	*/
	public void SetPoint(Point l) {
		this.location = l;
	}
	/**
	   * This method calculate the distance between this person to the given one
	   * @param p The person to whom we want to measure the distance
	   * @return the distance between this person to the p
	   * 
	*/
	public double calculateDistance(Person p)
	{
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
