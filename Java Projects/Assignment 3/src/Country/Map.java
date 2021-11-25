package Country;
import java.util.concurrent.CyclicBarrier;
import Simulation.Main;
import java.util.ArrayList;
import java.util.Arrays;

public class Map {
	
	private  Settlement[] settlements;
	private static int size=0;
	public CyclicBarrier cycle;

	public Map(){
		/**
		* This method is the default constructor for class Map.   
		* 
		*/
		this.settlements = new Settlement[]{};
	}
	public Map(Settlement[] s){
		/**
		* This method is constructor for class Map.
		* @param array of Settlements   
		* 
		*/
		this.settlements=new Settlement[size];
		this.setSettlements(s);
	}
	public static int getSize() {
		/**
		   * Get the map size	 
		   * @return map size
		   * 
		*/
		return size;
		}
	public static void setSize() {
		/**
		   * Set the map size+1 
		   * @return map size
		   * 
		*/
		size++;
	}
	
	public static void resetSize() {
		/**
		   * Reset the map Size	 
		   * @return map default
		   * 
		*/
		size=0;
	}
	public  Settlement[] getSettlements() {
		/**
		   * Get the settlements array	 
		   * @return settlements array
		   * 
		*/
		return this.settlements;
	}
	public  void setSettlements(Settlement[] s) {
		/**
		   * Set the settlements array
		   * @param settlements array
		   * 
		*/
		this.settlements = new Settlement[s.length];
        for (int i = 0; i < s.length; i++)
        	this.settlements[i] = s[i];
	}
	public void removeSettlement(Settlement s){
		/**
		   * this method remove the given settlement object from the array
		   * @param settlement object we want to remove
		   * 
		*/
		ArrayList<Settlement> tempList = new ArrayList<Settlement>(Arrays.asList(this.settlements));
	    tempList.remove(s);
	    this.settlements = tempList.toArray(this.settlements);		
	}
	public boolean isPause(){
		/**
		   * 
		   * @param if simulation is Paused or not
		   * 
		*/
		return Main.getPause();
	}
	public void start_thread(){
		for(int i=0;i<settlements.length;i++){
			new Thread(getSettlements()[i]).start();
		}
	}
	public boolean isStop(){
		/**
		   * 
		   * @param if simulation is Stopped or not
		   * 
		*/
		return Main.getStop();
	}
	public void addSettlement(Settlement s){
		/**
		   * this method add the given settlement object from the array
		   * @param settlement object we want to add
		   * 
		*/
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
