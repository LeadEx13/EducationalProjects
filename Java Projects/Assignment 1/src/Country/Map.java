package Country;
import java.util.ArrayList;
import java.util.Arrays;
public class Map {
	

	private  Settlement[] settlements;
	
	/**
	* This method is the default constructor for class Map.   
	* 
	*/
	public Map()
	{
		this.settlements = new Settlement[]{};
	}
	/**
	* This method is constructor for class Map.
	* @param array of Settlements   
	* 
	*/
	public Map(Settlement[] s)
	{
		this.setSettlements(s);
		
	}
	/**
	   * Get the settlements array	 
	   * @return settlements array
	   * 
	*/
	public  Settlement[] getSettlements() {
		return this.settlements;
	}
	 /**
	   * Set the settlements array
	   * @param settlements array
	   * 
	*/
	public  void setSettlements(Settlement[] s) {
		this.settlements = new Settlement[s.length];
		 
        // Copy elements of s[] to this.settlements[]
        for (int i = 0; i < s.length; i++)
        	this.settlements[i] = s[i];
	}
	
	 /**
	   * this method remove the given settlement object from the array
	   * @param settlement object we want to remove
	   * 
	*/
	public void removeSettlement(Settlement s)
	{
		ArrayList<Settlement> tempList = new ArrayList<Settlement>(Arrays.asList(this.settlements));
	    tempList.remove(s);
	    this.settlements = tempList.toArray(this.settlements);		
	}
	/**
	   * this method add the given settlement object from the array
	   * @param settlement object we want to add
	   * 
	*/
	public void addSettlement(Settlement s)
	{
		ArrayList<Settlement> tempList = new ArrayList<Settlement>(Arrays.asList(this.settlements));
	    tempList.add(s);
	    this.settlements = tempList.toArray(this.settlements);		
	}
	@Override
    public String toString() {
		String temp = "";
		for (Settlement s: settlements) {
			temp += s.toString() + "\n";
		}		
        return temp;
    }
}
