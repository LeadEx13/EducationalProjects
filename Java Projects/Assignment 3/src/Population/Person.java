package Population;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Country.Settlement;
import Location.Point;
import Virus.IVirus;
import Population.Person;
import Virus.BritishVariant;
import Virus.ChineseVariant;
import Virus.SouthAfricanVariant;

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
		Random rand = new Random();
		List<IVirus> mutation = new ArrayList<IVirus>();
		if (iv instanceof BritishVariant){
			if (BritishVariant.get_british_m()){
				mutation.add(new BritishVariant());
			}
			if (BritishVariant.get_chinese_m()){
				mutation.add(new ChineseVariant());
			}
			if (BritishVariant.get_southafrican_m()){
				mutation.add(new SouthAfricanVariant());
			}
			if(mutation.size()==0){
				return new Healthy(this.GetAge(),this.GetLocation(),this.GetSettlement());
			}
			int x1=rand.nextInt(mutation.size());
			iv = mutation.get(x1);
			s=new Sick(this.GetAge(),this.GetLocation(),this.GetSettlement(),iv);
			return s;
		}
		if (iv instanceof ChineseVariant){
			if (ChineseVariant.get_british_m()){
				mutation.add(new BritishVariant());
			}
			if (ChineseVariant.get_chinese_m()){
				mutation.add(new ChineseVariant());
			}
			if (ChineseVariant.get_southafrican_m()){
				mutation.add(new SouthAfricanVariant());
			}
			if(mutation.size()==0){
				return new Healthy(this.GetAge(),this.GetLocation(),this.GetSettlement());
			}
			int x1=rand.nextInt(mutation.size());
			iv = mutation.get(x1);
			s=new Sick(this.GetAge(),this.GetLocation(),this.GetSettlement(),iv);
			return s;
		}
		if (iv instanceof SouthAfricanVariant){
			if (SouthAfricanVariant.get_british_m()){
				mutation.add(new BritishVariant());
			}
			if (SouthAfricanVariant.get_chinese_m()){
				mutation.add(new ChineseVariant());
			}
			if (SouthAfricanVariant.get_southafrican_m()){
				mutation.add(new SouthAfricanVariant());
			}
			if(mutation.size()==0){
				return new Healthy(this.GetAge(),this.GetLocation(),this.GetSettlement());
			}
			int x1=rand.nextInt(mutation.size());
			iv = mutation.get(x1);
			s=new Sick(this.GetAge(),this.GetLocation(),this.GetSettlement(),iv);
			return s;
		}
		return new Healthy(this.GetAge(),this.GetLocation(),this.GetSettlement());
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
