package Country;

import java.util.List;
import java.util.Random;

import Location.Location;
import Location.Point;
import Population.Person;
import Population.Sick;

public abstract class Settlement {	
	
	private String name;
	private Location location;
	private List<Person> people;
	protected RamzorColor ramzorColor;	
	
	/**
	   * This method is constructor for class Settlement.
	   * @param name the name of this settlement 
	   * @param Location the borders of this settlement 
	   * @param people list of the settlement's citizen
	   * @param ramzorColor represent the status(color) of the settlement   
	   * 
	*/
	public Settlement(String name,Location location,List<Person> people,RamzorColor ramzorColor) {
		this.name = name;
		this.location = location;
		this.people = people;
		this.ramzorColor = ramzorColor;
	}	
	
	/**
	   * This method calculate and update the current Ramzor grade of this settlement.	   
	   * @return ramzorColor the color of this settlement   
	   * 
	*/
	public abstract RamzorColor calculateRamzorGrade() ;
	
	/**
	   * This method calculate the sick percentage .	   
	   * @return the sick percentage after normalization   
	   * 
	*/
	public double contagiousPercent() {

		double counter = 0;
		for (int i = 0;i<people.size();++i) {
			if(people.get(i) instanceof Sick) {
				counter += 1;
			}
		}
		
		return (counter/people.size());
	}
	
	/**
	   * This method random point inside the settlement.	   
	   * @return random point inside the settlement's border    
	   * 
	*/
	public Point randomLocation() {
		Random random = new Random();
        int x= random.nextInt(this.location.getSize().getWidth());
        int y= random.nextInt(this.location.getSize().getHeight());
        // add x and y to the top left corner point
        /*
         * location.getPosition() is the + symbol so we need to add x and subtract y
         * +------------*
         * *------------*
         * *------------*
         * *------------*         
         */
        return new Point(this.location.getPosition().getX() + x,this.location.getPosition().getY() - y);
	}
	
	/**
	   * This method remove the given person from the settlement's citizen
	   * @param p the person object we want to remove 	   
	   * @return true if the removal succeeded other wise false
	   * 
	*/
	public boolean removePerson(Person p) {
		return this.people.remove(p);		 
	}
	/**
	   * This method add the given person from the settlement's citizen
	   * @param p the person object we want to add 	   
	   * @return true 
	   * 
	*/
	public boolean addPerson(Person p) {
		this.people.add(p);
		return true;
	}
	/**
	   * This method transfer the given person from this settlement to the given one
	   * @param p the person object we want to transfer
	   * @param s The settlement to which we want to move	   
	   * @return true 
	   * 
	*/
    public boolean transferPerson(Person p,Settlement s) {
    	s.addPerson(p);
    	this.people.remove(p);
		return true;	
    }	
    /**
	   * Get the settlement's name	 
	   * @return  settlement's name
	   * 
	*/
    public String getName() {
		return name;
	}
    /**
	   * Set the settlement's name
	   * @param settlement's name
	   * 
	*/
	public void setName(String name) {
		this.name = name;
	}
	/**
	   * Get the settlement's location	 
	   * @return  settlement's location
	   * 
	*/
	public Location getLocation() {
		return location;
	}
	 /**
	   * Set the settlement's location
	   * @param settlement's location
	   * 
	*/
	public void setLocation(Location l) {
		this.location = l;
	}
	/**
	   * Set the settlement's people
	   * @return settlement's people
	   * 
	*/
	public List<Person> getPeople() {
		return this.people;
	}
	/**
	   * Set the settlement's people
	   * @param settlement's people
	   * 
	*/
	public void setPeople(List<Person> people) {
		this.people = people;
	}
	/**
	 * Returns a string representation of the object.
	 * @return  a string representation of the object.
	 */
	@Override
    public String toString() {
        return String.format("name = " + name + ";location=(" + location+");Citizen= " + people.size() +";RamzorColor=" +ramzorColor +"; Sick precentage = " +this.contagiousPercent()*100);
    }
}
